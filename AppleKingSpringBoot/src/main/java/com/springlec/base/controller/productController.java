package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductListDaoService;

@Controller
public class productController {
	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT)
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

	@GetMapping("/productList")
	 public String productList(Model model) throws Exception {
		List<ProductListDto> list = service.listDao();
			model.addAttribute("list", list );
			return "productList";
		}
	
	
	
	
}
