package com.springlec.base.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("first_check")
public class ProductListController {
	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT LIST)
	 * Author : PDG, KBS, LS, Diana
	 * Date : 2024.02.21
	 * Details 
	 * 	- where from??
	 * Update :
	 * 		Update 2024.02.21 by LS, DK 
	 * 		 1. 기존의 Appleking 을 SpringBoot version 으로 변환. 
	 * 		 2. 상품리스트를 위하여 product DAO 를 만들고 product page 생성한것을 컨트롤러에서 가게함. 
	 * 
	 * 		Update 2024.02.23 by pdg 
	 * 		 1. 상품 선택할때 세션에 저장되는 기능 추가 .
	 * 
	 * 		<<2024.02.26 by pdg >>
	 * 		 1. 검색기능
	 * 		 2. testProductDisplay =>ProductDisplay 
	 * 		 3. page 버튼 클릭할 때마다 환영합니다 메세지 뜨는 문제 해결 
	 * 		 4. query 를 searchCondition 으로 바꿈.
	 * 		 5. (검색 판매자이름등으로 검색조건을 만들수는 있으나 사과만 팔기때문에 category 필요없음.-> Discard
	 * 
	 *-------------------------------------- 
	 */
	@Autowired
	ProductListDaoService service;
	
	// 선택된 상품 세션 저장
	// saveProductInfo 는 uProduct2.js 에서 call 하여 쏴주는 request body 정보를 session 에 저장함. 
	@PostMapping("saveProductInfo")
	public void saveProductInfo(@ModelAttribute("product_code") String product_code,
								@ModelAttribute("product_name") String product_name,
								@ModelAttribute("price") 		String price,
								@ModelAttribute("origin") 		String origin,
								@ModelAttribute("size") 		String size,
								@ModelAttribute("weight") 		String weight,
								@ModelAttribute("product_qty") 	String product_qty,
								HttpServletResponse response,
								HttpSession session ) 
								throws Exception{
								
		// *** START Message***
		System.out.println("**<<ProductListController @POST:[saveProductInfo]>>**");
		
		// Session save
		session.setAttribute("product_code",	product_code);	
		session.setAttribute("product_name",	product_name);	
		session.setAttribute("price",		 	price		);
		session.setAttribute("origin", 			origin		);	
		session.setAttribute("size",			size		);
		session.setAttribute("weight",			weight		);	
		session.setAttribute("product_qty",		product_qty	);	
		System.out.println(">> Product information session saved");
		
		//JS response
		PrintWriter out = response.getWriter();
		out.print("sessionSaved");
		
		System.out.println(">> origin session value:"+session.getAttribute("origin"));
	}
	
	//paging 기능
	@GetMapping("ProductDisplay")
	public String ProductDisplay(HttpServletRequest request,
								 HttpSession session,
							   	 Model model
							   	//@ModelAttribute("pageNum") String pageNum
								) throws Exception{
		// *** START Message***
		System.out.println("**<<ProductListController @POST:[ProductDisplay]>>**");
				
		//총 product 개수
		int pcnt = service.productCntDao(); 
		
		// 첫 페이지 버튼 
		String pageNum ="1"; 
		if(pcnt !=0) {// current page 를 받아옴. 
			if(request.getParameter("pageNum") != null) {
				pageNum = request.getParameter("pageNum");
			}
			
		//  검색 조건 
		String searchQuery = "product_name"; // 기본값은 상품이름으로 찾기
		
		// 검색 내용
		String searchContent = "";
		if(request.getParameter("searchContent")!= null) {
			searchContent = request.getParameter("searchContent");
		}
		
		// 정렬 조건 ( 첫페이지는 애플랭킹순)
		String sortingOption = "productRank";
		if(request.getParameter("sortingOption")!= null) {
			sortingOption = request.getParameter("sortingOption");
		}
		
		// 현재페이지 <- request 로 받음. 
		int currentPage= Integer.parseInt(pageNum);
		
		//한페이지에 나타낼 상품개수
		int pageSize = 8;
		
		// 해당 페이지에서 출력할 첫번째 상품코드 ex) 3페이지에서 보여줄 첫상품코드 : (3-1)*8+1 = 17
		int startProduct = (currentPage-1)*pageSize +1;
		
		// total page number calc
		int pageCount = pcnt / pageSize + (pcnt%pageSize ==0? 0:1);
		// 한페이지에 보여줄 페이지 블럭 : 페이지버튼의 개수
		int pageBlock = 2;
		
		// Start page 
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		
		// End page can be greater than the total page. 
		int endPage = startPage + pageBlock -1;
		
		//Last page to equalize with the total page count. 
		model.addAttribute("pcnt"			,pcnt);
		model.addAttribute("currentPage"	,currentPage);
		model.addAttribute("pageSize"		,pageSize);
		model.addAttribute("startProduct"	,startProduct);
		model.addAttribute("pageCount"		,pageCount);
		model.addAttribute("pageBlock"		,pageBlock);
		model.addAttribute("startPage"		,startPage);
		model.addAttribute("endPage"		,endPage);
		
		// Parameter check
		System.out.println(">> searchContent :" + searchContent);
		System.out.println(">> sortingOption :" + sortingOption);
		
		// ServiceDao Call
		List<ProductListDto> productList = 
							service.productListDao(	searchQuery, 	// 검색조건
													searchContent,	// 검색내용
													sortingOption,	// 정렬조건
													startProduct,	// 페이지 첫상품
													pageSize);		// 한페이지당 상품수
		
		model.addAttribute("productList", productList);

		}// pcnt !=0 end
		return "/ProductPart/uProductList";
		
	}// ProductDisplay End

}//PRODCUT LIST CONTROLLER END



