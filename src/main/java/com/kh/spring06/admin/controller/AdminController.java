package com.kh.spring06.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring06.member.model.service.MemberService;
import com.kh.spring06.member.model.vo.Member;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	
	//회원 정보 리스트 화면을 띄워주는 메소드
	@GetMapping("/memberList.do")
	public String memberList(Model model
						   , HttpSession session) {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser==null||!loginUser.getMemberId().equals("admin")) {
			session.setAttribute("alertMsg", "관리자가 아닙니다.");
			return "redirect:../";
		}
		else {
			//서비스 단으로 토스
			List<Member> list = memberService.selectList();
			
			model.addAttribute("list", list);
			
			//조회를 다 했다면=> 수하물 붙이고(Model 객체를 이용해서 addAttribute 메소드 이용)
			return "admin/memberList";//"WEB-INF/views/admin/memberList.jsp"
		}
		
	}
	
	
}
