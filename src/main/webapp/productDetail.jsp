<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ='/TOP/top_user.jsp' %>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
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
        
        
        

input[type="number"] {
    position: relative;
    margin: 0 0 1rem;
    border: 1px solid #BBB;
    border-color: #BBB #ECECEC #ECECEC #BBB;
    padding: .2rem;
    max-width: 50px; /* 입력 필드의 최대 너비 설정 */
}

/* Spin Buttons modified */
input[type="number"]::-webkit-outer-spin-button, 
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    background: #FFF url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAJCAYAAADgkQYQAAAAKUlEQVQYlWNgwAT/sYhhKPiPT+F/LJgEsHv37v+EMGkmkuImoh2NoQAANlcun/q4OoYAAAAASUVORK5CYII=) no-repeat center center;
    width: 1em;
    border-left: 1px solid #BBB;
    opacity: .5; /* shows Spin Buttons per default (Chrome >= 39) */
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
}

/* Override browser form filling */
input:-webkit-autofill {
    background: black;
    color: red;
}

/* 숫자 입력값이 음수로 넘어가지 않도록 함 */
input[type="number"] {
    -moz-appearance: textfield;
}

/* 입력값이 50을 넘어가지 않도록 함 */
input[type="number"]::-webkit-outer-spin-button, 
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

input[type="number"]::-webkit-inner-spin-button:hover,
input[type="number"]::-webkit-inner-spin-button:active{
    box-shadow: 0 0 2px #0CF;
    opacity: .8;
}

 	
    </style>
</head>
<body>
    <div class="container">
        <div class="product-details">
            <div class="product-image">
                <img src="image/${dto.detail_image_name}" alt="상품 이미지">
            </div>
            <div class="product-info">
                 
      
	이미지: <%= request.getParameter("product_image_names") %><br>
   	상품명: <%= request.getParameter("product_name") %><br>
    원산지: <%= request.getParameter("origin") %><br>
    별점: <%= request.getParameter("rating") %><br>
    가격: <%= request.getParameter("price") %><br>
    크기: <%= request.getParameter("size") %><br>
    무게: <%= request.getParameter("weight")  %>kg<br>
	
	<input type="number">
	
	<form name="cartForm" action="cart.do">
		<input type="submit" class="btn btn-primary" value="장바구니"></input>
	</form>
	<!-- <input type="submit" class="btn btn-primary" value="장바구니"></input> -->
	<!-- <input type="submit" class="btn btn-primary" value="바로구매"></input>  -->
</body>