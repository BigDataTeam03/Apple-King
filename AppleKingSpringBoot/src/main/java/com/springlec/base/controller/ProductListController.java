package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductListController {
	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT LIST)
	 * Author : PDG, KBS, LS, Diana
	 * Date : 2024.02.21
	 * Update :
	 * 		Update 2024.02.21 by LS, DK 
	 * 		 1. 기존의 Appleking 을 SpringBoot version 으로 변환. 
	 * 		 2. 상품리스트를 위하여 product DAO 를 만들고 product page 생성한것을 컨트롤러에서 가게함. 
	 *-------------------------------------- 
	 */
	
	@Autowired
	ProductListDaoService service;
	
	@GetMapping("/testProductDisplay")
	public String testProductDisplay(HttpServletRequest request, Model model)
			
			throws Exception{
		
		int pcnt = service.productCntDao();
		if(pcnt !=0) {
			String pageNum ="1";
			if(request.getParameter("pageNum") != null) {
				pageNum = request.getParameter("pageNum");
			}
		String query = "product_name";
		if(request.getParameter("query") != null) {
			 query = request.getParameter("query");
		}
		
		String searchContent = "";
		if(request.getParameter("searchContent")!= null) {
			searchContent = request.getParameter("searchContent");
		}
			int currentPage= Integer.parseInt(pageNum);
			int pageSize = 8;
			// 해당 페이지에서 출력할 첫번째 상품의 순번 :ex) 3페이지에서 보여줄 상품의 첫번째 코드는 17
			int startRow = (currentPage-1)*pageSize +1;
			// total page number calc
			int pageCount = pcnt / pageSize + (pcnt%pageSize ==0? 0:1);
			// 한페이지에 보여줄 페이지 블럭 : 페이지버튼의 개수
			int pageBlock = 2;
				
			int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
			int endPage = startPage + pageBlock -1;
			//End page can be greater than the total page. 
			//Last page to equalize with the total page count. 
			
			model.addAttribute("pcnt",pcnt);
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("pageSize",pageSize);
			model.addAttribute("startRow",startRow);
			model.addAttribute("pageCount",pageCount);
			model.addAttribute("pageBlock",pageBlock);
			model.addAttribute("startPage",startPage);
			model.addAttribute("endPage",endPage);
			List<ProductListDto> productList = service.productListDao(query, searchContent, startRow, pageSize);
			model.addAttribute("productList", productList);
			
		}// pcnt !=0 end
		return "uProductList";
	}// testProductDisplay End

	
}//PRODCUT LIST CONTROLLER END

