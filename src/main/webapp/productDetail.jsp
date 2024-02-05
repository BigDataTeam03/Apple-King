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
<c:set var="dto" value="${detailSession }" />
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
                <img src="이미지URL" alt="상품 이미지">
            </div>
           <div class="product-info">
                 
            <input type = "text" name = "product_name" style="border: none; background-color: transparent; outline: none;" value="APPLE" readonly required>
         </div>
