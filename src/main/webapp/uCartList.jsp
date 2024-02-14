<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file ="top_user.jsp" %>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: User Cart page
*  Detail		: 장바구니페이지 
* Author 		: KBS, PDG 
* Date 			: 2024.02.08
* ---------------------------Update---------------------------		
* <<2024.02.09>> by PDG
*		 1. 주석  및 정리
--------------------------------------------------------------
-->	

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니 목록</title>
	 <link rel = "stylesheet" href ="uCartList.css">
</head>
<body>
    <div class="container">
        <h2>장바구니 목록</h2>
        <div>총 상품 종류: <span id="cartTot"></span></div><br>
        <div id="cartList"></div> 
        <br>
        <button id="cartAllSelectBtn">전체 선택</button>
        <br><br>
        <button id="cartDeleteBtn">선택 삭제</button>
        <br><br>
        총 가격: <span id="totalPrice"></span>
        <br><br>
        <form action="purchase.do" method="post">
            <input type="submit" value="구매하기">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="uCartList.js"></script>
</body>
</html>
