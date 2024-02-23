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
	// 선택 상품 업데이트하는 변수가 추가된 인터페이스
	public void updateProduct(String product_name,String product_qty,String origin,
			String manufacture_date,String weight,String size,
			String detail_image_name,String view_count,String product_reg_date,
			String kind,String product_image_names, String product_code) throws Exception;
	
}
