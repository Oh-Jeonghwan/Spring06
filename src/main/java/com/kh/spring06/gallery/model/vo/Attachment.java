package com.kh.spring06.gallery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
	private int attNo;//ATT_NO NUMBER PRIMARY KEY, --첨부파일 번호 
    private String originName;//ORIGIN_NAME VARCHAR2(255) NOT NULL, --첨부파일 원래 이름
    private String changeName;//CHANGE_NAME VARCHAR2(255) NOT NULL, --첨부파일 변한 이름
    private String filePath;//FILE_PATH VARCHAR2(300) NOT NULL, --첨부파일 저장경로
    private String status;//STATUS CHAR(1) DEFAULT 'Y' NOT NULL, --첨부파일 상태
    private int refGno;//REF_GNO NUMBER NOT NULL, --참조하는 갤러리 번호
    private int fileLevel;//FILE_LEVEL NUMBER NOT NULL --파일레벨
}
