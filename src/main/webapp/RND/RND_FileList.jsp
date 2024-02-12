<%@page import="java.net.URLEncoder"%>
<%@page import="dto.FileDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.FileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	--------------------------------------------------------------
	* Description 	: RND file list 
			Detail	:
					1. Forwarded by : RND_fileUploadMain.jsp
					2. form submit 
						-> name 
						-> file 
						-> cate		
						-> attachedFile
					2. MultipartRequest importing
	* Author 		: PDG 
	* Date 			: 2024.02.12
	* ---------------------------Update---------------------------		
	* <<2024.02.12>> by PDG
	--------------------------------------------------------------
	*/
%>

<!DOCTYPE html>


<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<h2> db 에 등록된 파일 목록 보기 </h2>
		<a href ="RND_fileUploadMain.jsp">파일 등록하기 </a>
		<%
			FileDao dao = new FileDao();
			List<FileDto> fileLists = dao.fileList();
		%>
		<table border ="1">
			<tr>
				<th>No</th><th>작성자</th><th>제목</th><th>카테고리</th>
				<th>원본파일명</th><th>저장된 파일명</th><th>작성일</th><th></th>
			</tr>
			
		<%
			for (FileDto f : fileLists){	
		%>	
			<tr>
				<td><%= f.getIdx() %></td>
				<td><%= f.getName() %></td>
				<td><%= f.getTitle() %></td>
				<td><%= f.getCate() %></td>
				<td><%= f.getOfile() %></td>
				<td><%= f.getSfile() %></td>
				<td><%= f.getPostdate() %></td>
				<td> <a href ="Download.jsp?oName=
						<%= URLEncoder.encode(f.getOfile(),"UTF-8")%>
						&sName=<%= URLEncoder.encode(f.getSfile(),"UTF-8") %>">
						
						[다운로드]</a>
						</td>
				
			</tr>
		<%
			}
		%>			
			
						
		</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</body>
</html>