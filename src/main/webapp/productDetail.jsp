<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ='top_user.jsp' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    // jsp 내장객체
    ServletContext context = getServletContext();
    //String realfolder = context.getRealPath(savefolder);
%>
<!DOCTYPE html>
<c:set var="dto" value="${detailSession}" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>상품 상세 페이지</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            text-align: center;
            background-color: rgb(255, 255, 200);
            padding: 20px;
        }

        .container {
            max-width: 1200px;
        }

        .product-details {
            display: flex;
            justify-content: space-between;
        }

        .product-image {
            flex: 1;
            margin-right: 20px;
        }

        .product-image img {
            width: 100%;
            height: auto;
        }

        .product-info {
            flex: 2;
            text-align: left;
        }
        
    </style>
</head>
<body>
    <div class="container">
        <div class="product-details">
            <div class="product-image">
                <img src="${dto.detail_image_name}" alt="상품 이미지">
            </div>
            <div class="product-info">
                 
       
	이미지: <%= request.getParameter("product_image_names") %><br>
   	상품명: <%= request.getParameter("product_name") %><br>
    원산지: <%= request.getParameter("origin") %><br>
    별점: <%= request.getParameter("rating") %><br>
    가격: <%= request.getParameter("price") %><br>
    수량: <%= request.getParameter("size") %><br>
    무게: <%= request.getParameter("weight") %><br>

	
	<form name="cartForm" action="cart.do">
		<input type="submit" class="btn btn-primary" value="장바구니"></input>
	</form>
	<!-- <input type="submit" class="btn btn-primary" value="장바구니"></input> -->
	<!-- <input type="submit" class="btn btn-primary" value="바로구매"></input>  -->
</body>