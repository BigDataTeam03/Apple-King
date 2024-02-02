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
			상품코드 : 	<input type ="text" id  = "product_code" readonly = "readonly"> <br>
			상품명 : 	<input type ="text" id  = "product_name" > <br>
			수량 : 	<input type ="text" id  = "product_qty" > <br>
			원산지:	<input type ="text" id  = "origin" > <br>
			생산일 : 	<input type ="text" id  = "manufacture_date" > <br>
			무게 : 	<input type ="text" id  = "weight" > <br>
			사이즈 : 	<input type ="text" id  = "size" > <br>
			상세 이미지 : 	<input type ="text" id  = "detail_image_name" > <br>
			조회수 : 	<input type ="text" id  = "view_count" readonly="readonly"> <br>
			상품 등록일 : 	<input type ="text" id  = "product_reg_date" > <br>	
			품종 : 	<input type ="text" id  = "kind" > <br>
			섬네일 이미지 : 	<input type ="text" id  = "product_image_names" > <br>
			
			
		</div>
		<br>
		<!--  submit 을 누르면 s~ 어쩌구가 submit 되어 js 에서 받아줌.  -->
		<button type ="button" id = "updateBtn">수정</button>
		
		<button type ="button" id = "insertBtn">입력</button>
		
		<button type ="button" id = "deleteBtn">삭제</button>
		
		<div 	id = "Print_code"></div> <!--  // result 부분을 Js 가 만들어주는구나 --> 
		
		
		
	</body>
</html>