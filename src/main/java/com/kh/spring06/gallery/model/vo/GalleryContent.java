package com.kh.spring06.gallery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryContent {
	private int galleryNo;
	private String galleryTitle;
	private String galleryExplain;
	private String path;
	private String enrollDate;
	private String galleryWriter;
	
}
