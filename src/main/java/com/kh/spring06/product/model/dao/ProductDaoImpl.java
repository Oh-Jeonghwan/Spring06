package com.kh.spring06.product.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring06.product.model.vo.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//상품 추가 메소드
	@Override
	public int addProduct(Product p) {
		return sqlSession.insert("product.addProduct",p);
	}
	//상품 전체 조회용 메소드(정렬기준 추가)
	@Override
	public List<Product> selectProductList(Map<String,String> param) {
		return sqlSession.selectList("product.productList",param);
	}
	
	//상품 상세조회
	@Override
	public Product detailProduct(int pno) {
		return sqlSession.selectOne("product.detailProduct",pno);
	}
	//상품 삭제
	@Override
	public int deleteProduct(int pno) {
		return sqlSession.delete("product.deleteProduct",pno);
	}
}
