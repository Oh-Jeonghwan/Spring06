package com.kh.spring06.gallery.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring06.gallery.model.vo.Attachment;
import com.kh.spring06.gallery.model.vo.Gallery;
import com.kh.spring06.gallery.model.vo.GalleryContent;
import com.kh.spring06.gallery.model.vo.GalleryReply;
import com.kh.spring06.gallery.model.vo.GalleryThumbnail;

@Repository
public class GalleryDaoImpl implements GalleryDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Gallery gallery) {
		return sqlSession.insert("gallery.galleryInsert",gallery);
	}

	@Override
	public List<GalleryThumbnail> galleryList(HashMap<String, Integer> param) {
		return sqlSession.selectList("gallery.galleryList",param);
	}

	@Override
	public int attInsert(Attachment at) {
		return sqlSession.insert("gallery.gelleryAttInsert",at); 
	}

	@Override
	public int selectGno() {
		return sqlSession.selectOne("gallery.selectGno");
	}

	@Override
	public List<GalleryContent> selectContent(int bno) {
		return sqlSession.selectList("gallery.selectGContent", bno);
	}

	@Override
	public int totalCount() {
		return sqlSession.selectOne("gallery.getTotalCount");
	}

	@Override
	public int galleryCountUp(int bno) {
		return sqlSession.update("gallery.galleryCountUp",bno);
	}

	@Override
	public int gRinsert(GalleryReply gr) {
		return sqlSession.insert("gallery.gRinsert",gr);
	}

	@Override
	public List<GalleryReply> gRList(int gno) {
		return sqlSession.selectList("gallery.gRList",gno);
	}

	@Override
	public Gallery galleryOne(int gno) {
		return sqlSession.selectOne("gallery.galleryOne",gno);
	}
	
	@Override
	public int galleryUpdate(Gallery gu) {
		return sqlSession.update("gallery.galleryUpdate",gu);
	}

	@Override
	public int statusN(int gno) {
		return sqlSession.update("gallery.statusN",gno);
	}

	@Override
	public int attUpdate(Attachment at) {
		return sqlSession.insert("gallery.attUpdate",at);
	}

	@Override
	public int gDelete(int gno) {
		return sqlSession.update("gallery.gDelete",gno);
	}

}
