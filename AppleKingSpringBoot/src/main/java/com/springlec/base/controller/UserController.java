package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.model.MemberDto;
import com.springlec.base.service.MemberDaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	/*--------------------------------------
	 * Description: User controller - LogIn, signUp function 
	 * Author : PDG
	 * Date : 2024.02.21
	 * Update :
	 * 		Update 2024.02.21 by PDG
	 * 		 1. 기존의 Appleking 을 SpringBoot version 으로 변환. 
	 * 		 2. user login 을 위하여 member DAO 를 만들고 login page 생성한것을 컨트롤러에서 가게함. 
	 * 
	 * 
	 *-------------------------------------- 
	 */
	
	 @Autowired //service wired
	 MemberDaoService memberService;
	 
	 @GetMapping("/")
	 public String userLogin() throws Exception{
		 System.out.println(">> userLogin START ");
		 return "/UserCheckPart/login_view";
	 }
	 @PostMapping("/signUpStart.do")
	 public String userSignUp() throws Exception {
		 System.out.println(">> userSignUp.do START ");
		 return "/UserCheckPart/signup_view";
	 }
	 @GetMapping("cGoHome.do")
	 public String cGoHome() throws Exception {
		 System.out.println(">> cGoHome.do START");
		 return "uProductList";
	 }
	 
	 @PostMapping("loginProcess")
	 public String loginProcess(@RequestParam String userId, 	 // userID 
			 								  String userPw, 	 // user Password
			 								  String save_check, // user ID save
			 								  String first_check // 첫방문인지 확인. 
			 								   ) throws Exception {
		 
		 boolean first_check_bool = false;// 첫 방문이아니라  뒤로가기등으로 들어온 사람.
		 if(first_check == null) {
			 first_check_bool =true; // 첫방문인 사람. 
		 }
		 
		 System.out.println(">> loginProcess START");
		 System.out.println(">> userId : "  	+ userId + 
				 			  "\n userPw :" 	+ userPw +
				 			  "\n save_check : "+ save_check+
				 			  "\n first_check: "+ first_check
				 );
		 if( !memberService.membeChkDao(userId,userPw).equals("0")) {
			 System.out.println("회원입니다.");
			 return "uProductList";
		 } else {
			 System.out.println("정보가 불일치 합니다. ");
			 return "/UserCheckPart/login_view";
		 }
		
	 }

}
