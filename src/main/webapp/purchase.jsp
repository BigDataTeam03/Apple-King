<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file ="top_user.jsp" %>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: User Purchase page
*  Detail		: 주문/결제페이지 
				1.주문한 상품정보 
				2. 장바구니에서 가져온 상품
				3. 총상품 가격
				4.총결제금액
				5. 결제수단
				6. 결제하기 버튼 
				
* Author 		: LS
* Date 			: 2024.02.16
 <<Update 2024.02.19>.
* ---------------------------Update---------------------------		
* 
--------------------------------------------------------------
-->	

<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주문/결제 페이지</title>
</head>
<body>
    <h1>주문/결제</h1>
    <hr>
    
    <h2>구매자 정보</h2><br>
        
        <table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>전화번호</th>
    </tr>
        	</table>
    <c:forEach items= "${lists}" var="aaa">
		<tr>			 		 
	 	 	<th>${aaa.name}</th>			
	 	 	<th>${aaa.tel}</th>			
	 	 	<th>${aaa.email}</th>			
		</tr>					
	</c:forEach>
        	
    <h2>결제 정보</h2><br>
        <table>
        
        </table>
        
        	
  		<form action="purchasecomplete.do" method="post">
            <input type="submit" value="결제하기">
        </form>
    
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="purchase.js"></script>
</body>
</html>
