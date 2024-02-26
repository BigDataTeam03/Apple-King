package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("first_check")
public class ProductListController {
	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT LIST)
	 * Author : PDG, KBS, LS, Diana
	 * Date : 2024.02.21
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
	 * 
	 *-------------------------------------- 
	 */
	@Autowired
	ProductListDaoService service;
	
	
	
	//paging 기능
	@GetMapping("/ProductDisplay")
	public String ProductDisplay(HttpServletRequest request,
							   	 Model model
							   	// @ModelAttribute("first_check") String first_check
			) throws Exception{
		
		//총 product 개수
		int pcnt = service.productCntDao(); 
		
		// 첫 페이지 버튼 
		String pageNum ="1"; 
		if(pcnt !=0) {
			if(request.getParameter("pageNum") != null) {
				pageNum = request.getParameter("pageNum");
			}
			
		//  검색 조건
		String searchQuery = "product_name";
		if(request.getParameter("query") != null) {
			searchQuery = request.getParameter("query");
		}
		
		// 검색내용
		String searchContent = "";
		if(request.getParameter("searchContent")!= null) {
			searchContent = request.getParameter("searchContent");
		}
		
		// 현재페이지
		int currentPage= Integer.parseInt(pageNum);
		
		//한페이지에 나타낼 상품개수
		int pageSize = 8;
		
		// 해당 페이지에서 출력할 첫번째 상품코드 ex) 3페이지에서 보여줄 첫상품코드 : (3-1)*8+1 = 17
		int startProduct = (currentPage-1)*pageSize +1;
		
		// total page number calc
		int pageCount = pcnt / pageSize + (pcnt%pageSize ==0? 0:1);
		// 한페이지에 보여줄 페이지 블럭 : 페이지버튼의 개수
		int pageBlock = 2;
			
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage + pageBlock -1;
		//End page can be greater than the total page. 
		//Last page to equalize with the total page count. 
		
		model.addAttribute("pcnt"			,pcnt);
		model.addAttribute("currentPage"	,currentPage);
		model.addAttribute("pageSize"		,pageSize);
		model.addAttribute("startProduct"	,startProduct);
		model.addAttribute("pageCount"		,pageCount);
		model.addAttribute("pageBlock"		,pageBlock);
		model.addAttribute("startPage"		,startPage);
		model.addAttribute("endPage"		,endPage);
		List<ProductListDto> productList = service.productListDao(searchQuery, searchContent, startProduct, pageSize);
		model.addAttribute("productList", productList);
		}// pcnt !=0 end
		return "/ProductPart/uProductList";
	}// testProductDisplay End
}//PRODCUT LIST CONTROLLER END

