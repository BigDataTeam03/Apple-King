package com.springlec.base.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.service.ProductDetailDaoService;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	 * Update : 2024.02.23 by KBS
	 * 		1. 장바구니로 인서트,업데이트 기능 추가함
	 */
	@Autowired
	ProductDetailDaoService service;
	
	@GetMapping("/productDetail")
	public String productDetailDisplay (HttpServletRequest request,
			HttpSession session,
			Model model) throws Exception{
		
		String product_code = (String)session.getAttribute("product_code");
				
		List<ProductListDto> listDao = service.productDetailDao(product_code);
		model.addAttribute("listDao", listDao);
		System.out.println(">> 상품목록 :" + listDao);
		return "/ProductPart/uProductDetail";
	}
	
	// 장바구니로 넣는 메소드
	@PostMapping("/cartInsert")
	public String insertCart(HttpSession session, HttpServletRequest request,
						   HttpServletResponse response, Model model) throws Exception {
		// 세션 받아야함
		String product_code  = session.getAttribute("product_code").toString();
		String cust_id 		 = session.getAttribute("userId").toString();
		int cart_qty         = (int) request.getAttribute("cart_qty");
		//String product_name  = (String)session.getAttribute("product_name"); 
		
		boolean check = service.checkItem(cust_id, product_code);
		if(check == true) {
				service.insertCart(cust_id, product_code, cart_qty);
		}else {
				service.updateCart(cust_id, product_code, cart_qty);
		}
		return "cartList/ListView";
	}//insertCart END
}//PRODUCT DETAIL CONTROLLER END
