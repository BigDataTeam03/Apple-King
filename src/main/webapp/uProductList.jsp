<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="top_user.jsp" %>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: User Prodcut List 
		Detail	: 상품 목록 조회 및 검색, 정렬 기능 
    		  	
* Author 		: PDG
* Date 			: 2024.02.05
* ---------------------------Update---------------------------		
* <<2024.02.09>> by PDG
    1.  주석안달아서 여기는 내가 먹음. 
    2. CSS 추가. 사진 너무커서 줄임. 
    3. 정렬 전혀안됨. + 기본순정렬 = 이름순으로 정렬할것. 
    4. 상품 선택시 상품의 정보가 상세페이지로 이동하면서 세션에 저장되게 함. 
* <<2024.02.11>> by DK  
    1.  CSS 추가. 
    2.  몇개씩 보기 추가.        
--------------------------------------------------------------
-->		
<html>
	<head>
		<style> 
		.red-price {
	    color: red;
	}
		.bold {
    	font-weight: bold;
	}	
		</style>		
	    <meta charset="UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="uProductList.css" />
	</head>
	<body>

		<input type="text" placeholder="찾고싶은 상품을 입력하세요!" id ="product_name" size="50"></input>
		<button id ="searchButton">검색</button>
		<!-- 가격순으로 정렬 -->
	   	<select id="classifyOption">
		 	<option value="highprice">높은가격순</option>
		 	<option value="lowprice">낮은가격순</option>
	    </select>
	    
	    <!-- 몇개씩 보기 기능 추가 (아이템 추가할경우 수정하기) -->
		<select id="itemsPerPageSelect" onchange="setItemsPerPage(this.value)">
		  <option value="5" selected>5개씩 보기</option>
		  <option value="8">8개씩 보기</option>
		  <option value="10">10개씩 보기</option>
		</select>
	   
	    <!-- 상품평 전체 조회 -->
	    <div class="container">
	    	<div id="result" class="row"></div>
	    </div>
	 	<div id="product-list"></div>
	 	<div id="pagination"></div>
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src="uProduct.js?var=1"></script>
	</body>
</html>
