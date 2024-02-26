package com.springlec.base.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.AdminDaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	/*--------------------------------------
	 * Description: Admin 컨트롤러
	 * Author :  KBS
	 * Date : 2024.02.24
	 * Update : 2024.02.23 KBS 
	 * 		1.  상품 리스트출력 기능 완료
	 *      2.  정렬, 검색기능 호환 완료
	 *      3.  상품 업데이트 기능 완료
	 * Update : 2024.02.24 KBS 
	 * 		1. 고객 리스트가 데이터가 안나옴..
	 *      2. 문의 내역도 데이터가 안나옴
	 *      3. jsp 에서 만든 페이지 연결 완료
	 *-------------------------------------- 
	
	*/

	
	@Autowired
	AdminDaoService service;
	
	@PostMapping("/aProductListUpdate")
	public ResponseEntity<List<ProductListDto>> PLIST(HttpServletRequest request,HttpServletResponse response,
													 HttpSession session, Model model	) throws Exception {
	// Json 값 한글 지정
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	//  상품이름 널처리w
	String product_name = "";
	if (request.getParameter("name") == null) {
		product_name = "";					
	}else {
	    product_name = "%"+request.getParameter("name")+"%" ;
	}			
	//상품 총 갯수를 나타내기 위한 변수지정
    int totalProductNumber =0;
	 
	// AJAX 에서 가져온 값을 변수에 받아온다	
	// 라디오 버튼으로 선택한 상세 검색 값
	String origin = request.getParameter("origin");
	String size = request.getParameter("size");
	String kind = request.getParameter("kind");

	// 콤보박스로 선택된 정렬값
	String sorting = request.getParameter("sorting");
	
	///기본값은 재고순으로
	if (sorting == null) {
		sorting = "stokHigh";
	}
	//정렬 기본값 		
	String orderby = "";
	
	//선택한 콤보박스값에 따라 정렬쿼리문 변경
	//재고순
	 if (sorting.equals("stokHigh")) 
		 	orderby = "order by product_qty desc";
     if (sorting.equals("stokLow"))
    	 orderby = "order by product_qty asc";
    	 
    //생산일자순	 
     if (sorting.equals("makeHigh")) 
		 	orderby = " order by manufacture_date desc";
     if (sorting.equals("makeLow"))
    	 orderby = " order by manufacture_date asc";
    	 
    //무게순	 
     if (sorting.equals("weightHigh")) 
		 	orderby = " order by weight desc";
     if (sorting.equals("weightLow"))
    	 orderby = " order by weight asc";
    	 
    //조회수순	 
     if (sorting.equals("viewHigh")) 
		 	orderby = " order by view_count desc";
     if (sorting.equals("viewLow"))
    	 orderby = " order by view_count asc";
    	 
    //등록일순	 
     if (sorting.equals("insertHigh")) 
		 	orderby = " order by product_reg_date desc";
     if (sorting.equals("insertLow"))
    	 orderby = " order by product_reg_date asc";
    	 
    //가격순	 
     if (sorting.equals("priceHigh")) 
		 	orderby = " order by price desc";
     if (sorting.equals("priceLow"))
    	 	orderby = " order by price asc";
     
     // 라디오 버튼으로 선택하는 상세 검색 기능
     String selected ="";
     if (origin != null && !origin.isEmpty()) {
         selected += " and origin = '" + origin +"'";
     }
     if (size != null && !size.isEmpty()) {
         selected += " and size = '" + size +"'";
     }
     if (kind != null && !kind.isEmpty()) {
         selected += " and kind = '" + kind + "'";
     }
      
     //입력시 코드를 생성하기위한 세션
     totalProductNumber++;
     session.setAttribute("totalProductNumber", totalProductNumber );
     //  서비스에 해당 변수를 넣어 다오를 실행시키고 리스트에 넣는다
  
     List<ProductListDto> productList = service.productlist(product_name, selected, orderby);
   
     return ResponseEntity.ok().body(productList);
	}
	
	// 상품수정 메소드
	@PostMapping("/aProductUpdate")
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		// out 선언
		
		// 수정된 변수 받기
		String product_name 		= request.getParameter("name");
		String product_qty 			= request.getParameter("qty");
		String origin 		= request.getParameter("origin");
		String manufacture_date 	= request.getParameter("manufacture");
		String weight 		= request.getParameter("weight");
		String size 		= request.getParameter("size");
		String detail_image_name 	= request.getParameter("detailImage");
		String view_count 		= request.getParameter("view");
		String product_reg_date 		= request.getParameter("regDate");
		String kind 		= request.getParameter("kind");
		String product_image_names = request.getParameter("productImage");
		String product_code 		= request.getParameter("code");
		// 서비스를 통해 다오로 변수를 집어 넣는다
		service.updateProduct(product_name, product_qty, origin,
							  manufacture_date, weight, size,
							  detail_image_name, view_count, product_reg_date,
							  kind, product_image_names, product_code);
			out.print("성공");
		
		
	}
	
