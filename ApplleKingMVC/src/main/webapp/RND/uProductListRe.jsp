<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>for each loop </title>
	</head>
	<%
		ProductDao dao = new ProductDao();
		int pageSize = 10;
		int totalItemCount = 219;
		//int totalItemCount = dao.productCount();//Integer.parseInt(request.getParameter("totalItemCount"));
		int totalPage = (int) Math.ceil((double)totalItemCount/pageSize) ;
		//out.print(totalPage);
		out.print(totalItemCount +"<br/>");
		// 먼저 total item count 를 여기서 받아서 사용해야한다.  -> dao 를 사용?
		
		//totalItemCount= 
		//out.print(totalPage);
%>
	
	<br>
	<body>
	
		<c:forEach var ="i" begin ="1" end ="<%=totalPage %>" step ="1" varStatus ="status">
			<a href ="#">${i}</a>
		</c:forEach>
		
		
		
		
		
		
		
		
		
		
		
		
		
	</body>
</html>