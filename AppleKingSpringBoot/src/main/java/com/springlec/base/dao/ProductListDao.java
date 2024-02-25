package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductListDao {
	/*
	 * Description 	: uProductlist.jsp 에 뿌려줄 product list  생성 
	 * Detail 		: 
	 * 					1.
	 * Author		:
	 * Date			:
	 * Update 		:
	 * 
	 */
	//Product 정보를 불러오는 DAO 
	public int productCntDao() throws Exception;
	public List<ProductListDto> productListDao(String query, String searchContent, int startRow, int pageSize) throws Exception;
}
