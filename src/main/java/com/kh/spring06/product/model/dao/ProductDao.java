package com.kh.spring06.product.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring06.product.model.vo.Product;

public interface ProductDao {
	
	//상품 추가 메소드 틀
	int addProduct(Product p);
	
	//상품 전체조회용 메소드 틀(정렬기준 추가)
	List<Product> selectProductList(Map<String, String> param);

	//상품 상세조회
	Product detailProduct(int pno);
	//상품 삭제 메소드 틀
	int deleteProduct(int pno);
	

}
