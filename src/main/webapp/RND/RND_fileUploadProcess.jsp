<%@page import="dto.FileDto"%>
<%@page import="dao.FileDao"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/*
	--------------------------------------------------------------
	* Description 	: RND file uploadProcess 
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
<% 
	String saveDirectory = application.getRealPath("/image");
	//out.print(saveDirectory);
	int maxPostSize = 1024* 1000*10; // 10MB
	String encoding = "UTF-8";
	
	try {
		// Multi Part Requst
		MultipartRequest mr = new MultipartRequest(			 
				request	  ,	 	 // request
				saveDirectory,	 // image 가 저장될 application folder 
				maxPostSize ,	 // image size limit
				encoding  ,	 	 // image file name utf-8 
			new DefaultFileRenamePolicy()); // 중복시 (1). ...
	
		String fileName = mr.getFilesystemName("attachedFile");
		//out.print(fileName);
		
		//file 확장자 ( xxx.xxx.png 같은 경우도 있음. )
		String ext =fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
		
		//다른 form value 받기
		String title = mr.getParameter("title");
		String name = mr.getParameter("name");
		String[] cateArray = mr.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();

		// 카테고리 선택된 것드을 배열로 받아서 string buffer 로 만든후 , 로 이어줌. 
		if (cateArray ==null) {
			cateBuf.append("선택없음");
		}else {
			for (String s: cateArray){
				cateBuf.append(s+ ",");
			}
		}
		String newFileName = now+title+ext ;
		//out.print(newFileName);
		
		//file name change
		File oldFile = new File(saveDirectory + File.separator + fileName);
		File newFile = new File(saveDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		// DTO 생성
		FileDto dto = new FileDto();
		dto.setName(name);
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(fileName);
		dto.setSfile(newFileName);
		dto.setPostdate(now);
		
		
		// Dao 를 통해 데이터베이스에 반영
		FileDao dao = new FileDao();
		dao.insertFile(dto);
		
		response.sendRedirect("RND_FileList.jsp");
	}catch(Exception e){
		e.printStackTrace();
		request.setAttribute("errorMessage","파일 업로드 오류");
		request.getRequestDispatcher("FileUploadMain.jsp").forward(request,response);
		
	}

%>




<%	
/*	
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
*/
%>	





