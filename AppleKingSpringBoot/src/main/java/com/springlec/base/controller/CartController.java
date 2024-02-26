package com.springlec.base.controller;

import java.io.PrintWriter;
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
	 *Update : 2024.02.23 KBS
	 *      1. 수량 업데이트 기능 완료
	 *      2. 장바구니 삭제 기능 완료
	 *      3. 목록으로 돌아가기 완료
	 *      4. 구매페이지로 이동 완료
	 *       
	 */
	@Autowired
	CartDaoService service;
	
	@PostMapping("/showCartList")
	
	//Json 형식의 데이터를 받기위한 어노테이션
	 public ResponseEntity<List<CartDto>> showcartList(HttpServletRequest session, Model model,
			 										   HttpServletResponse response  ) throws Exception {
	        // 세션에서 유저 아이디 값 가져오기
	        String userId = (String)session.getAttribute("userId");
		 System.out.println("세션아이디값" + userId);
			// Json 값 한글 지정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//임시 
			//String userId = "pdg";
			
	        // 유저 아이디에 해당하는 장바구니 리스트 가져오기
	        List<CartDto> cartList = service.cartlistdao(userId);
	        	
	        return ResponseEntity.ok().body(cartList);
	    }
	@GetMapping("/ListView")
	public String gomone() throws Exception{

		return "cartList/ListView";
	}
	
	@PostMapping("/qtyUpdate")
	public  void qtyCount(HttpServletRequest request, Model model,
						  HttpServletResponse response, HttpServletRequest session) throws Exception {
		String product_qty = (String) session.getAttribute("product_qty");
		System.out.println("세션으로 받아온 수량값" +product_qty);
		//response 를 보내기 위한 선언
		PrintWriter out = response.getWriter();
		//변수지정
		//String product_qty = "5";
		int product_qtyInt = Integer.parseInt(product_qty);
		String cart_qty = request.getParameter("quantity");
		int cart_qtyInt = Integer.parseInt(cart_qty);
		String cart_code = request.getParameter("cartCode");
		System.out.println(" 상품 수량 선택수량 카트코드" + product_qty +cart_qty+ cart_code);
					//  상품 재고보다 변경할 수량이 적을 때에만 실행
				 if (cart_qtyInt <= product_qtyInt) {
				        System.out.println(" if 문 입장");
				        System.out.println(" 성공");
				        
				    service.updateqty(cart_qty,cart_code);
				   
				    out.print("성공");
				    }else  {
				    	   out.print(1);	
					}
}
	// 카트 상품 삭제 메소드
	@PostMapping("/deleteCart")
	public void deletecart( HttpServletRequest request, Model model,
							HttpServletResponse response) throws Exception {
		// response 를 보내기 위한 out 선언
		PrintWriter out = response.getWriter();
		// 혹시 모르는 한글깨짐 방지
		response.setContentType("text/html;charset=UTF-8");
		// 배열에 들어있는 값을 받아온다 
		String [] selected = request.getParameterValues("code");
		// 반복문을 돌려서 만약 1개가 선택되었으면 배열의 0번째 [0]   Ex)cart_code = 1 를 삭제함
		// 만약 3개를 선택하고 삭제한다면 배열은 0,1,2 칸이 있고 길이는 3 이므로 3번 반복한다,
		// service 에 보내지는 데이터는 배열의 0번칸 1번칸 2번칸이 전부 보내진다.
		for(int i = 0; i < selected.length; i++) {
				service.deleteCart(selected[i]);
		}
		out.print("성공");
	} 
		
	// 장바구니에 담긴 상품 구매 메소드
	@PostMapping("/purchaseCart")
	public String purchaseCart(HttpServletRequest request, 
							   HttpSession session) throws Exception {
		
		// 배열에 들어있는 값을 받아온다 (선택한 상품들)
		String [] purchase = request.getParameterValues("cartCheckList");
		// 내가 선택한 상품을 세션에 넣어줌 ( 구매 확정을 할 때 사용한다 )	
		session.setAttribute("cartPurchase", purchase);
		return "UserCheckPart/purchaseView";
	}

	// 상품목록으로 돌아가기 버튼 기능
	@GetMapping("/goProductList")
	public String goback() throws Exception {
		return"ProductPart/uProductList";
	}
	
}
