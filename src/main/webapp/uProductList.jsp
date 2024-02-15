<%@page import="dto.productDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	* <<2024.02.11>> by DK  
	    1.  CSS 추가. 
	    2.  몇개씩 보기 추가.     
	    
	  <<2024.02.13>> by DK, pdg
	  	1.상품목록에서 검색을한후 5개씩 보기를 클릭하면 검색결과가 사라지는 문제 해결   
	  <<2024.02.14>> by DK , pdg
	  	1.paging 기능 (for each 문으로 페이지 링크버튼 추가)
	  	2. paging 기능 완성 및 가격 포맷 jstl 로 수정
	--------------------------------------------------------------
	*/
    		  	 %>
<html>
	<head>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<style>
	
		.navbar {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	}

	    .logo-img {
      max-height: 100px; 
	}
	
	/* 검색창 스타일 */
	#searchContent {
	    padding: 5px;
	    width: 300px;
	    margin-right: 1px;
	    margin-top: 150px;
	}
	
	#searchButton {
	    padding: 5px 10px;
	    background-color: #007bff;
	    color: white;
	    border: none;
	    cursor: pointer;
	    margin-top: 150px;
	}
	
	/* 가격 정렬 셀렉트 박스 스타일 */
	#classifyOption, #itemsPerPageSelect {
	    padding: 1px;
	    width: 100px; /* Adjust the width as needed */
	    margin-top: 100px;
	}
	
	/* 상품 카드 스타일 */
	.card {
	    width: 200px;
	    height: 300px;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	    margin: 10px;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    overflow: hidden;
	}
	
	.card img {
	    width: 100%;
	    height: auto;
	}
	
	.card-body {
	    padding: 100px;
	}
	
	.card-title a {
	    color: #333;
	    text-decoration: none;
	    font-weight: bold;
	    font-size: 40px;
	}
	
	.red-price {
	    color: red;
	    font-weight: bold;
	}
	/* 추가한 CSS 스타일 */
	.blue-price {
	    color: blue; /* 파란색으로 설정 */
	    font-size: 14px; /* 글씨 크기를 작게 조절 */
	}
	/* 카드 그리드 스타일 */
	.card-container {
	    display: flex;
	    flex-wrap: wrap;
	    justify-content: center;
	    margin-top: 40px; /* Adjust margin-top to move search content lower on the page */
	    margin-bottom: 20px;
	}
	
	#page_control a {
	    padding: 5px 10px;
	    margin-right: 5px;
	    background-color: #007bff;
	    color: white;
	    text-decoration: none;
	    border-radius: 5px;
	}
	
	.card-title a {
	    color: #333;
	    text-decoration: none;
	    font-weight: bold;
	    font-size: 18px; 
	}
	
	        
    </style>		
	    <meta charset="UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="uProductList.css" />
	</head>
	<%
		ProductDao pdao =new ProductDao();
		int pcnt = pdao.productCount();
		// paging  process 
		int pageSize = 8;
		// Curren page information 
	 	String pageNum = request.getParameter("pageNum");
		//out.print(pageNum);
		if(pageNum == null){
			pageNum ="1";
		}
		// First row number calc
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize +1;
		List<productDto> productList = pdao.getProductList(startRow, pageSize);
	%>
	<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <a class="navbar-brand" href="cGoHome.do">
            <img src="image/logo.png" class="logo-img">
        </a>
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="uCartList.do">장바구니</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="myPage.do">마이페이지</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.do">로그아웃</a>
                </li>
            </ul>
        </div>
    </div>
	</nav>
	
		<input type="text" placeholder="찾고싶은 상품을 입력하세요!" id ="searchContent"  ></input>
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
	
		
	   
	    <!-- 상품 전체 조회 -->
	    	<div class="card-container">
			 	<c:forEach var="item" items="<%=productList%>">
			 		 	<div class ="card" >
	 	 				<img src="image/${item.detail_image_name}">
							<div class="card-body">
								<h5 class="card-title">
									<a href="javascript:void(0);" onclick="saveProductInfo(${item.product_code},'${item.product_name}', ${item.price}, '${item.origin}', '${item.size}', ${item.weight})" class="bold">${item.product_name}</a>
								</h5>
								<p class="card-text">
		                        <span class="red-price bold">
		                         <fmt:formatNumber value="${item.price}"  pattern="0,000"/> 원<br />
		                        </span>
		                         <span class="blue-price">100g당 <fmt:formatNumber value="${Math.ceil(item.price /(item.weight *100))}" />원<br /></span>
		                    </p>
							</div>
	 					</div>
	 			</c:forEach>
	 		</div>
	 	<div id ="page_control">
	 		<% 
	 			if(pcnt != 0){ // pcnt : 전체 상품 개수
	 				// total page number calc
	 				int pageCount = pcnt / pageSize + (pcnt%pageSize ==0? 0:1);
	 				// 한페이지에 보여줄 페이지 블럭 : 페이지버튼의 개수
	 				int pageBlock = 2;
	 				// 한페이지에 보여줄 페이지 블럭 시작번호 계산 
	 				// 전체 페이지 수를 페이지 블럭 으로쪼개면 12345, 67879 10
	 				// 등으로 나누어지는데  이와중에 특정 블럭에 있는 페이지에서 1을 빼는이유는
	 				// 마지막 페이지를 그냥 나누면 다음 페이지블록 첫 번째시작 번호가 나오기 때문. 
	 				
 	 				int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
	 				int endPage = startPage + pageBlock -1;
	 				//End page can be greater than the total page. 
	 				//Last page to equalize with the total page count. 
	 				if(endPage > pageCount){
	 					endPage = pageCount;
	 				}//if end
	 				
	 				// Previous button gen
	 			    if(startPage>pageBlock){
	 			%>
		 				<a href ="uProductList.jsp?pageNum=<%=startPage-pageBlock %>">Prev</a>
	 			<%  }//if end %>
	 		
		 		<%  // Page button gen 
		 			for(int i= startPage; i<=endPage; i++){ %>
		 			<a href="uProductList.jsp?pageNum=<%=i %>"><%=i %></a>
		 		<%  } // for end%>
		 		<%
		 			// next button gen
		 			if(endPage<pageCount){
		 				%>
		 				<a href ="uProductList.jsp?pageNum=<%=startPage+pageBlock %>">Next</a>
		 				<%
		 			}
		 		%>
	 		<% }// pcnt If end%>
	 	</div>
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src="uProduct2.js?var=1"></script>
	</body>
</html>
