<%@ page import="com.javaproject.util.JSFunction_servlet"%>
<%@page import="dto.MemberDto"%>
<%@page import="dao.Login_Dao"%>
<%@ page import="com.javaproject.util.JSFunction"%>
<%@ page import="com.javaproject.util.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	//JSFunction.alertMessage(">>loginporcess 실행(in loginProcess.jsp) ",out);
	
	/*--------------------------------------------------------------
	* Description 	: Login 아이디를 저장하는 +db 조회와 아이디 비교jsp
			Detail	:
	    		  	1.JSFunction 을 사용하여 페이지 이동 및 back
	    		  	2. Cookiemanager 사용 
	    		  	3. Get parameters
	    		  		- userId
	    		  		- userPw
	    		  		- save_check
	* Author 		: PDG
	* Date 			: 2024.02.10
	* ---------------------------Update---------------------------		
	* <<2024.02.10>> by PDG
		1. 로그인 체크 간단하게 admin db 안쓰고 구현
		2. 아이디 저장 기능 구현 (쿠키 사용 -> 3분 저장 시간)
	* <<2024.02.11>> by pdg
		1. db 에서 아이디 정보 불러와서 패스워드 체크 구현
		2. 향후 MVC 로 바꿀 예정
	* << 2024.02.16>>by pdg , diana
		1. name 도 세션에 저장하게 수정
	* << 2024.02.18>>by diana
		1. register date 도 세션에 저장하게 수정
		
		
	* <<2024.02.19>> by pdg 
		1. 처음 로그인 했는지 체크하는 세션 추가  => first_check 값이 true 인 경우에만 환영합니다 메세지 띄움
		
	<<2024.02.23 > by Pdg 
	
		1. login proces jsp Dao => spring  controller 로 넘김.  login process 기능 자체를 controller
		에서 하도록 변환함. 
		
		
	--------------------------------------------------------------*/
	// loginForm 에서 전송함  form value get.
	String user_id = request.getParameter("userId");
	String user_pw = request.getParameter("userPw");
	String save_check = request.getParameter("save_check");
	String first_check = request.getParameter("first_check");// first_check 를 불러옴. 
	
	// DB -> controller -> command 로 변경 필요
	Login_Dao dao =new Login_Dao();
	MemberDto memberDto = dao.checkLogin(user_id, user_pw);
	
	//login check process
	if (memberDto.getCust_id() !=null){
		// login 성공 
		if ("admin".equals(user_id)){// admin 사용자 인증
			if(save_check != null && save_check.equals("Y")){
				// save check 되어있을 때 loginId 쿠키를 저장 
				CookieManager.makeCookie(response, "loginId", user_id, 60*60*1); // 5분동안 loginId 쿠키 가지고있음 
			} // If End 
			else {
				// save check 안되어있을 경우 쿠키 삭제함. 
				CookieManager.deleteCookie(response,"loginId");
			}
			String user_name = memberDto.getName(); 
			
			// 관리자 페이지로 이동
			request.getRequestDispatcher("aGoHome.do").forward(request,response);
			
			
		}//Admin 인증 end
		 
		
		else{// 일반 유저 인증 
			if(save_check != null && save_check.equals("Y")){
				// save check 되어있을 때 쿠키 저장 
				CookieManager.makeCookie(response, "loginId", user_id, 60*60*5); // 5분동안 쿠키 가지고있음 
			} // If End 
			else {
				// save check 안되어있을 경우 쿠키 삭제함. 
				CookieManager.deleteCookie(response,"loginId");
			}
			
			
			//세션에 아이디및 이름 저장
			session.setAttribute("userId", memberDto.getCust_id());
			session.setAttribute("userName", memberDto.getName());
			session.setAttribute("userRank", memberDto.getCust_rank());
			session.setAttribute("regDate", memberDto.getReg_date());
			String user_name = memberDto.getName(); 
			//response.sendRedirect("loginForm.jsp");
			// 일반유저 메인 화면으로 이동
			request.getRequestDispatcher("cGoHome.do").forward(request,response);
		}
	}// if end
	else{
		//로그인 실패
		request.setAttribute("LoginErrMsg"," 존재하지 않는 아이디이거나 비밀번호가 틀립니다.");
		JSFunction.alertBack("로그인에 실패했습니다. "+user_id,out);
		request.getRequestDispatcher("login_view.jsp").forward(request,response);
	}
	
	
/* 	// 관리자 아이디 비교 
	if ("admin".equals(user_id) && "123".equals(user_pw)){// admin 사용자 인증
		

		if(save_check != null && save_check.equals("Y")){
			
			// save check 되어있을 때 쿠키 저장 
			CookieManager.makeCookie(response, "loginId", user_id, 60*60*3); // 3분동안 쿠키 가지고있음 
						
		}// If save check end
		else {
			
			// save check 안되어있을 경우 쿠키 삭제함. 
			CookieManager.deleteCookie(response,"loginId");
		}
		JSFunction.alertLocation("로그인에 성공했습니다.","cGoHome.do",out);
		
	}//If id equal end
	else {
		
		//로그인 실패
		JSFunction.alertBack("로그인에 실패했습니다. "+user_id,out);
		
	}
	

	session.setAttribute("userId",request.getParameter("userId"));
	session.setAttribute("userPw",request.getParameter("userPw")); */


%>