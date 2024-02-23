package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface AdminDao {
	/*--------------------------------------
	 * Description: Admin  Dao
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.23 KBS 
	 * 	
	 *-------------------------------------- 
	 */
	
//  상품목록 전체를 보여주고 정렬, 검색 변수가 추가된 기능을 .xml에 보내는 다오
	public List<ProductListDto> productlist(String product_name, String selected, String orderby ) throws Exception;
}
