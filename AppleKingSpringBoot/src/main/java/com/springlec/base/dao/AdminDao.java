package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;

public interface AdminDao {
	/*--------------------------------------
	 * Description: Admin  Dao
	 * Author :  KBS	
	 * Date : 2024.02.22
	 * Update : 2024.02.23 KBS 
	 * 	
	 *  1. 상품 리스트 출력, 수정하는 다오 작성
	 *-------------------------------------- 
	 */
	
//  상품목록 전체를 보여주고 정렬, 검색 변수가 추가된 기능을 .xml에 보내는 다오
	public List<ProductListDto> productlist(String product_name, String selected, String orderby ) throws Exception;
// 선택한 상품 목록을 업데이트하는 다오	
	public void updateProduct(String product_name,String product_qty,String origin,
			String manufacture_date,String weight,String size,
			String detail_image_name,String view_count,String product_reg_date,
			String kind,String product_image_names, String product_code) throws Exception;
	// 고객 리스트 출력 다오
	public List<MemberDto> custList(String name, String notThis, String orderby2) throws Exception;
	// 문의 게시판 출력 다오
	public List<InquireDto> questionList() throws Exception;
	// 문의에 대한 답변을 업데이트 하는 다오
	public void updateQuestion(String answer, String inquire_code)throws Exception;
	
}
