package com.kh.spring06.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String memberId;//MEMBER_ID VARCHAR2(20) PRIMARY KEY, -- 아이디
	private String memberPwd;//MEMBER_PWD VARCHAR2(16) NOT NULL, -- 비밀번호
	private String memberNick; //MEMBER_NICK VARCHAR2(24) NOT NULL UNIQUE, -- 닉네임
	private String post;//POST CHAR(6), -- 우편번호
	private String baseAddr;//BASE_ADDR VARCHAR2(300), -- 기본주소
	private String extraAddr;//EXTRA_ADDR VARCHAR2(300), -- 상세주소
	private String birth;//BIRTH DATE NOT NULL, -- 생일
	private String phone;//PHONE CHAR(13) NOT NULL, -- 휴대폰
	private String memberIntro;//MEMBER_INTRO VARCHAR2(4000), -- 자기소개
	private String memberAuth;//MEMBER_AUTH VARCHAR2(9) NOT NULL, -- 회원등급
	private String enrollDate;//ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL, -- 가입일
	private String lastLogin;//LAST_LOGIN DATE -- 로그인한 시간
	
	
}
