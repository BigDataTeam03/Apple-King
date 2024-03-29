package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductDetailDao {
	public ProductListDto productDetailDao(String product_code) throws Exception;
	// 장바구니에 넣을 때 장바구니에 이미 존재하는지 여부를 가리는 다오
	public String checkItem(String cust_id, String product_code) throws Exception;
	// 장바구니에 인서트하는 다오
	public void insertCart(String cust_id, String product_code, int cart_qty) throws Exception;
	// 장바구니에 같은 상품이 존재 할 때 수량을 업데이트를 하는 다오
	public void updateCart(String cust_id, String product_code, int cart_qty) throws Exception;
}
