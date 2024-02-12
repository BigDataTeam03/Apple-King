<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - javabeans</title>
</head>
<body>
	<h2> useBean 액션태</h2>
	<h3> 자바빈즈 생성하기 </h3>
	
	
	<jsp:setProperty name ="person" property="name" value ="임꺽정" />
	
	<jsp:getProperty name ="person" property="name" />
	
</body>
</html>