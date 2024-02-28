package com.springlec.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 *  
	 *  <<2024.02.28 by pdg>
	 *  1. 결제 버튼 기능 활성화
	 *  2. 직접결제에서 수량 변경할 때 총상품금액 바로 변경되는 기능  
	 *  3. 즉시 결제기능 완성
	 *  4. 장바구니 결제 기능 
	 *  
	 */
	
	@Autowired
	PurchaseDaoService service;
	
	@Autowired
	OrderDaoService service_order;
	
	@PostMapping("purchaseComplete")
	public String purchaseComplete(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		// ***START massage ***
		System.out.println("**<<PurchaseController @Post: purchaseComlete>>**");
		Map<String, String> orderInfo =(Map<String, String>) session.getAttribute("orderInfo");
		// Request body information
		String payment_method	= request.getParameter("payment_method");
		Integer used_point		= Integer.parseInt(request.getParameter("used_point"));
		Integer order_qty		= Integer.parseInt(request.getParameter("order_qty"));
		
		System.out.println(">> payment_method : "+ payment_method	+ "\n"+
						   ">> used_point 	  : "+ used_point  		+ "\n"+
						   ">> order_qty 	  : "+ order_qty		
				);
		// orderinfo -> order table 에 insert 
		service_order.orderInsertDao(
		orderInfo.get("userId"),
		orderInfo.get("userName"),
		Integer.parseInt(orderInfo.get("product_code")),
		orderInfo.get("product_name"),
		Integer.parseInt(orderInfo.get("price")),
		payment_method,
		used_point,
		order_qty);
		return "redirect:/MyPage";
	}
	
	//즉시결제시 구매자 정보 + 결제 정보 불러오기 
	@PostMapping("/directPurchase")
	public String directPurchase (HttpSession session, HttpServletRequest request, Model model) throws Exception{
		// *** START massage ***
		System.out.println("**<< PurchaseController @Post : directPurchase>>**");
		// 구매자 정보(session 값 fetch) 
		String userId 	= (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		if (userId ==null) {
			return "/UserCheckPart/login_view";
		}
		// 결제 할 상품 정보. (즉시결제) 
		String product_code = (String)session.getAttribute("product_code");
		String product_name = (String)session.getAttribute("product_name");
		String price 		= (String)session.getAttribute("price"); 
		String origin		= (String)session.getAttribute("origin"); 
		String size			= (String)session.getAttribute("size"); 
		String weight		= (String)session.getAttribute("weight"); 
		String product_qty	= (String)session.getAttribute("product_qty"); 
		
		
		// 결제정보를 만든다. 
		Map<String, String> orderInfo = new HashMap<String, String>();
		
		orderInfo.put("userId",userId);
		orderInfo.put("userName",userName);
		orderInfo.put("product_code",product_code);
		orderInfo.put("product_name",product_name);
		orderInfo.put("price",price);
		orderInfo.put("origin",origin);
		orderInfo.put("size",size);
		orderInfo.put("weight",weight);
		orderInfo.put("product_qty",product_qty);
		model.addAttribute("orderInfo", orderInfo);
		//구매 정보를 세션에 저장. 
		session.setAttribute("orderInfo", orderInfo);
		
		return "/ProductPart/purchase";	
		}// DirectPurchase END
	@PostMapping
	public String cartPurchase() throws Exception {
		return "/ProductPart/purcase";
	}
}
