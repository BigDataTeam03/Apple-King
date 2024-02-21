package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.model.MemberDto;
import com.springlec.base.service.MemberDaoService;

@Controller
public class userController {
	/*--------------------------------------
	 * Description: Apple King Controller (USER)
	 * Author : PDG, KBS, LS, Diana
	 * Date : 2024.02.21
	 * Update :
	 * 		Update 2024.02.21 by PDG
	 * 		 1. 기존의 Appleking 을 SpringBoot version 으로 변환. 
	 * 		 2. user login 을 위하여 member DAO 를 만들고 login page 생성한것을 컨트롤러에서 가게함. 
	 *-------------------------------------- 
	 */
	
	 @Autowired //service wired
	 MemberDaoService service;
	 
	 @GetMapping("/")
	 public String userLogin() throws Exception{
		 System.out.println(">> userController START ");
		 //List<MemberDto> memberDto =  service.memberListDao();
		 
		 return "login_view";
	 }
	
}
