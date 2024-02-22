package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.dao.OrderDao;
import com.springlec.base.model.OrderDto;
import com.springlec.base.service.OrderDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class myPageController {

	@Autowired
	OrderDaoService service;
	
	
	@GetMapping("/myPage")
    public String myInfo(HttpSession session, HttpServletRequest request, Model model) throws Exception {
//		String userId = (String)   session.getAttribute("userId");
//		String userName = (String) session.getAttribute("userName");
//		int userRank =  		   session.getAttribute("userRank");
//		String regDate = (String)  session.getAttribute("regDate");
		String cust_id = "pdg";
		String userName = "박동근";
		int    userRank = 1;
		String regDate = "2023.01.20";
		
		if( request.getParameter("cust_id") != null ){
			cust_id = request.getParameter("cust_id");
		}
		
		List<OrderDto> orderList = service.OrderDao(cust_id);
		model.addAttribute("order", orderList);
		
        session.setAttribute("cust_id", cust_id);
        session.setAttribute("userName", userName);
        session.setAttribute("userRank", userRank);
        session.setAttribute("regDate", regDate);
       
        return "myPage"; 
    }
	
	
	
	
}
