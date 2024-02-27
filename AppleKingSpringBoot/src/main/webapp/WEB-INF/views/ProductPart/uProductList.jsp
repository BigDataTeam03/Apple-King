<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../top/top_user.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%/*
	--------------------------------------------------------------
	* Description 	: User Prodcut List 
			Detail	: 상품 목록 조회 및 검색, 정렬 기능 
	    		  	 button id  : 1. searchButton -> 상품 검색 수행. (product_name  JS 에서 사용)
	    		  	 몇개씩 보기 기능 -> id : itemsPerPageSelect
	    		  	 가격순 정렬 값 JS 로 보내고 서블릿에서도 getParm 으로 받아 씀."classifyOption"
	* Author 		: PDG
	* Date 			: 2024.02.05
	* ---------------------------Update---------------------------		
	* <<2024.02.09>> by PDG
	    1.  주석안달아서 여기는 내가 먹음. 
	    2. CSS 추가. 사진 너무커서 줄임. 
	    3. 정렬 전혀안됨. + 기본순정렬 = 이름순으로 정렬할것. 
	    4. 상품 선택시 상품의 정보가 상세페이지로 이동하면서 세션에 저장되게 함. 
	  <<2024.02.11>> by DK  
	    1.  CSS 추가. 
	    2.  몇개씩 보기 추가.     
	  <<2024.02.13>> by DK, pdg
	  	1.상품목록에서 검색을한후 5개씩 보기를 클릭하면 검색결과가 사라지는 문제 해결   
	  <<2024.02.14>> by DK , pdg
	  	1.paging 기능 (for each 문으로 페이지 링크버튼 추가)
	  	2. paging 기능 완성 및 가격 포맷 jstl 로 수정
	  <<2024.02.15> by pdg
	  	1. 로그인 후에 들어올때 userId session 값을 사용해서 alert 띄워주는 기능 추가
	  <<2024.02.21 >by pdg, dk, ls
	  	1.기존 jsp 로 만든 것을 jstl 을 사용하고 controller 단에서 해결하도록 변경함.
	  <<2024.02.22 >by dk, ls
	  	1.JSP에서는 특수 문자를 그대로 사용할 수 없기 때문에 >와 <를 각각 gt와 lt로 바꿈.
	  <<2024.02.26>> by pdg
	  	1.  top_user 경로 설정 수정함. 
	  	2. classifyOption => SortingOptions 로 수정, highprice 등등 carmelCasification
	  	3  정렬 옵션 을 셀렉트가아닌 링크로 바꿔야함. 	
	  
	--------------------------------------------------------------
	*/
    		  	 %>
<html>
	<head>		
	    <meta charset="UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="resources/css/proListStyle.css" />
	</head>	
	<body>
	
		<input type="hidden" id ="userName" value ="${userName}"/> 
		<input type="hidden" id ="first_check" value ="${first_check}"/> 
		<input type="hidden" id ="currentPage" value ="${currentPage}"/> 
		<input type="hidden" id ="startProduct" value ="${startProduct}"/> 
		<input type="hidden" id ="pageSize" value ="${pageSize}"/> 
		
		<!--상품 검색 -->
	    <div class="searchContainer">
			<input 	type="text" 	
					placeholder="찾고싶은 상품을 입력하세요!"
					id ="searchContent" 
					size="50" ></input>
			<button id ="searchButton">검색</button>
			
			<!-- 가격순으로 정렬 -->
		    <a href="#" id="productRank" >애플랭킹순</a> &nbsp;
			<a href="#" id="highPrice" >높은 가격순</a>&nbsp;
			<a href="#" id="lowPrice">낮은 가격순</a>&nbsp;
			<a href="#" id="sold_qty">판매량순</a>&nbsp;
			<a href="#" id="product_reg_date">최신순</a>
		    
		    <!-- 몇개씩 보기 기능 추가 (아이템 추가할경우 수정하기) -->
			<select id="itemsPerPageSelect" onchange="setItemsPerPage(this.value)">
			  <option value="5" selected>	5개씩 보기</option>
			  <option value="8">			8개씩 보기</option>
			  <option value="10">			10개씩 보기</option>
			</select>
		</div>
	    	
    	<!-- Card Container -->
    	<div class="card-container">
		 	<c:forEach var="item" items="${productList}">
		 			<!-- CARD -->
		 		 	<div class ="card" >
		 		 		<!-- Product image Plot -->
 	 					<img src="resources/image/${item.product_image}" alt="${item.product_name} Image">
						<!--Card Body -->
						<div class="card-body">
							<!--Card Title -->
							<h5 class="card-title">
								<a href="javascript:void(0);" 
								   onclick="saveProductInfo('${item.product_code}',
								    '${item.product_name}',
								     ${item.price},
								    '${item.origin}',
								    '${item.size}',
								     ${item.weight},
								     ${item.product_qty})">${item.product_name}</a>
							</h5><!--Card Title END-->
							<!--  Card content -->
							<p class="card-text">
	                        	<span class="red-price bold">
	                         		<fmt:formatNumber value="${item.price}"  pattern="0,000"/> 원<br />
	                        	</span>
		                        <span class="blue-price">
		                         	(100g당 <fmt:formatNumber value="${item.price /(item.weight *100)}" pattern= "#" />원)<br /> 
		                        </span>
	                    	</p><!--  Card content END -->
						</div><!--/ Card Body END -->
 					</div><!-- CARD END-->
 			</c:forEach>
 		</div><!-- Card Container END -->
	 		
		<div id="page_control">
    <c:if test="${endPage gt pageCount}">
        <c:set var="endPage" value="${pageCount}" />
    </c:if>

    <!-- PREVIOUS button generation -->
    <c:if test="${startPage gt pageBlock}">
        <a href="ProductDisplay?pageNum=${startPage-pageBlock}">PREV</a>
    </c:if>

    <!-- Page number button generation -->
    <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <a href="ProductDisplay?pageNum=${i}">${i} </a>
    </c:forEach>

    <!-- NEXT button generation -->
    <c:if test="${endPage lt pageCount}">
        <a href="ProductDisplay?pageNum=${startPage+pageBlock}">NEXT</a>
    </c:if>

</div>
		<!--  Java Script Call -->
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src="resources/js/uProduct2.js?var=1"></script>
	</body>
</html>
