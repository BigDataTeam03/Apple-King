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
import com.springlec.base.model.OrderDto;
import com.springlec.base.service.CartDaoService;
import com.springlec.base.service.OrderDaoService;

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
	 * <<2024.02.28 by pdg>>
	 * 	1. session 으로 받아오는 수량값이 내가 상품목록에서 선택했던 상품이었을 경우에만 
	 *     값이 들어가있고 그냥 상품선택없이 top 의 마이페이지 링크를 통해 들어온경우, 혹은
	 *     장바구니에 여러 상품이 존재할때 상품수량은 여러개여야하는데 하나밖에 불러오지않는 이슈가 있다. 
	 *     그래서 세션에서 받아오기보다, 내가 장바구니에서 해당 상품의 스피너를 누를때 그 스피너가 속한 상품 코드를 
	 *     가져와서 직접 db 에서 상품수를 조회해야한다. 
	 *  2. 하지만 이런 문제를 모두 제외하고 먼저 장바구니 결제 기능부터 수행하도록 한다. 
	 *  
	 *       
	 *   
	 */
	 @Autowired
	 CartDaoService service;
	 
	 @Autowired
	 OrderDaoService service_order;
	 
	 
	 //장바구니 목록 
	 @PostMapping("/showCartList") //<-From cart.js 
	 public ResponseEntity<List<CartDto>> showCartList(
			 HttpSession session,
			 HttpServletRequest request,
			 Model model,
			 HttpServletResponse response  ) throws Exception {
		 
	     	// 세션에서 유저 아이디 값 가져오기
	     	String userId = (String)session.getAttribute("userId");
	     	System.out.println(">> 세션아이디값" + userId);
	     	
			// Json 값 한글 지정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
	        // 유저 아이디에 해당하는 장바구니 리스트 가져오기 
	        List<CartDto> cartList = service.cartlistdao(userId);
	        /*
							cart_code,
							cust_id,
							product_code,
							cart_qty,
							product_name,
					        price, 
					        product_image
	         */
	        return ResponseEntity.ok().body(cartList);
	    }
	@GetMapping("/cartList")
	public String cartList() throws Exception{
		return "CartPart/cartList";
	}
	
	@PostMapping("/qtyUpdate")
	public  void qtyCount(HttpServletRequest request,
						  Model model,
						  HttpServletResponse response,
						  HttpServletRequest session) throws Exception {
		
		
		String product_qty = (String) session.getAttribute("product_qty");
		System.out.println(">> 세션으로 받아온 수량값" +product_qty);
		
		//response 를 보내기 위한 선언
		PrintWriter out = response.getWriter();
		int product_qtyInt = Integer.parseInt(product_qty);
		String cart_qty = request.getParameter("quantity");
		int cart_qtyInt = Integer.parseInt(cart_qty);
		String cart_code = request.getParameter("cartCode");
		
		System.out.println(">> 상품 수량 선택수량 카트코드" + product_qty +cart_qty+ cart_code);
		
		// 상품 재고보다 변경할 수량이 적을 때에만 실행
		if (cart_qtyInt <= product_qtyInt) {
			System.out.println(" if 문 입장");
			System.out.println(" 성공");
			service.updateqty(cart_qty, cart_code);
			out.print("성공");
		} else {
			out.print(1);
		}
}
	// 카트 상품 삭제 메소드
	@PostMapping("/deleteCart")
	public void deletecart( HttpServletRequest request, Model model,
							HttpServletResponse response) throws Exception {
		
		System.out.println("**<<CartController @POST : deleteCart >>**");
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
	public ResponseEntity<String> purchaseCart(
			@RequestBody Map<String, Object> requestMap,
			HttpServletRequest request, 
							   HttpSession session) throws Exception {
		
		//*** STARAT message
		System.out.println("**<< CartController @POST : purchaseCart>>**");
		
		// 배열에 들어있는 값을 받아온다 (선택한 상품들)
		//String[] cartCheckList = (String[]) requestMap.get("cartCheckList");
	    List<Map<String, String>> selectedProducts =
	    		(List<Map<String, String>>) requestMap.get("selectedProducts");
	    System.out.println(">> 장바구니에서 선택된 상품 항목(상품코드)");
	    for (Map<String, String> product : selectedProducts) {
	        System.out.println("상품 코드: " + product.get("cart_code"));
	        System.out.println("상품 수량: " + product.get("cart_qty"));
	        
	        Integer cart_code = Integer.parseInt(product.get("cart_code"));
	        Integer cart_qty = Integer.parseInt(product.get("cart_qty"));
	        // cart code 로 알수있는 것 => cart_code, cust_id, product_code, cart_qty
			
	     	//cart code 를 가지고 쿼리를 짜서 결제에 필요한 정보들을 불러도록하자. 
	     	OrderDto orderInfo = service.orderInfoFromCart(cart_code);
	     	// orderInsertDao 에서 필요한것. =>cust_id, name,product_code, product_name, price, payment_method, used_point, order_qty
 			service_order.orderInsertDao(orderInfo.getCust_id(),
 										 orderInfo.getName(),
 										 orderInfo.getProduct_code(),
 										 orderInfo.getProduct_name(),
 										 orderInfo.getPrice(),
 										 request.getParameter("payment_method"),
 										 0,
 										 cart_qty
 									);
 			service.deleteCart(Integer.toString(cart_code));
 			// 결제후에는 장바구니 데이터를 삭제한다. 
 			
 			
	    }
		

		// 내가 선택한 상품을 세션에 넣어줌 ( 구매 확정을 할 때 사용한다 )	
		//session.setAttribute("cartPurchase", cartCheckList);
		
		//구매하기 버튼을 누르면 결제를 실행한다. 
		
		return ResponseEntity.ok("deleteCart");
	}

	// 상품목록으로 돌아가기 버튼 기능
	@GetMapping("/goProductList")
	public String goback() throws Exception {
		return"ProductPart/uProductList";
	}
	
}
