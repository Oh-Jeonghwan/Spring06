package com.kh.spring06.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring06.member.model.dao.MemberDao;
import com.kh.spring06.member.model.vo.Member;

@Service // service 단임을 명시
public class MemberServiceImpl implements MemberService {
	
	//모듈화 작업 5단계=> MemberDao 객체를 @Autowired로 생성해두기
	@Autowired
	private MemberDao memberDao;
	//인터페이스 객체 생성가능? 안됨
	//=> 엄밀히 따지면 new 문으로 객체를 생성하는 거는 아니고
	//   도구를 가져다 쓰겠다고 연동한 것
	//=> 부모격인 MemberDao 인터페이스를 선언했지만 자바의 동적바인딩에 의해서
	//   자식인 MemberDaoImpl 클래스의 메소드가 호출되어 실행되는 구조
	
	//인터페이스의 추상메소드를 오버라이딩 해서 쓸 것
	
	//모듈화 작업 4단계 => Service 구현체에 메소드 오버라이딩
	@Override
	public int join(Member m) {
		return memberDao.join(m);
	}
	
	//아이디 체크
	@Override
	public int idCheck(String id) {
		return memberDao.idCheck1(id);
	}

	//닉네임 체크
	@Override
	public int nickCheck(String nick) {
		return memberDao.nickCheck(nick);
	}
	
	//로그인 처리를 해주는 메소드
	@Override
	public Member login(Member member) {
		int result = memberDao.lastLogin(member);
		Member loginUser = null;
		
		if(result>0) {
			loginUser = memberDao.login(member);
		}
		return loginUser;
	}
	
	//관리자 전체조회 메소드
	@Override
	public List<Member> selectList() {
		return memberDao.selectList();
	}

}
