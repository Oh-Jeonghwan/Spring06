package com.kh.spring06.gallery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryThumbnail {
	private int galleryNo;
	private String galleryTitle;
	private String path;
	private int count;
	private double rating;
}
