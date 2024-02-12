<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// 이미지 업로드 db table  : image_upload
	
	// columns : idx, name, title, cate, ofile, sfile, postdate

	// dto.FileDto 
%>


<html>
	<head>
		<meta charset="UTF-8">
		<title>form</title>
	</head>
	<script>
		function validateForm(form){
			
			if (for.name.value == ""){
				alert("작성자를 입력하세요.")
				form.name.focus()
			}
		}
	</script>
	
	<body>
		<h1>file upload</h1>
		<span style="color : red;">${errorMessage }</span>
		
		<form name ="fileForm"
			  method="post" 
			  enctype="multipart/form-data"
			  action="RND_fileUploadProcess.jsp"
			  onsubmit =" return validateForm(this)" >
			작성자 : <input type ="text"
						  name ="name"
						  value="머스트해브"/> <br />
						
			제목 : <input type ="text"
						  name ="title"/> <br />
			카테고리(선택사항):
				  <input type="checkbox" name ="cate" value="사진" checked /> 사진
				  <input type="checkbox" name ="cate" value="과제"  /> 과제
				  <input type="checkbox" name ="cate" value="워드"  /> 워드
				  <input type="checkbox" name ="cate" value="음원"  /> 음원<br />
				  
			첨부파일 : 
				  <input type="file" name ="attachedFile" /><br />
				  <input type="submit" value="전송">	
		</form>	
	</body>
</html>