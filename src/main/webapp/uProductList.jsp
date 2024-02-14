<%@page import="dto.productDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="top_user.jsp" %>
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
		<style> 
		.red-price {
	    color: red;
	}
		.bold {
    	font-weight: bold;
	}	
		.card-container {
	    display: grid;
	    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
	    gap: 20px;
	    justify-items: center;
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
		int pageSize = 5;
		// Curren page information 
	 	String pageNum = request.getParameter("pageNum");
		out.print(pageNum);
		if(pageNum == null){
			pageNum ="1";
		}
		// First row number calc
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize +1;
		List<productDto> productList = pdao.getProductList(startRow, pageSize);
	%>
	<body>
		<input type="text" placeholder="찾고싶은 상품을 입력하세요!" id ="searchContent" size="50" ></input>
		<button id ="searchButton">검색</button>
		
		<!-- 가격순으로 정렬 -->
	   	<select id="classifyOption">
		 	<option value="highprice">높은가격순</option>
		 	<option value="lowprice">낮은가격순</option>
		 	<option value="product_code">상품코드</option>
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
		                         <fmt:formatNumber value="${item.price }"  pattern="0,000"/> 원<br />
		                        </span>
		                        <br>
		                         <span class="red-price">100g당 <fmt:formatNumber value="${item.price /(item.weight *100)}" /> 원<br />
		                        </span>
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
