<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		상품 총개수 : ${pcnt}
		<c:set var ="pageSize" value="8"/><br>
		페이지 당 상품수 : ${pageSize}
		currentPage: ${currentPage }<br>
		startRow:	${startRow}<br>
		pageCount:${pageCount}<br>
		pageBlock:${pageBlock}<br>
		startPage:${startPage}<br>
		endPage:${endPage}<br>
		
		<c:if test="${endPage> pageCount}">
			<c:set var  ="endPage" value ="pageCount"/>
		</c:if>
		
		<!-- PREVIOUS button generation -->
		<c:if test="${startPage > pageBlock}">
			<a href ="cGoHome.do?pageNum=${startPage-pageBlock}">Prev</a>
		</c:if>
		
		<!-- Page number button generation -->
		<c:forEach begin ="${startPage}" end ="${endPage}" var= "i">
			<a href="cGoHome.do?pageNum=${i}">${i} </a>
		</c:forEach>

		<!-- NEXT button generation -->
		<c:if test="${endPage < pageCount}">
			<a href ="cGoHome.do?pageNum=${startPage+pageBlock}">Next</a>
		</c:if>
			
			
			
	
		
	
			
		
		
		
	
	</body>
</html>