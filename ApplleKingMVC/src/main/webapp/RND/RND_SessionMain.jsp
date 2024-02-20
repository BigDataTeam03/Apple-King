<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<%
session.setMaxInactiveInterval(1900); // session remain time
// Date formatting 
SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
// session livelong -> string 
long creationTime = session.getCreationTime();
String creationTimeStr = dateFormat.format(new Date(creationTime));
System.out.println(creationTimeStr);
//System.out.print(creationTime);

long lastTime = session.getLastAccessedTime();
String lastTimeStr = dateFormat.format(new Date(lastTime));

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>세션 설정확인</h2>
	<ul>
		<li>세션 유지시간 :		<%= session.getMaxInactiveInterval() %></li>
		<li>세션 아이디 : 	 	<%= session.getId() %></li>
		<li>세션 최초 요청시각 : 	<%= creationTimeStr %></li>
		<li>마지막 요청 시각 : 	<%= lastTimeStr %></li>
		
		
	</ul> 

</body>
</html>