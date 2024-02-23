package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface aProductListDaoService {
	/*--------------------------------------
	 * Description: aProductList  Service
	 * Author :  KBS
	 * Date : 2024.02.23
	 * Update : 2024.02.23 KBS 
	 * 		1. 페이지 생성
	 *-------------------------------------- 
	 */
	//  상품목록 전체를 보여주고 정렬, 검색 변수가 추가된 인터페이스
	public List<ProductListDto> productlist(String product_name,
			 String selected, String orderby ) throws Exception;
//	public void updateProduct()
	
}
