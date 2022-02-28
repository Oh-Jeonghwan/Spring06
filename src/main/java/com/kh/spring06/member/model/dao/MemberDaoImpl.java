package com.kh.spring06.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring06.member.model.vo.Member;

//Repository: 저장소라는 뜻
//			  Spring 에서는 주로 DB(==데이터를 보관하는 저장소)와 관련된 작업을 처리하겠다.
//			    즉, DB와 관련된 작업이라고 하면 CRUD를 일컫는다.(==영속성 작업)
@Repository //Dao 단임을 명시
public class MemberDaoImpl implements MemberDao {
	//모듈화 작업 7단계=> 영속성 작업을 위한 sqlSession 도구를 연동
	@Autowired
	private SqlSession sqlSession;
	
	//아이디 체크
	@Override
	public int idCheck1(String id) {
		return sqlSession.selectOne("member.idCheck2",id);
	}
	
	//nick체크
	@Override
	public int nickCheck(String nick) {
		return sqlSession.selectOne("member.nickCheck",nick);
	}	

	//회원 가입
	@Override
	public int join(Member m) {
		return sqlSession.insert("member.join",m);
	}
	
	//로그인 기능
	@Override
	public Member login(Member member) {
		return sqlSession.selectOne("member.loginMember", member);
	}
	
	//관리자 회원 전체 조회
	@Override
	public List<Member> selectList() {
		return sqlSession.selectList("member.selectList");
	}

	//마지막 로그인
	@Override
	public int lastLogin(Member member) {
		
		return sqlSession.update("member.lastLogin",member);
	}

}	