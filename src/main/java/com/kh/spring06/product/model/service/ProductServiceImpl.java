package com.kh.spring06.product.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring06.product.model.dao.ProductDao;
import com.kh.spring06.product.model.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	//상품 추가용 메소드
	@Override
	public int addProduct(Product p) {
		
		return productDao.addProduct(p);
	}
	//상품 전제조회용 메소드(정렬기준 추가)
	@Override
	public List<Product> selectProductList(Map<String,String> param) {
		return productDao.selectProductList(param);
	}
	
	//상품 상세조회
	@Override
	public Product detailProduct(int pno) {
		return productDao.detailProduct(pno);
	}
	
	//상품 삭제 메소드
	@Override
	public int deleteProduct(int pno) {
		return productDao.deleteProduct(pno);
	}
}
