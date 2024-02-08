<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="top_user.jsp" %>

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
        
        .listPage{
        	padding:10px;
        	text-align: center; 
        	list-style: none;       
        }
        .listPage li {
        	background-color: #ffffffBD;
        	padding:20px; 
        	display: inline-block; 
        	margin: 0 10px;
        	cursor: pointer; 
        
        }
        .listPage:active {
			background-color: #B192EF;
			color: #fff; 
		}
        
        
        
        
    </style>
</head>
<body>

	<input type="text" placeholder="찾고싶은 상품을 입력하세요!" id ="product_name" size="70"></input>
	<button id ="searchButton">검색</button>
			
			   <select id="classifyOption">
            		 <option value="highprice">높은가격순</option>
           			 <option value="lowprice">낮은가격순</option>
               </select>
			
	

    <!-- 상품평 전체 조회 -->
     
    <div class="container">
    <div id="result" class="row"></div>
    </div>

 	<div id="product-list"></div>
 	
 	
 	<div id="pagination"></div>

   
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="uProduct.js?var=1"></script>
   
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js?ver=1"></script>
   
  
   
   
</body>
</html>
