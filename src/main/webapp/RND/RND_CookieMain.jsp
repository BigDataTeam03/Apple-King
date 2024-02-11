<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: RND Cookie 를 사용해보자 
		Detail	:
				1.
* Author 		: PDG 
* Date 			: 2024.02.10
--------------------------------------------------------------
-->


<html>
<head>
<meta charset="UTF-8">
<title>Cookie</title>
</head>
<body>
	<h2> 1. cookie 설정 </h2>
	<%
		Cookie cookie = new Cookie("myCookie", "this");
		
		//웹 에플리케이션 전체에서 사용하겠다. 
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
	%>
	<h2> 2. cookie값 확인 </h2>
	<%
	 	Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie c : cookies){
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				out.println(String.format("%s : %s<br>",cookieName,cookieValue));
			}
		}
	%>
	<h2>3. 페이지 이동후 쿠키값 확인하기 </h2>
	<a href ="CookieResult.jsp"> 다음 페이지에서 쿠키값 확인하기 </a>
</body>
</html>
