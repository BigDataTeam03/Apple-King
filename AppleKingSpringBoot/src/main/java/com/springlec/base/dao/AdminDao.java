package com.springlec.base.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;

public interface AdminDao {
	/*--------------------------------------
	 * Description: Admin  Dao
	 * Author :  KBS,pdg
	 * Detail : 
	 * 	AdminDao 에서 정의 되는 Interface 함수의 이름은.xml의 id 와 동일 (spring boot 가 연결한다.
	 * 	<<AdminDao.xml id list>>
	 * 		1. productInsertDao
	 * 		2. productlist
	 * 		3. updateProduct
	 * 		4. custList
	 * 		5. questionList
	 * 		6. updateQuestion
	 * Date : 2024.02.22
	 * Update : 2024.02.23 KBS 
	 * 	
	 *  1. 상품 리스트 출력, 수정하는 다오 작성
	 *-------------------------------------- 
	 */
	
	// 상품목록 전체를 보여주고 정렬, 검색 변수가 추가된 기능을 .xml에 보내는 다오
	public List<ProductListDto> productList(String product_name, String selected, String orderby ) throws Exception;
	
	// 선택한 상품 목록을 업데이트하는 다오	
	public void updateProduct(String product_name,String product_qty,String origin,
			String manufacture_date,String weight,String size,
			String detail_image_name,String view_count,String product_reg_date,
			String kind,String product_image_names, String product_code) throws Exception;
	
	// 고객 리스트 출력 다오
	public List<MemberDto> custList(String name, String notThis, String orderby2) throws Exception;
	
	// 문의 게시판 출력 다오
	public List<InquireDto> questionList(String Not) throws Exception;
	
	// 문의에 대한 답변을 업데이트 하는 다오
	public void updateQuestion(String answer, String inquire_code) throws Exception;
	
	// 상품을 인서트하는 다오
	public void productInsertDao(
			String product_code, 	//1
			String product_name, 	//2
			String product_rank,	//3
			String product_qty, 	//4
			String origin,			//5
			String manufacture_date,//6
			String weight, 			//7
			String size, 			//8
			String product_reg_date,//9
			String kind,			//10
			String product_image_name,	//11
			String detail_image_name,	//12
			String view_count,		//13
			String price,			//14
			String sold_qty,		//15
			Double starred
		 ) throws Exception ;
}
