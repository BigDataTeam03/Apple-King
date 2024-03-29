package com.springlec.base.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.model.ProductListDto;
import com.springlec.base.model.ReviewDto;
import com.springlec.base.service.ProductDetailDaoService;
import com.springlec.base.service.ProductListDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductDetailController {
	/*--------------------------------------
	 * Description: Apple King Controller (PRODUCT DETAIL)
	 * Author : LS, dk ,pdg
	 * Date : 2024.02.22
	 * Update : 2024.02.22 by LS, DK 
	 * 		 1. 기존의 uProductList 를 SpringBoot version 으로 변환. 
	 * 		 2. 상품상세페이지를 위하여 productDetailDAO 를 만들고 
	 * 			해당 상품의 상세정보를 product_code를 통해 컨트롤러에서 가게함. 
	 *-------------------------------------- 
	 * Update : 2024.02.23 by KBS
	 * 		1. 장바구니로 인서트,업데이트 기능 추가함
	 * 
	 * <<2024.02.27 by pdg , ls>
	 * 		1.상품 체크 관련 주석 수정. 
	 * 		2.login 안된 상태에서는 장바구니 담기를 누르면 로그인페이지로 이동하는 기능
	 */
	
	//DI
	@Autowired
	ProductDetailDaoService service;
	
	// 상품 상세페이지 
	@GetMapping("/productDetail")
	public String productDetailDisplay (HttpServletRequest 	request,
										HttpSession 		session,
										Model 				model) 
									    throws Exception{
		
		// *** START Message***
		System.out.println("**<<ProductDetailController @GET:[productDetailDisplay]>>**");
		
		// Session 정보 불러옴.
		String product_code = (String)session.getAttribute("product_code");
		ProductListDto listDao = service.productDetailDao(product_code);
		
		//model add
		model.addAttribute("listDao", listDao);
		System.out.println(">> 상품목록 :" + listDao);
		System.out.println(">> 상품재고수 :" + Integer.toString(listDao.getProduct_qty()));
		
		return "/ProductPart/uProductDetail";
	}// END
	
	// 장바구니로 담기
	@PostMapping("/cartInsert")
	public String insertCart(	HttpSession session,
								HttpServletRequest request,
								HttpServletResponse response,
								Model model
								) throws Exception {
		// *** START Message***
		System.out.println("**<<ProductDetailController @POST:[insertCart]>>**");
		
		// 세션값 받음
		String product_code  = (String)session.getAttribute("product_code");
		String userId 		 = (String)session.getAttribute("userId");
		Integer cart_qty     = Integer.parseInt(request.getParameter("cart_qty"));
		
		if(userId ==null) {
			return "/UserCheckPart/login_view";
		}
		
		int cart_qty_int = cart_qty != null ? (int) cart_qty: 0;
		String product_name  = (String)session.getAttribute("product_name"); 
		System.out.println(">>  product code : " + product_code +" \n"
						  +">>  user Id		 : " + userId + "\n"
					      +">>  product name : " + product_name + "\n"
					      +">>  상품 선택 개수  : " + cart_qty_int );
		
		// 장바구니에 이미 있는 상품인지 체크 
		String checkAlreadyInCart = service.checkItem(userId, product_code);
		System.out.println(">> 장바구니에 있는 상품인지 체크 :"+ checkAlreadyInCart);
		if( !checkAlreadyInCart.equals("0")) { // cart 에 상품이 이미 있을때 
			System.out.println(">> 장바구니에 삽입합니다.");
			service.updateCart(userId, product_code, cart_qty);
		}else { // cart 에 상품이 없을때 
			System.out.println(">> 장바구니 개수를 수정합니다.");
			service.insertCart(userId, product_code, cart_qty);
		}
		return "CartPart/cartList";
	}//insertCart END
}//PRODUCT DETAIL CONTROLLER END
