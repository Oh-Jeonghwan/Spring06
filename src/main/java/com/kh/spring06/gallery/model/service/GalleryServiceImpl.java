package com.kh.spring06.gallery.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring06.gallery.model.dao.GalleryDao;
import com.kh.spring06.gallery.model.vo.Attachment;
import com.kh.spring06.gallery.model.vo.Gallery;
import com.kh.spring06.gallery.model.vo.GalleryContent;
import com.kh.spring06.gallery.model.vo.GalleryReply;
import com.kh.spring06.gallery.model.vo.GalleryThumbnail;

@Service
public class GalleryServiceImpl implements GalleryService {
	 @Autowired
	 private GalleryDao galleryDao;

	@Override
	public int insert(Gallery gallery) {
		return galleryDao.insert(gallery);
	}

	@Override
	public List<GalleryThumbnail> galleryList(HashMap<String, Integer> param) {
		return galleryDao.galleryList(param);
	}

	@Override
	public int attInsert(Attachment at) {
		return galleryDao.attInsert(at);
	}

	@Override
	public int selectGno() {
		return galleryDao.selectGno();
	}

	@Override
	public List<GalleryContent> selectContent(int bno) {
		return galleryDao.selectContent(bno);
		
	}

	@Override
	public int totalCount() {
		return galleryDao.totalCount();
	}

	@Override
	public int galleryCountUp(int bno) {
		return galleryDao.galleryCountUp(bno);
	}

	@Override
	public int gRinsert(GalleryReply gr) {
		return galleryDao.gRinsert(gr);
	}

	@Override
	public List<GalleryReply> gRList(int gno) {
		return galleryDao.gRList(gno);
	}

	@Override
	public Gallery galleryOne(int gno) {
		return galleryDao.galleryOne(gno);
	}
	
	@Override
	public int galleryUpdate(Gallery gu) {
		return galleryDao.galleryUpdate(gu);
	}

	@Override
	public int statusN(int gno) {
		return galleryDao.statusN(gno);
	}

	@Override
	public int attUpdate(Attachment at) {
		return galleryDao.attUpdate(at);
	}

	@Override
	public int gDelete(int gno) {
		return galleryDao.gDelete(gno);
	}

}
