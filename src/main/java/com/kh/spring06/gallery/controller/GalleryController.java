package com.kh.spring06.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring06.common.Criteria;
import com.kh.spring06.common.MyfileRenamePolicy;
import com.kh.spring06.common.PgaeDto;
import com.kh.spring06.gallery.model.service.GalleryService;
import com.kh.spring06.gallery.model.vo.Attachment;
import com.kh.spring06.gallery.model.vo.Gallery;
import com.kh.spring06.gallery.model.vo.GalleryContent;
import com.kh.spring06.gallery.model.vo.GalleryReply;
import com.kh.spring06.gallery.model.vo.GalleryThumbnail;
import com.kh.spring06.gallery.model.vo.UploadVo;
import com.kh.spring06.member.model.vo.Member;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	 @Autowired
	 private GalleryService galleryService;
	 
	 //갤러리 게시판 띄워주는 메소드
	 @GetMapping("/list.do")
	 public String list(Model model
			    	  , @ModelAttribute Criteria cri) throws Exception {
		 
		//  커맨드 객체로 Criteria를 매개변수로 넣어줘, 넘어오는 page와 perPageNum정보를 받습니다.
		 
         // 해당 cri 를 이용해서 service->dao->mapper.xml 순으로 접근하면서 DB처리를 해 cri 전달된

         // 현재 페이지 정보를 기준으로 <GalleryThumbnail> 객체를 담은 List가 반환될 것입니다.
		 
		 /*
			 * boardLimit가 10이라고 가정하에
			 * currentPage = 1 => 시작값 1, 끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * ...
			 * 
			 * => 시작값 = (currentPage - 1)*boardLimit + 1
			 * => 끝값 = ((currentPage - 1)*boardLimit + 1) + boardLimit - 1
			 */
		 
		 	int pageStart = (cri.getPage()-1)*cri.getPerPageNum()+1;
			int perPageNum = ((cri.getPage()-1)*cri.getPerPageNum()+1)+cri.getPerPageNum()-1;
			
			HashMap<String,Integer> param = new HashMap<>();
			param.put("pageStart", pageStart);
			param.put("perPageNum",perPageNum);
		 
		 List<GalleryThumbnail> list = galleryService.galleryList(param);
		 
		 
		// 이제 view jsp 페이지에서 페이징 처리를 위해 사용할 PageMaker 객체를 생성하고
		 PgaeDto pageMaker =new PgaeDto();
		 
		// Criteria를 set해주고 setTotalCount() 를 해주어야
		 
        // 페이징처리에 필요한 것들이 내부적으로 계산될 수 있도록 작성했다고 했습니다.
		 pageMaker.setCri(cri);
		 int totalNum = galleryService.totalCount();
		 pageMaker.setTotalCount(totalNum);
		 
		 model.addAttribute("pageMaker", pageMaker);
		 model.addAttribute("list",list);
		 return "gallery/galleryList";
	 }
	 
	//갤러리 이미지 등록 폼을 띄워주는 메소드
	 @GetMapping("/add.do")
	 public String add(HttpSession session) {
		 Member loginUser = (Member)session.getAttribute("loginUser");
			
		 if(loginUser == null) {
			 	session.setAttribute("alertMsg", "로그인을 해주세요");
				return "redirect:../";
			}
			else {
				return "gallery/add"; //"WEB-INF/views/gallery/add.jsp"
			}
	 }
	 
	 //갤러리 인서트 
	 @PostMapping("/add.do")
	 public String add(@RequestParam String galleryTitle
			 		 , @RequestParam String galleryExplain
			 		 , @ModelAttribute UploadVo uploadVo  //파일 여러 개를 받는다.
			 		 , HttpSession session) {
		
		Gallery gallery = new Gallery();
		String userId=((Member)session.getAttribute("loginUser")).getMemberId();
		
		gallery.setGalleryWriter(userId);
		gallery.setGalleryTitle(galleryTitle);
		gallery.setGalleryExplain(galleryExplain);
		//web-workspace2 attachment 다시 보기
		
		int result = galleryService.insert(gallery);
		
		if(result>0) {
			//갤러리 테이블과 어태치먼트 테이블의 조인을 위한 갤러리 번호 가져오기 
			int refGno = galleryService.selectGno()-1;
			
			
			//성공 시 서버의 하드디스크에 파일을 저장
			//=>단, 이름을 변경해서 저장해야만 한다.(MyFileRename 클래스의 rename 메소드)
			Attachment at = new Attachment();
			//파일을 저장하기 위해 필요한 것: 저장 경로, 파일명, 
			String savePath = session.getServletContext().getRealPath("/resources/upfile/");
			
			
			//파일레벨 0번째에 1 나머지는 2해줘야 한다.
			//MultipartFile 객체에서 제공하는 transferTo 메소드를 이용해서 이관 
			if(uploadVo.isFileExist()) {
				
				for(int i=0; i<uploadVo.getUpfile().size(); i++) {
					String changeName1 = new MyfileRenamePolicy().rename(uploadVo.getUpfile().get(i).getOriginalFilename());
					File target1 = new File(savePath,changeName1);
					
					try {
						uploadVo.getUpfile().get(i).transferTo(target1);
						
						at.setOriginName(uploadVo.getUpfile().get(i).getOriginalFilename());
						at.setChangeName(changeName1);
						at.setRefGno(refGno);
						
						if(i==0) {
							at.setFileLevel(1);
						}
						else {
							at.setFileLevel(2);
						}
						int insertimg = galleryService.attInsert(at);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
				
			}
				
		}
		return "redirect:list.do";
	 }
	 
	 //갤러리 상세보기
	 @GetMapping("/content.do")
	 public String content(@RequestParam int bno
			 			  , Model model
			 			  , HttpSession session) {
		 int result = galleryService.galleryCountUp(bno);
		 List<GalleryContent> list = new ArrayList<>();
		 
		 if(result>0) {//bno를 통해 상세보기 페이지에 뿌려줄 객체
			list = galleryService.selectContent(bno);
		 }
		
		 if(!list.isEmpty()) {
			 model.addAttribute("list",list);
			 return "gallery/galleryContent";
		 }
		 else {
			 session.setAttribute("alertMsg", "게시물이 삭제되었습니다.");
			 return "redirect:list.do";
		 } 
	 }
	 
	 
	 //댓글 등록 메소드
	 //no converter found for return value of type: class java.lang.integer
	 //ajax 통신 시 jackson dependency 등록 안 했을 때 생기는 오류
	 //unknown return value type: java.lang.integer
	 //@ResponseBody 안 달면 생기는 오류
	 @ResponseBody
	 @PostMapping("/rinsert.do")
	 public int gRinsert(@RequestParam String content
			 		   , @RequestParam int gno
			 		   , HttpSession session) {
		 
		 GalleryReply gr = new GalleryReply();
		 
		 String galleryWriter = ((Member)session.getAttribute("loginUser")).getMemberId();
		 
		 gr.setRefGno(gno);
		 gr.setReplyContent(content);
		 gr.setReflyWriter(galleryWriter);
		 
		 int result = galleryService.gRinsert(gr);
		 
		 return result;
	 }
	 
	 //댓글 리스트 불러오는 메소드
	 @ResponseBody
	 @PostMapping("rlist.do")
	 public List<GalleryReply> gRList(@RequestParam int gno){
		 
		 List<GalleryReply> list = galleryService.gRList(gno);
		 
		 return list;
	 }
	 
	//갤러리 수정페이지에 뿌려줄 갤러리 객체 불러올 메소드
	@GetMapping("/update.do")
	public String GalleryUpdate(HttpSession session
							  , @RequestParam int gno) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		 if(loginUser == null) {
			 	session.setAttribute("alertMsg", "로그인을 해주세요");
				return "redirect:../";
			}
			else {
				//기존 갤러리 테이블에서 제목이랑 설명랑 가져오기
				
				Gallery gallery = galleryService.galleryOne(gno);
				
				session.setAttribute("gallery", gallery);
				session.setAttribute("gno", gno);
				return "gallery/galleryUpdate";
			}
		
	}
	
	//갤러리 수정을 위한 메소드
	@PostMapping("/update.do")
	public String GalleryUpdate(@ModelAttribute UploadVo uploadVo
							  , @RequestParam String galleryTitle
							  , @RequestParam String galleryExplain
							  , @RequestParam int gno
							  , HttpSession session) {
		Gallery gu = new Gallery();
		
		gu.setGalleryNO(gno);
		gu.setGalleryTitle(galleryTitle);
		gu.setGalleryExplain(galleryExplain);

		//먼저 갤러리 테이블 제목이랑 설명란 변경(gno를 이용)
		int galleryU = galleryService.galleryUpdate(gu);
		int statusN = 0;
			
		if(galleryU>0) {
			//먼저 관련 gno를 통해 관려 사진 status=N으로
			statusN = galleryService.statusN(gno);
		}
		
		if(statusN>0) {//갤러리 테이블 변경후 사진 업데이트
			
			//성공 시 서버의 하드디스크에 파일을 저장
			//=>단, 이름을 변경해서 저장해야만 한다.(MyFileRename 클래스의 rename 메소드)
			Attachment at = new Attachment();
			//파일을 저장하기 위해 필요한 것: 저장 경로, 파일명, 
			String savePath = session.getServletContext().getRealPath("/resources/upfile/");
			
			if(uploadVo.isFileExist()) {
				for(int i=0; i<uploadVo.getUpfile().size();i++) {
					String changeName = new MyfileRenamePolicy().rename(uploadVo.getUpfile().get(i).getOriginalFilename());
					//File 객체로 저장
					File target = new File(savePath,changeName);
					
					try {
						uploadVo.getUpfile().get(i).transferTo(target);
						
						//저장후 저장된 객체 Attachment 객체에 담아주기
						at.setRefGno(gno);
						at.setChangeName(changeName);
						at.setOriginName(uploadVo.getUpfile().get(i).getOriginalFilename());
						
						if(i==0) {
							at.setFileLevel(1);
						}
						else {
							at.setFileLevel(2);
						}
						
						int attU = galleryService.attUpdate(at);
						
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return "redirect:content.do?bno="+gno;
	}
	
	@GetMapping("delete.do")
	public String gDelete(@RequestParam int gno
						, HttpSession session) {
		int gDe = galleryService.gDelete(gno);
		
		if(gDe>1) {
			session.setAttribute("alertMsg", "삭제되었습니다.");
		}
		else {
			session.setAttribute("alertMsg", "삭제되었습니다.");
		}
		return "redirect:list.do?page=1";
	}
}
