package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductDetailDao;
import com.springlec.base.model.ProductListDto;

@Service
public class ProductDetailDaoServiceImpl implements ProductDetailDaoService {

	@Autowired
	ProductDetailDao dao;

	@Override
	public List<ProductListDto> productDetailDao(String product_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.productDetailDao(product_code);
	}
	// 장바구니에 넣을 때 장바구니에 이미 존재하는지 여부를 가리는 서비스
	@Override
	public boolean checkItem(String cust_id, String product_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkItem(cust_id, product_code);
	}
	// 장바구니에 인서트하는 서비스
	@Override
	public void insertCart(String cust_id, String product_code, int cart_qty) throws Exception {
		// TODO Auto-generated method stub
		dao.insertCart(cust_id, product_code, cart_qty);
		
		
	}
	// 장바구니의 해당 상품의 수량을 업데이트하는 서비스
	@Override
	public void updateCart(String cust_id, String product_code, int cart_qty) throws Exception {
		// TODO Auto-generated method stub
		dao.updateCart(cust_id, product_code, cart_qty);
		
		
	}

}
