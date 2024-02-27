<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../top/top_user.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%/*
--------------------------------------------------------------
* Description 	: My Page + Orders
* Detail		: 마이페이지 + 구매내역
* Author 		: DK, LS
* Date 			: 2024.02.22
* ---------------------------Update---------------------------	
* <<2024.02.22> by DK
*	1. session 말고 sessionScope 를 사용해 세션에 저장된 사용자의 정보를 가져온다.
*<<2024.2.27> by DK 
*	1. 구매내역 컬럼 더 추가해서 불러오기. 
--------------------------------------------------------------
*/ %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Page</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/myPageStyle.css">
</head>
<body>
      <div class="card-body">
          <h1>
             ${memberList.name} <span class="smaller-font">님</span> 
              	  
          </h1>
          <a class="nav-link ml-2 underline-blue" href="myInfo">회원정보 변경</a> 
      </div>
      
   	  <div style="margin-left: 20px;"> 
	        <span style="font-weight: bold; font-size: 24px;">등급: ${memberList.cust_rank}</span>
	        	&nbsp;&nbsp;&nbsp;
	        <span style="font-weight: bold; font-size: 18px;">가입날짜: ${memberList.reg_date}</span>
	  </div><br><br>
 
	 <div class="orderContainer">
    	<h4><strong>${memberList.name} 님의 구매내역</strong></h4><br>
    	<table>
	        <tr>
	            <th>구매날짜</th>
	            <th>주문코드</th>
	            <th>상품명</th>
	            <th>가격</th>
	            <th>구매수량</th>
	            <th>결제수단</th>
	            <th>사용한포인트</th>
	            <th>사진</th>
	        </tr>
        
	        <c:forEach var="order" items="${orderList}">
	            <tr>
	                <td>${order.orderdate}</td>
	                <td>${order.order_code}</td>
	                <td><strong>${order.product_name}</strong></td>
	                <td><fmt:formatNumber value="${order.price}" pattern="#,###"/></td>
	                <td>${order.order_qty}</td>
	                <td>${order.payment_method}</td>
	              	<td>${order.used_point}</td>
	                <td style="width: 15%; height: 15%;"> <img src="resources/image/${order.product_image}" alt="상품 이미지" style="max-width:100%; max-height: 100%;"></td>
	            </tr>
	        </c:forEach>
    	</table>
	</div>
</body>

</html>
