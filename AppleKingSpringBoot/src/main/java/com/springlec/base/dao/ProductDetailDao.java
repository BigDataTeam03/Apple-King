package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductDetailDao {
	public List<ProductListDto> productDetailDao(String product_code) throws Exception;
}
