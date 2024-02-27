<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file ='../top/top_user.jsp' %>
<!DOCTYPE html>
	<%
	/*
	--------------------------------------------------------------
	* Description 	: User Purchase page
	*  Detail		: 주문/결제페이지 
					1.주문한 상품정보 
					2. 장바구니에서 가져온 상품
					3. 총상품 가격
					4. 총 결제금액
					5. 결제수단
					6. 결제하기 버튼 
	* Author 		: LS, pdg
	* Date 			: 2024.02.16
	* ---------------------------Update---------------------------		
	 <<Update 2024.02.19>
	 <<Update 2024.02.27>>
		1. 코드정리
		2. 이페이지는 최종 결제를 확인하는 페이지임. 결제 버튼을 눌렀을때 결제완료페이지로 가는데 
		   이페이지가 바로 그 결제 완료 페이지임. 
		3. order table 에 필요한 정보 중 5개를 controller 에 넘겨야함.
			- payment_method
			- used_point
	    	- order_qty
	    	- orderdate
	    	- soldout
			
	--------------------------------------------------------------
	*/%>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>주문/결제 페이지</title>
		<!-- CSS 추가 -->
	    <link rel="stylesheet" href="resources/css/purchase.css" />
		
	</head>
	<body>
		<select>
			<option>
			
		
		</select>
	
	
	    <h1>주문/결제</h1>
	    <hr>
	    <h2>구매자 정보</h2><br>
        <table border="1">
		    <tr>
		        <th>이름</th>
		        <th>이메일</th>
		        <th>전화번호</th>
		    </tr>
			<tr>			 		 
		 	 	<td>${memberList.name}</td>			
		 	 	<td>${memberList.tel}</td>			
		 	 	<td>${memberList.email}</td>			
			</tr>					
       	</table>
	    <h2>결제 정보</h2><br>
        <table>
        	<tr>
		        <th>총상품가격</th>
		        <th>포인트</th>
		        <th>총결제금액</th>
		        <th>결제방법</th>
   		   </tr>
   		   <tr>			 		 
		 	 	 <td>${memberList.price * memberList.product_qty}</td>
      			 <td>${memberList.product_qty}</td>
        		 <td>${memberList.price * memberList.product_qty - memberList.cust_point}</td>
        		 <td>${memberList.payment_method}</td>	
			</tr>		
        </table>
  		<form action="purchaseComplete" method="post">
            <input type="submit" value="결제하기">
        </form>
	     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	     <script src="purchase.js"></script>
	</body>
</html>
