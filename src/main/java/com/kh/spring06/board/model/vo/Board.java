package com.kh.spring06.board.model.vo;

public class Board {
	private int boardNo;//BOARD_NO NUMBER PRIMARY KEY, -- 글번호
	private String boardHead;//BOARD_HEAD CHAR(6) CHECK(BOARD_HEAD in ('정보','공지','유머')), -- 카테고리명
	private String boardTitle;//BOARD_TITLE VARCHAR2(300) NOT NULL, -- 제목
	private String boardWriter;//BOARD_WRITER REFERENCES MEMBER(MEMBER_ID) ON DELETE SET NULL, -- 작성자
	private String boardContent;//BOARD_CONTENT VARCHAR2(4000) NOT NULL, -- 내용
	private String createDate;//CREATE_DATE DATE DEFAULT SYSDATE NOT NULL, -- 작성일
	private int boardRead;//BOARD_READ NUMBER DEFAULT 0 NOT NULL, -- 조회수
	private int boardReplyCount;//BOARD_REPLYCOUNT NUMBER DEFAULT 0 NOT NULL -- 댓글 개수
	
	public Board() {
		super();
	}
	
	public Board(int boardNo, String boardHead, String boardTitle, String boardWriter, String boardContent,
			String createDate, int boardRead, int boardReplyCount) {
		super();
		this.boardNo = boardNo;
		this.boardHead = boardHead;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.createDate = createDate;
		this.boardRead = boardRead;
		this.boardReplyCount = boardReplyCount;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardHead() {
		return boardHead;
	}
	public void setBoardHead(String boardHead) {
		this.boardHead = boardHead;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getBoardRead() {
		return boardRead;
	}
	public void setBoardRead(int boardRead) {
		this.boardRead = boardRead;
	}
	public int getBoardReplyCount() {
		return boardReplyCount;
	}
	public void setBoardReplyCount(int boardReplyCount) {
		this.boardReplyCount = boardReplyCount;
	}
	
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardHead=" + boardHead + ", boardTitle=" + boardTitle
				+ ", boardWriter=" + boardWriter + ", boardContent=" + boardContent + ", createDate=" + createDate
				+ ", boardRead=" + boardRead + ", boardReplyCount=" + boardReplyCount + "]";
	}
	

}
