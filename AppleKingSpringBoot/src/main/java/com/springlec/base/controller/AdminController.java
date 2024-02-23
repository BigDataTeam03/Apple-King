package com.springlec.base.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.aProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	/*--------------------------------------
	 * Description: Admin 컨트롤러
	 * Author :  KBS
	 * Date : 2024.02.23
	 * Update : 2024.02.23 KBS 
	 * 		1. 
	 *      2. 
	 *-------------------------------------- 
	
	*/
	
	@Autowired
	aProductListDaoService service;
	
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
    System.out.println(" 서비스로 간다");
     List<ProductListDto> productList = service.productlist(product_name, selected, orderby);
     System.out.println("이제 뿌려준다" + productList);
     return ResponseEntity.ok().body(productList);
	}
	
	@GetMapping("/aProductListUpdate")
	public String go() throws Exception{
		return "AdminPart/aProductListUpdate";
	}		
	// 상품수정 메소드
	@PostMapping("/aProductUpdate")
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String code 		= request.getParameter("code");
		String name 		= request.getParameter("name");
		String qty 			= request.getParameter("qty");
		String origin 		= request.getParameter("origin");
		String manufacture 	= request.getParameter("manufacture");
		String weight 		= request.getParameter("weight");
		String size 		= request.getParameter("size");
		String detailImage 	= request.getParameter("detailImage");
		String view 		= request.getParameter("view");
		String regDate 		= request.getParameter("regDate");
		String kind 		= request.getParameter("kind");
		String productImage = request.getParameter("productImage");
	}
}