//-----------------------------------------------------
// 			Admin top 의 연결 부분	
//-----------------------------------------------------
	// 상품 조회 수정
	@GetMapping("/aProductListUpdate")
	public String go() throws Exception{
		return "AdminPart/aProductListUpdate";
	}		
	// 상품 등록
	@GetMapping("/aProductInsert")
	public String goinsert() throws Exception {
		return "AdminPart/aProductInsert";
	}
	// 회원 목록 조회
	@GetMapping("/aCustomerList")
	public String goCustomerList() throws Exception {
		return "AdminPart/aCustomerList";
	}
	// 매출 조회
	@GetMapping("/aCustomerOrderList")
	public String goOrderList() throws Exception {
		return "AdminPart/aCustomerOrderList";
	}
	// 문의 내역
	@GetMapping("/aProductQuestionList")
	public String goquestion() throws Exception {
		return "AdminPart/aProductQuestionAnswer";
	}
	// 유저 홈
	@GetMapping("/cGoHome")
	public String cGoHome() throws Exception {
		return "uProductList";
	}
	// 관리자 홈
	@GetMapping("/aGoHome")
	public String aGoHome() throws Exception {
		return "AdminPart/aProductListUpdate";
	}
//------------------------------------------------------
	// 상품 등록 메소드
	@PostMapping("/aProductInsert")
	public void insert(HttpServletRequest request) throws Exception {
		
	}
	// 고객 리스트를 보여주는 메서드
	@PostMapping("/custmoerList")
	public ResponseEntity<List<MemberDto>> custlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//PrintWriter out = response.getWriter();
		// Json 값 한글 지정
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// 이름 검색값 가져오는 변수
		String name =    "'%"+(String)request.getAttribute("name")+"%'";
		//정렬옵션 가져오는 변수 
		String sortOption = request.getParameter("sortOption");
		//시작시 선택되지 않았으니 디폴트값으로 날짜정렬
		if (sortOption == null) {
			sortOption = "reg_date";
		}
		//쿼리문 기본값 날짜
		String orderby = " order by reg_date asc";
		
		//선택한 콤보박스값에 따라 정렬쿼리문 변경
		if (sortOption.equals("rankHigh")) 
		 	orderby = "order by cust_rank desc";
		if (sortOption.equals("dateNew"))
			orderby = "order by reg_date desc";
		if (sortOption.equals("rankLow"))
    	 	orderby = "order by cust_rank asc";
		if (sortOption.equals("dateLate"))
    	 	orderby = "order by reg_date asc";
		//  .xml 에 <> 가 주석으로 인식되서 여기서 함....
		//   고객 테이블에 어드민도 포함되어있기 때문에 어드민을 빼고 조회한다
		String notThis = "cust_id <> 'admin123' ";
		// 서비스 실행 
	List<MemberDto>	memberList = service.custList(name,notThis,orderby);
		
	
			
	return ResponseEntity.ok().body(memberList);
		
		
	}
	@PostMapping("/inqueireList")
	public ResponseEntity<List<InquireDto>> qustionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		List<InquireDto> QList = service.questionList();
		
		return ResponseEntity.ok().body(QList);
		
	}
	
}
