package com.springlec.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.PurchaseDto;
import com.springlec.base.service.OrderDaoService;
import com.springlec.base.service.PurchaseDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseController {
	
	/*--------------------------------------
	 * Description: 구매 결제 페이지 컨트롤러
	 * Author :  PDG, LS
	 * Date : 2024.02.27
	 *       
	 * Update 
	 * 	<<2024.02.27 by pdg>>
	 * 	1. 결제하기 버튼을 눌렀을 때 -> Order table 에 insert 하는 기능
	 *  2. 결제하기 위해서 해당 결제정보를 불러오는 기능 ( 구매자, 물건정보, 등등을 orderDto 로 전환하여 사용할것.)
	 *  3. 즉시 결제와 장바구니 결제를 나누어 작성함 ( directPurchase, cartPurchase)
	 *  4. 결제자 정보는 굳이 다오를 사용하지않아도 세션값에서 충분히 표기 할수있으므로 service 를 사용하지 않도록 함. 
	 */
	
	//@Autowired
	//PurchaseDaoService service;
	
	@Autowired
	OrderDaoService service_order;
	
	@PostMapping("purchaseComplete")
	public String purchaseComplete() throws Exception {
		return "";
	}
	
	//즉시결제시 구매자 정보 + 결제 정보 불러오기 
	@GetMapping("/directPurchase")
	public String PurchaserInfoDao (HttpSession session, HttpServletRequest request, Model model) throws Exception{
		
		// 구매자 정보(session 값 fetch) 
		String userId 	= (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		// 결제 할 상품 정보. (즉시결제) 
		String product_code = (String)session.getAttribute("product_code");
		String product_name = (String)session.getAttribute("product_name");
		String price 		= (String)session.getAttribute("price"); 
		String origin		= (String)session.getAttribute("origin"); 
		String size			= (String)session.getAttribute("size"); 
		String weight		= (String)session.getAttribute("weight"); 
		String product_qty	= (String)session.getAttribute("product_qty"); 
		
//		service_order.orderInsertDao(
//						userId,
//						userName,
//						product_code,
//						product_name,
//						price,
//						product_qty
					  //order_code, --> auto increased
//					  payment_method,
//					  used_point,
//					  order_qty,
//					  orderdate,
//					  soldout
//					  );
		
		return "/ProductPart/purchase";	
		}// DirectPurchase END
	@PostMapping
	public String cartPurchase() throws Exception {
		return "/ProductPart/purcase";
	}
}
