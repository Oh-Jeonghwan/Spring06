package com.kh.spring06.gallery.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.spring06.gallery.model.vo.Attachment;
import com.kh.spring06.gallery.model.vo.Gallery;
import com.kh.spring06.gallery.model.vo.GalleryContent;
import com.kh.spring06.gallery.model.vo.GalleryReply;
import com.kh.spring06.gallery.model.vo.GalleryThumbnail;

public interface GalleryService {

	int insert(Gallery gallery);

	List<GalleryThumbnail> galleryList(HashMap<String, Integer> param);

	int attInsert(Attachment at);

	int selectGno();

	List<GalleryContent> selectContent(int bno);

	int totalCount();

	int galleryCountUp(int bno);

	int gRinsert(GalleryReply gr);

	List<GalleryReply> gRList(int gno);
	
	Gallery galleryOne(int gno);

	int galleryUpdate(Gallery gu);

	int statusN(int gno);

	int attUpdate(Attachment at);

	int gDelete(int gno);

}
