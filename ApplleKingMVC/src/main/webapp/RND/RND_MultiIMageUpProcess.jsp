<!--
--------------------------------------------------------------
* Description 	: RND multi image upload 기능 
		Detail	: 
				1. 이전 페이지는 RND_MultiImageUpload.jsp 이다. 
				2. 이 페이지에서 가져온 이미지들을 멀티파트로 업로드한다. 
				
* Author 		: PDG 
* Date 			: 2024.02.07
* ---------------------------Update---------------------------		
* <<2024.02.04>> by PDG
--------------------------------------------------------------
-->			

<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ServletContext context = getServletContext();
	String realfolder = context.getRealPath("/image");
	System.out.print("image 가 들어갈 경로"+realfolder);
	int sizeLimit = 100*1024*1024;	
	MultipartRequest multi = new MultipartRequest(			 
			//<< Multi Part parameters >>
			request	  ,	 // request
			realfolder,	 // image 가 저장될 application folder 
            sizeLimit ,	 // image size limit
            "UTF-8"   ,	 // image file name utf-8 
			new DefaultFileRenamePolicy()); // 중복시 (1). ...
	
	System.out.println("--getFileNames");
	Enumeration enumeration01 = multi.getFileNames();
	while(enumeration01.hasMoreElements()){
		System.out.println(enumeration01.nextElement());
	}
	
	System.out.println("--getParameterNames");
	Enumeration enumeration02 = multi.getParameterNames();
	while(enumeration02.hasMoreElements()){
		System.out.println(enumeration02.nextElement());
	}	
	
	System.out.println("--getOriginalFileName");
	String ori1 = multi.getOriginalFileName("file1");
	String ori2 = multi.getOriginalFileName("file2");
	String ori3 = multi.getOriginalFileName("file3");
	System.out.println(ori1);
	System.out.println(ori2);
	System.out.println(ori3);
		
	System.out.println("--getParameter");
	String parameter01 = multi.getParameter("param1");
	String parameter02 = multi.getParameter("param2");
	String parameter03 = multi.getParameter("param3");
	System.out.println(parameter01);
	System.out.println(parameter02);
	System.out.println(parameter03);
	
	System.out.println("--getFilesystemName");
	String fileSysName01 = multi.getFilesystemName("file1");
	String fileSysName02 = multi.getFilesystemName("file2");
	String fileSysName03 = multi.getFilesystemName("file3");
	System.out.println(fileSysName01);
	System.out.println(fileSysName02);
	System.out.println(fileSysName03);		
	
	System.out.println("--getParameterValues");
	String[] strValues = multi.getParameterValues("hobbies");
	for(String s : strValues){
		System.out.println(s);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form result</title>
</head>
<body>
	<h1>form result</h1>
</body>
</html>