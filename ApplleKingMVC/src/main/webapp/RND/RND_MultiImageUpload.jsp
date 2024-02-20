<!--
--------------------------------------------------------------
* Description 	: RND multi image upload 기능 
		Detail	:
				1.
* Author 		: PDG 
* Date 			: 2024.02.07
* ---------------------------Update---------------------------		
* <<2024.02.04>> by PDG
--------------------------------------------------------------
-->			

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form</title>
</head>
<body>
	<h1>form</h1>
	<form action="RND_MultiIMageUpProcess.jsp" method="post" enctype="multipart/form-data">
		<p>parameter1: <input type="text" name="param1"></p>
		<p>parameter2: <input type="text" name="param2"></p>
		<p>parameter3: <input type="text" name="param3"></p>

		<p>file 1 : <input type="file" name="file1"></p>
		<p>file 2 : <input type="file" name="file2"></p>
		<p>file 3 : <input type="file" name="file3"></p>		
		
		<p>hobby : 
			<input type="checkbox" name="hobbies" value="movies">영화감상
			<input type="checkbox" name="hobbies" value="bike">자전거	
			<input type="checkbox" name="hobbies" value="walk">산책

		<p><input type="submit" value="전송"></p>	
	</form>	
</body>
</html>