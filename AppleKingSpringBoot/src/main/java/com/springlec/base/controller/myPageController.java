package com.springlec.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.MyInfoDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;
import com.springlec.base.service.MyInfoDaoService;
import com.springlec.base.service.OrderDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class myPageController {
	
	/*--------------------------------------
	 * Description: myPage 컨트롤러
	 * Author :  DK, LS ,pdg
	 * Date : 2024.02.22
	 * ----------------------------------------
	 * Update : 2024.02.22 DK, LS 
	 * 		1. OrderDaoService를 사용하여 사용자와 관련된 주문 목록을 가져옵
	 *      2. 세션에서 사용자 정보를 가져와서 userId, userName, userRank 및 regDate를 검색
	 * 
	 * <<2024.02.26 by pdg>>
	 * 	1.userRank 를 불러올때 세션에는 객체를 저장해야함  Integer 로 변환 하여 문제해결
	 *   
	 * <<2024.02.26 by DK>>
	 * 	1. Get the user's ID, NAME, RANK, and REGDATE by session
	 * 	2. Use pre-made memberList to get user's information after update.
	 * 
	 * <<2024.02.07 by DK>>
	 * 1. Get the user's review history. 
	 *-------------------------------------- 
	 */

	@Autowired 
	OrderDaoService service;
	
	@GetMapping("/MyPage")
    public String myPage(HttpSession session, HttpServletRequest request, Model model) throws Exception {
	//get session for the user's ID, NAME, RANK and REGDATE
	 String userId    = 	(String)session.getAttribute("userId");
	 String userName  = 	(String)session.getAttribute("userName");
	 Integer userRank = 	(Integer)session.getAttribute("userRank");
	 String regDate   = 	(String)session.getAttribute("regDate");
	 

	//get the user's order list using orderDao.
	List<OrderDto> orderList = service.OrderList(userId);
	MemberDto memberList = service.memberInfoDao(userId);
	model.addAttribute("orderList", orderList);
	model.addAttribute("memberList", memberList);
	
	
	//get the user's review history using orderDao.
	List<ReviewDto> reviewList = service.ReviewList(userId);
	model.addAttribute("reviewList", reviewList);
	
    return "/MyPagePart/myPage"; 
    }
	
	
	
}//CONTROLLER END
