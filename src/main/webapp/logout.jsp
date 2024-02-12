<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	/*--------------------------------------------------------------
	* Description 	: Login out process 
			Detail	:
	    		  	
	* Author 		: PDG
	* Date 			: 2024.02.11
	* ---------------------------Update---------------------------		
	* <<2024.02.11>> by PDG
	
	--------------------------------------------------------------*/
	//1. 회원 인증 정보 속성 삭제 
	session.removeAttribute("userId");
	session.removeAttribute("userName");
	session.removeAttribute("userRank");
	// 세션 모두삭제 
	session.invalidate();
	
	response.sendRedirect("loginStart.do");
	

%>
		
