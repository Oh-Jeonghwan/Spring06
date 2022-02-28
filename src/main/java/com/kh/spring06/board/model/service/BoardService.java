package com.kh.spring06.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring06.board.model.vo.Board;
import com.kh.spring06.board.model.vo.BoardReply;

public interface BoardService {
	
	//게시판 글작성 용 메소드 틀
	int write(Board b);

	//게시글 작성용 메소드들 틀
	//1. 조회수 증가
	int increaseCount(int boardNo);
	//2.게시글 조회
	Board content(int boardNo);

	List<Board> selectList(Map<String, Object> param);

	int totalCount(Map<String, Object> param);

	int insertBReply(BoardReply br);

	List<BoardReply> brList(int bno);

	Board selectBoard(int bno);

	int boardUpdate(Board b);

	int bDelete(int bno);

}
