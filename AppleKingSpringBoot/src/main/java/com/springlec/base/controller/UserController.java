package com.springlec.base.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.javaproject.util.CookieManager;
import com.springlec.base.model.MemberDto;
import com.springlec.base.service.MemberDaoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	/*--------------------------------------
	 * Description: User controller - LogIn, signUp function 
	 * Author : PDG
	 * Date : 2024.02.21
	 * Update :
	 * 		Update 2024.02.21 by PDG
	 * o	 1. 기존의 Appleking 을 SpringBoot version 으로 변환. 
	 * o	 2. user login 을 위하여 member DAO 를 만들고 login page 생성한것을 컨트롤러에서 가게함. 
	 * 
	 * 	   	Update 2024.02.23 by PDG
	 * o	 1. DB 에서 조회하여 회원 정보 필요한것 세션에 저장하고 쿠키 메니저 사용하여 쿠키 활성화 시킴. 
	 * o	 2. 로그인아이디와회원아이디 일치하는지 로직 짬. 
	 * o	 3. 정보가 불일치 할경우 불일치함을 페이지에 표시하는 기능. 
	 * o 	 4. admin 일경우 aGoHome 으로 가는 기능 
	 * o	 5. log out 기능 추가
	 *-------------------------------------- 
	 */

	@Autowired // service wired
	MemberDaoService memberService;

	@GetMapping("/")
	public String userLogin() throws Exception {
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
	public String loginProcess(
			@RequestParam 
			String userId, 		// userID
			String userPw, 		// user Password
			String save_check, 	// user ID save
			String first_check, // 첫방문인지 확인.
			HttpSession session, HttpServletResponse response,
			Model model) throws Exception {

	
		Optional<Boolean> login_test = Optional.ofNullable((Boolean) session.getAttribute("login_test_result"));
		boolean login_test_result = login_test.orElse(false);

		Optional<String> save_check_nullable = Optional.ofNullable(save_check);
		boolean idSaveChk = save_check_nullable.map(val -> true).orElse(false);

		Optional<String> first_check_nullable = Optional.ofNullable(save_check);
		boolean firstChk = first_check_nullable.map(val -> true).orElse(false);

		// -------------------------------TEST CODE---------------------------//
		System.out.println(">> LoginProcess START");
		System.out.println(">>  userId : " + userId + "\n" + ">>  userPw :" + userPw + "\n" + ">>  save_check : "
				+ idSaveChk + "\n" + ">>  first_check: " + firstChk);

		if (!memberService.memberChkDao(userId, userPw).equals("0")) {
			System.out.println(">>  login 성공.");
			login_test_result = true;
			session.setAttribute("login_test_result", login_test_result);
			session.setAttribute("userId", userId);
			// user 정보를 세션에 저장.

			MemberDto userInfo = memberService.memberInfoDao(userId);
			session.setAttribute("userInfo", userInfo);
			System.out.println(">> Session 에 user 정보를 저장합니다.");

			// login check process
			if ("admin".equals(userId)) {// admin 사용자 인증
				System.out.println(">> 관리자 입니다. ");
				if (save_check != null && idSaveChk) {
					// save check 되어있을 때 loginId 쿠키를 저장 (1분간)
					CookieManager.makeCookie(response, "loginId", userId, 60 * 60 * 1);
				} // If End
				else {
					// save check 안되어있을 경우 쿠키 삭제함.
					CookieManager.deleteCookie(response, "loginId");
				}
				return "redirect:/testProductDisplay";
			} else{// 일반 유저 인증 
				System.out.println(">> 일반 사용자 입니다. ");
				if(save_check != null && save_check.equals("Y")){
					// save check 되어있을 때 쿠키 저장 (5분간)
					CookieManager.makeCookie(response, "loginId", userId, 60*60*5); 
				} // If End 
				else {
					// save check 안되어있을 경우 쿠키 삭제함. 
					CookieManager.deleteCookie(response,"loginId");
				}
				return "redirect:/testProductDisplay";
			}
		} else {
			System.out.println(">>  정보가 불일치 합니다. ");
			login_test_result = false;
			session.setAttribute("login_test_result", login_test_result);
			model.addAttribute("LoginErrMsg","아이디와 패스워드를 다시 확인하세요.");
			return "/UserCheckPart/login_view";
		}
	}// loginProcess END
	
	@GetMapping("/logout.do")
	public String logOut(HttpSession session) throws Exception{
		System.out.println(">> logout 을 실행합니다.");
		
		//1. 회원 인증 정보 속성 삭제 
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userRank");
		
		// 세션 모두삭제 
		session.invalidate();
		return "/UserCheckPart/login_view";
	}// logOut END
}
