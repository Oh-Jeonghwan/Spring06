package com.kh.spring06.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring06.board.model.vo.Board;
import com.kh.spring06.board.model.vo.BoardReply;

@Repository
public class BoardDaoImpl implements BoardDao{
	@Autowired
	private SqlSession sqlSession;
	
	//게시글 작성용 메소드 dao
	@Override
	public int write(Board b) {
		return sqlSession.insert("board.write", b);
	}
	//게시글 작성용 메소드들 틀
	//1. 조회수 증가
	@Override
	public int increaseCount(int boardNo) {
		return sqlSession.update("board.increaseCount",boardNo);
	}
	//2. 게시글 상세조회
	@Override
	public Board content(int boardNo) {
		return sqlSession.selectOne("board.detailView",boardNo);
	}
	
	//보드 목록 조회
	@Override
	public List<Board> selectList(Map<String, Object> param) {
		return sqlSession.selectList("board.unionList",param);
	}
	//보드 등록글 수 총 개수
	@Override
	public int totalCount(Map<String, Object> param) {
		return sqlSession.selectOne("board.totalCount",param);
	}
	//보드 댓글 등록
	@Override
	public int insertBReply(BoardReply br) {
		return sqlSession.insert("board.insertBReply",br);
	}
	@Override
	public List<BoardReply> brList(int bno) {
		return sqlSession.selectList("board.brList", bno);
	}
	@Override
	public Board selectBoard(int bno) {
		return sqlSession.selectOne("board.selectdBoard",bno);
	}
	@Override
	public int boardUpdate(Board b) {
		return sqlSession.update("board.boardUpdate",b);
	}
	@Override
	public int bDelete(int bno) {
		return sqlSession.update("board.bDelete",bno);
	}

}
