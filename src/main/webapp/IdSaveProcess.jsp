<%@ page import="com.javaproject.util.JSFunction"%>
<%@ page import="com.javaproject.util.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 

	
	/*--------------------------------------------------------------
	* Description 	: Login 아이디를 저장하는 jsp
			Detail	:
	    		  	1.JSFunction 을 사용하여 페이지 이동 및 back
	    		  	2. Cookiemanager 사용 
	* Author 		: PDG
	* Date 			: 2024.02.10
	* ---------------------------Update---------------------------		
	* <<2024.02.10>> by PDG
	    
	--------------------------------------------------------------*/
	// loginForm 에서 전송함  form value get.
	String user_id = request.getParameter("userId");
	String user_pw = request.getParameter("userPw");
	String save_check = request.getParameter("save_check");
	
	// 아이디 비교 
	if ("admin".equals(user_id) && "123".equals(user_pw)){// admin 사용자 인증
		
		//Login 성공
		if(save_check != null && save_check.equals("Y")){
			
			// save check 되어있을 때 쿠키 저장 
			CookieManager.makeCookie(response, "loginId", user_id, 77221592);
						
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
	session.setAttribute("userPw",request.getParameter("userPw"));


%>