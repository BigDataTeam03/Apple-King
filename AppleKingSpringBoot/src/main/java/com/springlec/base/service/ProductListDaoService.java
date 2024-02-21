package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductListDaoService {

	public int productCntDao() throws Exception;
	public List<ProductListDto> productListDao(String query, String searchContent, int startRow, int pageSize) throws Exception;

}
