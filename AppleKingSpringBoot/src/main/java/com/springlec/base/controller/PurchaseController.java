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
import com.springlec.base.service.PurchaseDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseController {
	
	/*--------------------------------------
	 * Description: cartList 컨트롤러
	 * Author :  LS
	 * Date : 2024.02.27
	 *       
	 * Update 
	 * 	<<2024.02.27 by pdg>>
	 * 	1. 결제하기 버튼을 눌렀을 때 -> Order table 에 insert 하는 기능
	 *  2. 결제하기 위해서 해당 결제정보를 불러오는 기능 ( 구매자, 물건정보, 등등을 orderDto 로 전환하여 사용할것.)
	 */
	
	@Autowired
	PurchaseDaoService service;
	@PostMapping("purchaseComplete")
	public String purchaseComplete() throws Exception {
		
		return "";
	}
	
	// 역할. 구매자 정보 불러오기? + 결제 정보 불러오기 
	@GetMapping("/purchase")
	public String PurchaserInfoDao (HttpSession session, HttpServletRequest request, Model model) throws Exception{
		
		String userId 	= (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		// 결제 할 상품 정보. (즉시결제) < -session 불러옴
		String product_code = (String)session.getAttribute("product_code");
		String product_name = (String)session.getAttribute("product_name");
		String price 		= (String)session.getAttribute("price"); 
		String origin		= (String)session.getAttribute("origin"); 
		String size			= (String)session.getAttribute("size"); 
		String weight		= (String)session.getAttribute("weight"); 
		String product_qty	= (String)session.getAttribute("product_qty"); 
		
		MemberDto memberList = service.memberInfoDao(userId);
		model.addAttribute("memberList", memberList);
			
		return "/ProductPart/purchase";	
		}
}
