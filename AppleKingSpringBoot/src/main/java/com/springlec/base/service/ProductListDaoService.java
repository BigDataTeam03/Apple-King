package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductListDaoService {

	public int productCntDao() throws Exception;
	
	public List<ProductListDto> productListDao(	String searchQuery,
												String searchContent,
												String sortingOption,
												int startProduct,
												int pageSize) throws Exception;

}
