package com.springlec.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.MyInfoDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.service.MyInfoDaoService;
import com.springlec.base.service.OrderDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class myPageController {
	
	/*--------------------------------------
	 * Description: myPage 컨트롤러
	 * Author :  DK, LS 
	 * Date : 2024.02.22
	 * ----------------------------------------
	 * Update : 2024.02.22 DK, LS 
	 * 		1. OrderDaoService를 사용하여 사용자와 관련된 주문 목록을 가져옵
	 *      2. 세션에서 사용자 정보를 가져와서 userId, userName, userRank 및 regDate를 검색
	 * 
	 * <<2024.02.26 by pdg>>
	 * 	1.userRank 를 불러올때 세션에는 객체를 저장해야함  Integer 로 변환 하여 문제해결  
	 *-------------------------------------- 
	 */

	@Autowired 
	OrderDaoService service;

	
	@GetMapping("/myPage")
    public String myPage(HttpSession session, HttpServletRequest request, Model model) throws Exception {
	//(TEMPORARY)set user's info after they have logged in.
	String userId = "pdg";
	String userName = "박동근";
	Integer    userRank = 1;
	String regDate = "2023.01.20";
	
	//Null handling for userId, userName, userRank and regDate.
	if( session.getAttribute("userId") != null ){
		userId = (String)session.getAttribute("userId");
	}
	if( session.getAttribute("userName") != null ){
		userName = (String)session.getAttribute("userName");
	}
	
	//Optional null 처리함. 

	
	if( session.getAttribute("userRank") != null ){
		userRank = (Integer)session.getAttribute("userRank");
		
	}
	if( session.getAttribute("regDate") != null ){
		regDate = (String)session.getAttribute("regDate");
	}
	
	//get the user's order list using orderDao.
	List<OrderDto> orderList = service.OrderDao(userId);
	model.addAttribute("order", orderList);
	
	//save user's info using session. 
    session.setAttribute("userId", userId);
    session.setAttribute("userName", userName);
    

    session.setAttribute("userRank", userRank);
    session.setAttribute("regDate", regDate);
   
    return "/MyPagePart/myPage"; 
    }
	
}//CONTROLLER END
