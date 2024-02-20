<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: RND session 정보 
		Detail	:
				1.
* Author 		: PDG 
* Date 			: 2024.02.10
* ---------------------------Update---------------------------		
* <<2024.02.04>> by PDG
--------------------------------------------------------------
-->		
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> cookie value check( after generation of cookie page)</h2>
		<%
			Cookie[] cookies = request.getCookies();
			if ( cookies != null){
				for (int i = 0 ; i <cookies.length; i++){
					String cookieName = cookies[i].getName();
					String cookieValue = cookies[i].getValue();
					out.println(
						String.format("쿠키명 : %s - Cookie value : %s <br/>",
						 					cookieName, cookieValue));
				}
			}
		%>

</body>
</html>