package com.springlec.base.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;

public interface AdminDaoService {
	/*--------------------------------------
	 * Description: aProductList  Service
	 * Author :  KBS
	 * Date : 2024.02.23
	 * Update : 2024.02.23 KBS 
	 * 		1. 페이지 생성
	 * Update : 2024.02.24 KBS
	 * 		1. JSP 에서 만든 페이지에 필요한 인터페이스 작성 완료
	 *-------------------------------------- 
	 */
	//  상품목록 전체를 보여주고 정렬, 검색 변수가 추가된 인터페이스
	public List<ProductListDto> productList(String product_name,
			 String selected, String orderby ) throws Exception;
	// 선택 상품 업데이트하는 변수가 추가된 인터페이스
	public void updateProduct(String product_name,String product_qty,String origin,
			String manufacture_date,String weight,String size,
			String detail_image_name,String view_count,String product_reg_date,
			String kind,String product_image_names, String product_code) throws Exception;
	// 고객 리스트를 출력하는 인터페이스
	public List<MemberDto> custList(String name,String notThis, String orderby2) throws Exception;
	
	//고객 문의 리스트 출력 하는 인터페이스
	public List<InquireDto> questionList(String Not) throws Exception;
	// 문의에 대한 답변을 업데이트하는 인터페이스
	public void updateQuestion(String answer, String inquire_code)throws Exception;
	
	// 상품 인서트 하는 인터페이스
	public void productInsertDao(
			String product_code 	,//1
			String product_name 	,//2
			String product_rank		,//3	
			String product_qty  	,
			String origin  			,
			String manufacture_date ,
			String weight  			,
			String size  			,
			String kind 			,
			String product_reg_date ,
			MultipartFile product_image, 
			MultipartFile detail_image,  	
			String view_count  		,
			String price,
			String sold_qty,
			String seller_id
			
			) throws Exception ;
	
}
