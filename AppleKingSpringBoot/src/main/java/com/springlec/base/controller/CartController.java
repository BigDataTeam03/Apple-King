package com.springlec.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springlec.base.model.CartDto;
import com.springlec.base.service.CartDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller
public class CartController {

	/*--------------------------------------
	 * Description: cartList 컨트롤러
	 * Author :  KBS
	 * Date : 2024.02.22
	 * Update : 2024.02.22 KBS 
	 * 		1. 카트 리스트 출력기능 추가
	 *      2. 수량업데이트 기능 추가중(수정필)
	 *-------------------------------------- 
	 */
	@Autowired
	CartDaoService service;
	
	@PostMapping("/showCartList")
	
	//Json 형식의 데이터를 받기위한 어노테이션
	 public ResponseEntity<List<CartDto>> showcartList(HttpServletRequest session, Model model, HttpServletResponse response  ) throws Exception {
	        // 세션에서 유저 아이디 값 가져오기
	        //String userId = (String)session.getAttribute("userId");
		
			// Json 값 한글 지정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//임시 
			String userId = "sumin123";
			
	        // 유저 아이디에 해당하는 장바구니 리스트 가져오기
	        List<CartDto> cartList = service.cartlistdao(userId);
	        
	        return ResponseEntity.ok().body(cartList);
	    }
	@GetMapping("/ListView")
	public String gomone() throws Exception{
		return "cartList/ListView";
	}
	
	@PostMapping("/qtyUpdate")
	public String qtyCount(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
				String qty = request.getParameter("quantity");
				String code = request.getParameter("cartCode");
				
				service.updateqty(qty,code);
			return "/showCartList";
		
	
}
}