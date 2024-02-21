<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/proListStyle.css" />
</head>
<body>
    <table width="500" cellpadding="0" cellspacing="0" border="1">
        <tr>
            <th bgcolor="#808080">상품명</th>
            <th bgcolor="#808080">수량</th>
            <th bgcolor="#808080">제조일</th>
            <th bgcolor="#808080">무게</th>
            <th bgcolor="#808080">사이즈</th>
            <th bgcolor="#808080">사진</th>
            <th bgcolor="#808080">가격</th>
        </tr>
       
        <c:forEach items="${list}" var="dto">
            <tr>
                <td>${dto.product_name}</td>
                <td>${dto.product_qty}</td>
                <td>${dto.manufacture_date}</td>
                <td>${dto.weight}</td>
                <td>${dto.size}</td>
                <td>${dto.product_image_names}</td>
                <td>${dto.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
