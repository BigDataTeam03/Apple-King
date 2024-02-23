package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.MyInfoDto;
import com.springlec.base.service.MyInfoDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class myInfoController {

	/*--------------------------------------
	 * Description: myInfo 컨트롤러 
	 * Author :  DK 
	 * Date : 2024.02.23
	 * ----------------------------------------
	 * Update : 2024.02.23 DK
  	 *   1. MyInfoDaoService를 사용하여 사용자와 관련된 회원정보를 가져옴
     *   2. 세션에서 사용자 userId 를 가져와 해당하는 myInfo 를 검색. 
     *   3. Controller 를 하나 사용하기 위해서 myPageController 에 "service2" 추가 --> 에러걸림.짜증남.  
	 *-------------------------------------- 
	 */

	@Autowired 
	MyInfoDaoService service;
	
	@GetMapping("/myInfo")
	public String myInfo (HttpSession session, HttpServletRequest request, Model model) throws Exception{
	//(TEMPORARY)set user's Id after they have logged in.
	String userId = "pdg";
	
	//Null handling for userId. 
	if( session.getAttribute("userId") != null ){
		userId = (String)session.getAttribute("userId");
	}
	
	//get the user's order list using orderDao.
	List<MyInfoDto> myInfo = service.MyInfoDao(userId);
	model.addAttribute("myInfo", myInfo);
	
	//save user's info using session. 
    session.setAttribute("userId", userId);
		
	return "myInfo";	
	}
	
	
	
	
	
	
	
	

}//CONTROLLER END
