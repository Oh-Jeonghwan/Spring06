package com.kh.spring06.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring06.board.model.service.BoardService;
import com.kh.spring06.board.model.vo.Board;
import com.kh.spring06.board.model.vo.BoardReply;
import com.kh.spring06.common.Criteria;
import com.kh.spring06.common.PgaeDto;
import com.kh.spring06.member.model.vo.Member;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//한 개의  url로 검색도 되고 전체 조회도 되게끔
	@GetMapping("/union.do")
	public String union(
				@RequestParam(required = false) String type
			  , @RequestParam(required = false) String keyword
			  , Model model
			  , @ModelAttribute Criteria cri) {
		//값을 가공해서 처리(적어도 개별 값 2개 이상일 경우는 무조건 가공)
		//case 1) vo객체로 가공(이건 이제 우리가 안 해도 됨 => @ModelAttribute 로 알아서 해서 넘겨주니까) 
		//case 2)  개별값들이 넘겨짐=> 절대로 vo 객체로 가공할 수가 없는 사이
		//		  이 경우에는? 맵 이용
		//		 => 임의로 키-밸류 세트로 묶어서 일종의 vo 객체화
		
		//키는 스트링 밸류는 스트링 또는 스트링 형의 변수
		//객체를 생성해서 값을 넣어주려면 자식 타입으로 생성해서 넘겨준다.
		
		//Map 계열에 값을 넣을 때 쓰는 메소드 => put("키", "밸류");
		//vo가 아니라 map 으로 가공
		Map<String, Object> param = new HashMap<>();
		
		//type, keyword 를 키-밸류 세트로 put
		param.put("type", type);
		param.put("keyword", keyword);
		
		int pageStart = (cri.getPage()-1)*cri.getPerPageNum()+1;
		int perPageNum = ((cri.getPage()-1)*cri.getPerPageNum()+1)+cri.getPerPageNum()-1;
		
		param.put("pageStart", pageStart);
		param.put("perPageNum",perPageNum);
		
		//쿼리문 실행시 구멍을 메꿀 수도 있으니 param을 같이 보냄
		List<Board> list = boardService.selectList(param);
		
		PgaeDto pageMaker =new PgaeDto();
		pageMaker.setCri(cri);
		
		
		
		int totalNum = boardService.totalCount(param);
		pageMaker.setTotalCount(totalNum);
		
		if(param.get("type")!=null && param.get("keyword")!=null) {
			
			model.addAttribute("keyword",param.get("keyword"));
			model.addAttribute("type",param.get("type"));
		}
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list",list);
		
		return "board/boardList";
	}
	//게시글 작성 폼을 띄워주는 메소드
	@GetMapping("/write.do")
	public String write(HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		 if(loginUser == null) {
			 	session.setAttribute("alertMsg", "로그인을 해주세요");
				return "redirect:../";
			}
			else {
		
				return "board/write"; // "/WEB-INF/views/board/write.jsp"
			}
	}
	
	//게시글 등록처리를 위한 메소드
	@PostMapping("/write.do")
	public String write(@ModelAttribute Board b,
						HttpSession session) {
		//boardWriter 가 null
		
		//사용자 정보를 어디선간ㄴ 얻어와야 함
		//세선으로부터 현재 사용자의 정보를 뽑아서 boardWriter 필드에 담을 것
		String boardWriter = ((Member)session.getAttribute("loginUser")).getMemberId();
		
		b.setBoardWriter(boardWriter);
		
		//서비스단으로 토스
		int result = boardService.write(b);
		
		if(result>0) {
			session.setAttribute("alertMsg", "게시글이 등록되었습니다.");
			return "redirect:union.do";
		}
		else {
			session.setAttribute("alertMsg", "게시글 등록 실팬");
			return "redirect:write.do";
		}
	}
	
	//게시글 상세보기 메소드
	@GetMapping("content.do")
	public String content(@RequestParam int boardNo
						, Model model) {
		
		//게시글 조회 흐름
		//=> 조회수 증가 먼저, 성공했다면 SELECT 문을 실행
		
		//조회수증가
		int result = boardService.increaseCount(boardNo);
		Board board = new Board();
		//조회수 증가가 성공했다면
		if(result>0) {
			board = boardService.content(boardNo);
		}	
		
		if(board!=null) {
			//수하물 부치기
			 model.addAttribute("board",board);
			//응답뷰
			return "board/content";
		}
		else {
			return "redirect:union.do";
		}
		
	}
	
	//보드게시판 댓글등록 ajax메소드
	@ResponseBody
	@PostMapping("rinsert.do")
	public int rinsert(@RequestParam String content
						, @RequestParam int bno
						, HttpSession session) {
		
		String userId = ((Member)session.getAttribute("loginUser")).getMemberId();
		BoardReply br = new BoardReply();
		
		br.setRefBno(bno);
		br.setReplyContent(content);
		br.setReflyWriter(userId);
		
		int bri =  boardService.insertBReply(br);
		
		return bri;
	}
	
	//보드게시판 댓글리스트 불러오는 ajax 메소드
	@ResponseBody
	@PostMapping("rlist.do")
	public List<BoardReply> brList(@RequestParam int bno){
		List<BoardReply> list = boardService.brList(bno);
		return list;
	}
	
	
	@GetMapping("boardUpdate.do")
	public String boardUpdate(@RequestParam int bno
							, HttpSession session) {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		 if(loginUser == null) {
			 	session.setAttribute("alertMsg", "로그인을 해주세요");
				return "redirect:../";
			}
			else {
				Board selectBoard = boardService.selectBoard(bno);
				session.setAttribute("board", selectBoard);
				return "board/contentUpdate";
			}
	}
	
	@PostMapping("boardUpdate.do")
	public String boardUpdate(@ModelAttribute Board b
							, HttpSession session) {
		int boardUpdate = boardService.boardUpdate(b);
		
		if(boardUpdate>0) {
			session.setAttribute("alertMsg", "글이 수정되었습니다.");
		}
		else {
			session.setAttribute("alertMsg", "수정실패");
		}
		
			return "redirect:content.do?boardNo="+b.getBoardNo();
	}
	
	@GetMapping("delete.do")
	public String boardDelete(@RequestParam int bno
							, HttpSession session) {
		int bDe= boardService.bDelete(bno);
		
		if(bDe>0) {
			session.setAttribute("alertMsg", "글이 삭제되었습니다.");
		}
		else {
			session.setAttribute("alertMsg", "글이 삭제 되지 않았습니다.");
		}
		
		return "redirect:union.do";
	}
	
	
}
