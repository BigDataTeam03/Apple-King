<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--  
 /*
	--------------------------------------------------------------
	* Description 	: User Cart page
	* Detail		: 장바구니페이지 
	* Author 		: KBS , pdg
	* Date 			: 2024.02.22
	* ---------------------------Update---------------------------		
	* <<2024.02.22>> by KBS 
	*  1. 리스트 출력기능 완료( 추가작업 필요 )
	* <<2024.02.28>> by DK 
	*  1. 카트리스트 CSS 추가. \
	
		<<2024.02.28 by pdg>>
		1. ListVeiw => cartList 로 파일 이름 변경
		2. 스피너 버튼 누를때 가격 실시간으로 변경되는 기능
		
		3. 결제를 위한 정보를 장바구니 페이지에서도 보내줄수 있도록 하자. 
		
		
	--------------------------------------------------------------
*/
-->
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니 목록</title>
	<link rel = "stylesheet" href ="resources/css/cart.css"> 
</head>
<%-- <%
	String product_name = (String)session.getAttribute("product_name");
	Boolean pageChk = false ;
	Map<String,Boolean> productChkMap = (Map<String,Boolean>)session.getAttribute("productChkMap");
	boolean beforeChange = (boolean)productChkMap.get(product_name);
	out.print("수정하기 전의 pagecheck 값 : "+beforeChange);
	
	
	productChkMap.put(product_name,pageChk);
	boolean result = (boolean)productChkMap.get(product_name);
	out.print("pagechek 값 : "+result);
%> --%>
<body>
	<!--  필요한 정보들 
		cust_id, name,product_code, product_name, price, payment_method, used_point, order_qty 
	from cart as ca 
	
	 -->

    <div class="container" > 
        <h2>장바구니 목록</h2>
        <div>총 상품 종류: <span id="cartTot"></span></div><br>
        <div id="cartList"></div> 
        <br>
        <div style="display: inline-block;">
       		<button id="cartAllSelectBtn">전체 선택</button>
   		</div>
    	<div style="display: inline-block; margin-left: 10px;">
        	<button id="cartDeleteBtn">선택 삭제</button>
   		</div>
        <br><br>
       <div style="font-size: 24px;">총 가격: <span id="totalPrice" style="font-size: 20px; color: red;"></span>원</div>
        <br><br>
        <button id="purchaseBtn" style="font-size: 20px;"> 선택상품 구매하기</button>
      	<a href="ProductDisplay">상품목록으로 가기</a> 
      	
	    <input type="hidden" name = "cust_id" value = "${userId}">
		<input type="hidden" name = "name" value = "${userName}">
		<input type="hidden" name = "product_code" value = "${product_code}">
		<input type="hidden" name = "product_name" value = "${product_name}">
		<input type="hidden" name = "price" value = "${price}">
		<input type="hidden" name = "payment_method" value = "${payment_method}">
		<input type="hidden" name = "used_point" value = "${used_point}">
		<input type="hidden" name = "order_qty" value = "${order_qty}">
      	
      	
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="resources/js/cart.js"></script>
</body>
</html>
