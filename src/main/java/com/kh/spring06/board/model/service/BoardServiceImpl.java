package com.kh.spring06.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring06.board.model.dao.BoardDao;
import com.kh.spring06.board.model.vo.Board;
import com.kh.spring06.board.model.vo.BoardReply;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	//게시판 글작성용 메소드
	@Override
	public int write(Board b) {
		return boardDao.write(b);
	}

	//게시글 작성용 메소드들 틀
	//1. 조회수 증가
	@Override
	public int increaseCount(int boardNo) {
		return boardDao.increaseCount(boardNo);
	}
	//2. 게시글 상세 조회
	@Override
	public Board content(int boardNo) {
		return boardDao.content(boardNo);
	}

	@Override
	public List<Board> selectList(Map<String, Object> param) {
		return boardDao.selectList(param);
	}

	@Override
	public int totalCount(Map<String, Object> param) {
		return boardDao.totalCount(param);
	}

	@Override
	public int insertBReply(BoardReply br) {
		return boardDao.insertBReply(br);
	}

	@Override
	public List<BoardReply> brList(int bno) {
		return boardDao.brList(bno);
	}

	@Override
	public Board selectBoard(int bno) {
		return boardDao.selectBoard(bno);
	}

	@Override
	public int boardUpdate(Board b) {
		return boardDao.boardUpdate(b);
	}

	@Override
	public int bDelete(int bno) {
		return boardDao.bDelete(bno);
	}

}
