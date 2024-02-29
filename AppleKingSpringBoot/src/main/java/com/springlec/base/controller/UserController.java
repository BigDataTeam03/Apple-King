package com.springlec.base.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javaproject.util.CookieManager;
import com.springlec.base.model.MemberDto;
import com.springlec.base.service.MemberDaoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes
public class UserController {
	/*--------------------------------------
	 * Description	: User controller - LogIn, signUp function 
	 * Detail 		:
	 * 				1.userInfo session 
	 * 					- 
	 * Author 		: PDG
	 * Date 		: 2024.02.21
	 * Update 		
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
	 * 
	 * 		Update 2024.02.25 by PDG
	 * 		 1. annoatation 을이용하여 userId session 을 사용하자. ->discard
	 * 
	 * 		<<2024.02.28 by pdg>>
	 * 		 1. sign up 기능 
	 * o	 
	 *-------------------------------------- 
	 */

	@Autowired // service wired
	MemberDaoService memberService;
	private boolean checkDuplicateId;

	@GetMapping("/")
	public String userLogin() throws Exception {
		System.out.println("** ROOT PAGE START **");
		return "redirect:ProductDisplay";
	}

	@PostMapping("/signUpStart")
	public String userSignUp() throws Exception {
		// *** START Message ***
		System.out.println("**<<UserController @Post : userSignUp>>**");
		return "/UserCheckPart/signup_view";
	}
	
	@PostMapping("SignUpUserOverlapChk")
	@ResponseBody
	public ResponseEntity<Boolean> SignUpUserOverlapChk(@RequestParam("id") String userId) {
	    // *** START Message ***
	    System.out.println("**<<UserController @Post : SignUpUserOverlapChk>>**");

	    try {
	        // 중복 여부를 DB에서 확인
	        Integer result = memberService.checkDuplicateId(userId);

	        // 중복 여부에 따라 결과 반환
	        if (result != null && result > 0) {
	            // 중복된 아이디일 경우
	            System.out.println("중복된 아이디입니다.");
	            return ResponseEntity.ok(true);
	        } else {
	            // 사용 가능한 아이디일 경우
	            System.out.println("사용가능한 아이디입니다.");
	            return ResponseEntity.ok(false);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 발생 시 서버 오류 반환
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}

	@PostMapping("loginProcess")
	public String loginProcess(
			@ModelAttribute("userId") String userId,
			@ModelAttribute("save_check") String save_check,
			@ModelAttribute("first_check") String first_check,
			@RequestParam("userPw") 
			String userPw, 		// user Password 는 session 에 저장하지 않음. 
			HttpSession session, HttpServletResponse response,
			Model model) throws Exception {
		
		// Session  값 OPtional 을 이용한Null point  처리 
		Optional<Boolean> login_test = Optional.ofNullable((Boolean) session.getAttribute("login_test_result"));
		boolean login_test_result = login_test.orElse(false);

		Optional<String> save_check_nullable = Optional.ofNullable(save_check);
		boolean idSaveChk = save_check_nullable.map(val -> true).orElse(false);// null 일경우 false

		Optional<String> first_check_nullable = Optional.ofNullable(first_check);
		String firstChk = first_check_nullable.map(val -> "1").orElse("0");// 첫방문 => null => 1

		// -------------------------------TEST CODE---------------------------//
		System.out.println(">> **LoginProcess START**");
		System.out.println(">>  userId : " + userId + "\n" + 
						   ">>  userPw : " + userPw + "\n" + 
						   ">>  saveCheck : "+ (idSaveChk? "체크됨(true)"   :"체크안됨(false)")+ "\n" + 
						   ">>  firstChk: "+ (firstChk.equals("1")?  "첫로그인임(1)":"첫 로그인이 아님(0)"));

		if (!memberService.memberChkDao(userId, userPw).equals("0")) {
			System.out.println(">>  login 성공.");
			login_test_result = true;
		
			// user 정보를 세션에 저장.
			MemberDto userInfo = memberService.memberInfoDao(userId);
			session.setAttribute("login_test_result", login_test_result);
			session.setAttribute("first_check", "1");
			session.setAttribute("userId", userId);
			session.setAttribute("userName"		, userInfo.getName());
			session.setAttribute("userTel"		, userInfo.getTel());
			session.setAttribute("userEmail"	, userInfo.getEmail());
			session.setAttribute("userAddress"	, userInfo.getAddress());
			session.setAttribute("userRegDate"	, userInfo.getReg_date());
			session.setAttribute("userDeactDate", userInfo.getDeact_date());
			session.setAttribute("userRank"		, userInfo.getCust_rank());
			session.setAttribute("userPoint"	, userInfo.getCust_point());
			
			model.addAttribute("first_check","1");
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
				return "redirect:/aGoHome";
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
				return "redirect:/ProductDisplay";
			}
		} else {
			System.out.println(">>  정보가 불일치 합니다. ");
			login_test_result = false;
			session.setAttribute("login_test_result", login_test_result);
			model.addAttribute("LoginErrMsg","아이디와 패스워드를 다시 확인하세요.");
			return "/UserCheckPart/login_view";
		}
	}// loginProcess END
	

	
	
	@GetMapping({"/logout", "login"})
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
