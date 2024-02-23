<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--  
	/*
		Description :  관리자 페이지 top 
			Details : 
			Author	: pdg
			Date 	: 2024.02.02
			Update	:
	*/		Update	: 2024-02-23  by KBS 
	   				1. 스프링으로 이동
	
	
-->

<html>
	<head>
		<meta charset="UTF-8">
		<title>APPLE STORE 관리자 페이지 </title>
	 	 <link rel="stylesheet" href="resources/css/top_admin.css" />
	</head>
	<body>
	  <header id="header">
	    <h1>Apple store 상품및 회원 관리 </h1>
	
	  </header>
	<nav id="index">
	  <a href="/aProductListUpdate">	상품 조회/수정</a>	 	&nbsp;&nbsp;&nbsp;
	  <a href="/aProductInsert">		상품 등록</a>	 		&nbsp;&nbsp;&nbsp;
	  <a href="/aCustomerList">		회원 목록 조회/수정</a> 		&nbsp;&nbsp;&nbsp;
	  <a href="/aCustomerOrderList">	회원 매출 조회</a> 		&nbsp;&nbsp;&nbsp;
	  <a href="/aGoHome">				관리자홈</a> 			&nbsp;&nbsp;&nbsp;
	  <a href="/cGoHome">				유저홈</a> 			&nbsp;&nbsp;&nbsp;
	  <a href="/aProductQuestionList">문의 내역</a> 			&nbsp;&nbsp;&nbsp;
	  <br>
	</nav>
	</body>
<%-- 	<ul>
		<li>page 영역 속성 : <%= pageContext.getAttribute("pAttr") %></li>
		
		<li>request 영역 속성 : <%= request.getAttribute("rAttr") %></li>
	
	</ul> --%>
	
</html>