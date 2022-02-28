package com.kh.spring06.gallery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
	
	private int galleryNO;//	 	GALLERY_NO NUMBER PRIMARY KEY, -- 갤러리제목
	private String galleryTitle;//	    GALLERY_TITLE VARCHAR2(100) NOT NULL, -- 사진번호
	private String galleryExplain;//	    GALLERY_EXPLAIN VARCHAR2(2000) NOT NULL, -- 설명
	private String enrollDate;//	    ENROLL_DATE DATE DEFAULT SYSDATE -- 등록일
	private int count;
	private String status;
	private String galleryWriter;
}
