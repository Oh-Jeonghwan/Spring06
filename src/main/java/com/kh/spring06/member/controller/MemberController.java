package com.kh.spring06.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring06.member.model.service.MemberService;
import com.kh.spring06.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	//@Autowired 어노테이션을 이용해야 한다. (스프링의 IoC 특성)
	@Autowired
	private MemberService memberService;
	//회원가입 폼을 띄워주는 요청처리하는 메소드
	@GetMapping("/join.do")
	public String join(HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "member/join"; // "/WEB-INF/views/member/login.jsp"
		}
		else {
			session.setAttribute("alertMsg", "이미 로그인 하셨습니다.");
			return "redirect:/";
		}
		
	}//모듈화 작업 필요 없음

	//아이디 체크
	@ResponseBody
	@PostMapping("idCheck.do")
	public int idCheck(@RequestParam String id) {
		int idCheck = memberService.idCheck(id);
		return idCheck;
	}
	
	//nick체크
	@ResponseBody
	@PostMapping("nickCheck.do")
	public int nickCheck (@RequestParam String nick) {
		int nickCheck = memberService.nickCheck(nick);
		return nickCheck;
	}
	
	//회원가입 요청을 처리해주는 메소드
	@PostMapping("/join.do")
	public String join(@ModelAttribute Member m
					 , HttpSession session) {
		//**모듈화 후
		//매개ㅐ변수로 넘겨받았던 요청값이 Member m을 서비스단으로 토스
		//INSERT 문 => 처리된 행의 개수
		int result = memberService.join(m);
		
		//결과에 따라 응답뷰 지정
		if(result>0) { //성공
			session.setAttribute("alertMsg","가입에 성공하였습니다.");
			return "redirect:../";
		}
		else {
			session.setAttribute("alertMsg","회원 가입이 되지 않았습니다.");
			return "redirect:join.do?error";
		}
		
	}
	
	//로그인 폼을 띄워주는 메소드
	///member/login.do로 get 방식으로 요청 시 로그인 폼이 보여져야 함
	@GetMapping("/login.do")
	public String login(HttpSession session) {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "member/login"; // "/WEB-INF/views/member/login.jsp"
		}
		else {
			session.setAttribute("alertMsg", "이미 로그인 하셨습니다.");
			return "redirect:/";
		}
		
		
	}
	
	//로그인 요청이 들어왔을 때 처리해주는 메소드
	///member/login.do로 post 방식으로 입력한 값들이 들어가서 처리돼야함
	@PostMapping("/login.do")
	public String login(@ModelAttribute Member member,
						HttpSession session) {
		//vo로 가공까지는 @ModelAttribute가 해서 전달해줌
		//vo 객체 service단으로 토스
		Member loginUser = memberService.login(member);
		
		//결과에 따른 응답 뷰
		if(loginUser == null) {
			session.setAttribute("alertMsg", "로그인이 안 됐습니다.");
		}
		else {
			    
				session.setAttribute("alertMsg", "로그인 되었습니다.");
				session.setAttribute("loginUser", loginUser);
		}
		
		//return "redirect:/"; //절대경로*/
		//return "redirect:/spring06/member/login.do"; 절대주소
		//return "redirect:login.do?error";
		return "redirect:/";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		//session 객체가 새로 생성되어 들어오는 게 아니라
		//원래 있던 로그인 유저를 담고 있는 세션이 들어온다.
		
		//로그아웃: session 에 있는 회원 정보를 지우면 됨
		//=> 방법1) 세션 무효화(invalidate 메소드)
		//=> 방법2) 해당 키값을 삭제(removeAttribute 메소드)
		
		//방법1)
		//session.invalidate();
		
		//방법2)
		session.removeAttribute("loginUser");
		//로그아웃되었습니다. 알림메시지 같이 띄워보자
		session.setAttribute("alertMsg", "로그아웃 됐습니다.");
		//이미 뮤효화된 세션에 수하물을 실어버림 => 오류
		//리무브어트리뷰트를 쓰던가, 알람을 포기하던가
		return "redirect:/";
	}
	
}
