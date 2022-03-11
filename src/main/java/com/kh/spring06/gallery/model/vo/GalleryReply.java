package com.kh.spring06.gallery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryReply {
	private int replyNo;//	REPLY_NO NUMBER NOT NULL,
	private String replyContent;//	REPLY_CONTENT VARCHAR(400) NOT NULL,
	private int refGno;//	REF_BNO NUMBER NOT NULL,
	private String reflyWriter; //	REFLY_WRITER VARCHAR2(20) NOT NULL,
	private String createDate;//	CREATE_DATE DATE DEFAULT SYSDATE NOT NULL,
	private String status;//	STATUS VARCHAR(1) DEFAULT 'Y'
	private int rating;
}
