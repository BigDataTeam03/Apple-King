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
	 * 	<<2024.02.26 By pdg>>
	 * 	1. 정렬기능 추가.
	 * 
	 */
	
	// 상품 총 개수 반환 DAO
	public int productCntDao() throws Exception;
	
	//Product (검색조건, 검색내용, 정렬) 정보를 불러오는 DAO 
	public List<ProductListDto> productListDao(	String searchQuery,
												String searchContent,
												String sortingOption,
												int startRow,
												int pageSize) throws Exception;
}
