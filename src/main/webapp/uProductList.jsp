<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ='top_user.jsp' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            text-align: center;
            background-color: rgb(255, 255, 255);
            padding: 20px;
        }

        .card {
            width: 18rem;
            margin: 20px;
        }

        .card-img-top {
            width: 100%;
            height: auto;
        }

        .card-body {
            text-align: left;
        }

        .card-text {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>

	<input type="text" placeholder="찾고싶은 상품을 입력하세요!" size="70"></input>
	
	<button id ="searchButton">검색</button>

    <!-- 상품평 전체 조회 -->
    
  
    <div class="container">
        
		<div id="result" class="row"></div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
     
    
    <script src="uProduct.js"></script>
    
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js?ver=1"></script>
   
</body>
</html>
