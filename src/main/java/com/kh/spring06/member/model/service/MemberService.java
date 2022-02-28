package com.kh.spring06.member.model.service;

import java.util.List;

import com.kh.spring06.member.model.vo.Member;

//인터페이스를 만들고 거기에 따라서 따로 규현해야 하는 이유: 코드의 확장성, 호환성을 높이기 위해 
public interface MemberService {

	//이곳에는 메소드의 틀만 정의(추상 메소드)
	//모듈화 작업 3단계=> Service 인터페이스에 메소드 틀 만들기
	
	//id체크
	int idCheck(String id);
	
	//nick체크
	int nickCheck(String nick);
	
	//회원가입 기능 메소드
	int join (Member m);
	//public 생략이유: 어차피 재정의해서 써야하기에(다른 곳에서 접근 가능해야하기 때문)
	
	//로그인 기능 메소드 틀
	Member login(Member member);
	
	//관리자용 전체 조회 메소드 틀
	List<Member> selectList();

	

	
}
