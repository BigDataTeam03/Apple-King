<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 

	
	

	ServletContext context = getServletContext();
	String realfolder = context.getRealPath("/image");
    System.out.print("image 가 들어갈 경로"+realfolder);
	int sizeLimit = 100*1024*1024;		//100MB 제한
	MultipartRequest multi = new MultipartRequest(			 //<< Multi Part parameters >>
												request	  ,	 // request
												realfolder,	 // image 가 저장될 application folder 
                                                sizeLimit ,	 // image size limit
                                                "UTF-8"   ,	 // image file name utf-8 
            	             new DefaultFileRenamePolicy()); // 중복시 (1). ...






%>	