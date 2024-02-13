<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!--
--------------------------------------------------------------
* Description 	: User Purchase Page
*  Detail		: 구매 페이지 
* Author 		: KBS, L S 
* Date 			: 2024.02.13
* ---------------------------Update---------------------------		
* <<2024.02.13>> by KBS
*		 1.


카트에서 가져와야 할 정보
product_code

cust_id
--------------------------------------------------------------
-->	








					<table border="1">
						<tr>
								<th>상품이름</th>
								<th>수량</th>
								<th>가격</th>												
						</tr>
						<c:forEach items="${purchaseInfo}" var="dto">
							<tr>
									<td>${dto.product_name }</td>
									<td>${dto.cart_qty }</td>
									<td>${dto.price }</td>
							</tr>
						</c:forEach>
				</table>	
		






</body>
</html>