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
	public String qtyCount(HttpServletRequest request, Model model, HttpServletResponse response, HttpServletRequest session) throws Exception {
				//String product_qty = (String) session.getAttribute("product_qty");
	System.out.println(" 컨트롤러 등장");
				String product_qty = "5";
				int product_qtyInt = Integer.parseInt(product_qty);
				String cart_qty = request.getParameter("quantity");
				int cart_qtyInt = Integer.parseInt(cart_qty);
				String cart_code = request.getParameter("cartCode");
				System.out.println(" 상품 수량 선택수량 카트코드" + product_qty +cart_qty+ cart_code);
					//  상품 재고보다 변경할 수량이 적을 때에만 실행
				 if (cart_qtyInt <= product_qtyInt) {
					 service.updateqty(cart_qty,cart_code);
				        System.out.println(" if 문 입장");
				    }else {
				   System.out.println(" 실패");
						return"실패";
					}
				 System.out.println(" 성공");
			return "수량변경";
			
		
	
}
}