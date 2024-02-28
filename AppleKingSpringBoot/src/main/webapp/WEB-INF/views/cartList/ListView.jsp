<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--  
 /*
	--------------------------------------------------------------
	* Description 	: User Cart page
	*  Detail		: 장바구니페이지 
	* Author 		: KBS 
	* Date 			: 2024.02.22
	* ---------------------------Update---------------------------		
	* <<2024.02.22>> by KBS 
	   1. 리스트 출력기능 완료( 추가작업 필요 )
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
        총 가격: <span id="totalPrice"></span>
        <br><br>
        <button id="purchaseBtn"> 선택상품 구매하기</button>
        
      	<a href="ProductDisplay">상품목록으로 가기</a> 
       
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="resources/js/cart.js"></script>
</body>
</html>
