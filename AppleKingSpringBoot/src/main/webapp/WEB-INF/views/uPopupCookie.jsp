<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String popupCheckVal = request.getParameter("inactiveToday");
	if (popupCheckVal != null && popupCheckVal.equals("1")){//체크가 되있으면 1
		Cookie cookie = new Cookie("PopupClose", "off"); //cookie 생성
		cookie.setPath(request.getContextPath());
		int cookieRemainTime = 1 * 60 * 1;
							 //m * s  * h	
		cookie.setMaxAge( cookieRemainTime);// cookie 유지 기간 : 1분
		response.addCookie(cookie); // 응답 객체 에 쿠키 추가 
		out.println(" 쿠키 : "+cookieRemainTime+"동안 열지 않음. ");
	}
%>