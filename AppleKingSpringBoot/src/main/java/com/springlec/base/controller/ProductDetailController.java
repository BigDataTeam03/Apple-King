package com.springlec.base.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductDetailDaoService;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductDetailController {

	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT DETAIL)
	 * Author : LS, dk
	 * Date : 2024.02.22
	 * Update : 2024.02.22 by LS, DK 
	 * 		 1. 기존의 uProductList 를 SpringBoot version 으로 변환. 
	 * 		 2. 상품상세페이지를 위하여 productDetailDAO 를 만들고 
	 * 			해당 상품의 상세정보를 product_code를 통해 컨트롤러에서 가게함. 
	 *-------------------------------------- 
	 */
	
	@Autowired
	ProductDetailDaoService service;
	
	@GetMapping("/productDetail")
	public String productDetailDisplay (HttpServletRequest request, Model model) throws Exception{
		List<ProductListDto> listDao = service.productDetailDao(request.getParameter("product_code"));
		model.addAttribute("listDao", listDao);
		System.out.println("상품목록 :" + listDao);
		return "uProductDetail";
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}//PRODUCT DETAIL CONTROLLER END
