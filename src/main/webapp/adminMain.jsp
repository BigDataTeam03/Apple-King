<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>AJax MySQL Table Update</title>
	</head>
	
	<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script src = "adminUpdate.js"></script>
		
	<body>
		<h2> <strong>Product Management</strong></h2>

		<input 	type ="text" id ="name" placeholder ="상품 검색">
		<button id ="queryButton">검색</button>
		
		<!--상품평 전체 조회 및 검색 결과를 하단에 보여줌. -->
		<div 	id = "result"></div> 
		<div>
			상품코드 : 	<input type ="text" id  = "scode" readonly = "readonly"> <br>
			상품명 : 	<input type ="text" id  = "sname" > <br>
			수량 : 	<input type ="text" id  = "sdept" > <br>
			 :	<input type ="text" id  = "sphone" > <br>
			주소 : 	<input type ="text" id  = "saddress" > <br>
			
		</div>
		<br>
		<!--  submit 을 누르면 s~ 어쩌구가 submit 되어 js 에서 받아줌.  -->
		<button type ="button" id = "submitBtn">수정</button>
		
		<button type ="button" id = "insertBtn">입력</button>
		
		<div 	id = "Print_code"></div> <!--  // result 부분을 Js 가 만들어주는구나 --> 
		
		
		
	</body>
</html>