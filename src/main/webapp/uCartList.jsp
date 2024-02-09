<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file ="top_user.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

			<p><strong><h2>장바구니 목록 </h2></strong> 
			
	 	<div>총 상품 종류 :<div id = "cartTot"></div>   </div>
	 				<br>
	 				<br>
	 				
		               <div id = "cartList"></div> 
	
		
		
		
		
	<br>
	<br>
	 <button id="cartAllSelectBtn">전체 선택</button>    
	 <br>
	 <button id ="cartDeleteBtn"> 삭제 </button>
	 <br>
	 총 가격 : <div id="totalPrice"></div>
	 
	 
	  <form action="purchase.do" method="post">
	  	<input type="submit" value="구매하기">
	  </form>


<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src = "uCartList.js"></script>

</body>
</html>