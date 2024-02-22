package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductDetailDaoService {
	public List<ProductListDto> productDetailDao(String product_code) throws Exception; 

}
