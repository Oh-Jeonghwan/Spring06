package com.kh.spring06.admin.controller;

import java.util.List;

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
	//private SqlSession sqlSession;
	//회원 정보 리스트 화면을 띄워주는 메소드
	@GetMapping("/memberList.do")
	public String memberList(Model model) {
		
		//화면을 뿌리기 전에
		//조회할 것 => DB에 저장된 회원의 정보를 싹 다 긁어와야함 select
		//Spring+mybatis => 도구를 쓰겠다고 연동 먼저(==객체생성, @Autowired)
		//도구이름: sqlSession
		//모듈화 전
		//List<Member> list = sqlSession.selectList("member.selectList");
		
		//모듈화 후
		//서비스 단으로 토스
		List<Member> list = memberService.selectList();
		
		model.addAttribute("list", list);
		
		//조회를 다 했다면=> 수하물 붙이고(Model 객체를 이용해서 addAttribute 메소드 이용)
		return "admin/memberList";//"WEB-INF/views/admin/memberList.jsp"
	}
	
	
}
