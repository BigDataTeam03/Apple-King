package com.javaproject.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;


@WebServlet("/JSFunction_servlet")
public class JSFunction_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public JSFunction_servlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*--------------------------------------
		 * Description : Java script 에서 중복되는 기능모음  => java class에서 servlet 으로 변환함. 
		 * Author 	   : pdg
		 * Date 	   : 2024.02.07
		 * Update------------------------------- 
		 * <2024.02.07> by PDG
		 *	1. alertLocation : 경고창 메세지 알림 이후 특정 페이지로 넘어가는 함수 
		 *	2. alertBack     : 경고ㅊ아 메세지 알림 이후 이전 페이지로 넘가는 함수
		 *-------------------------------------- 
		 * <<2024.02.15>> by pdg 
		 *  1. alert 하고 request 를 받아서 전달해야하는 일이 생김.  서블릿 버전으로 다시 만듬. 
		 *
		 */
		// Field
		// Constructor
		// Method 
	}
	public static void alertMessage(String message, JspWriter out) {
		System.out.println(">> alertLocation 실행 :"+ message);
		try{
			String script =
					" " // 삽입할 자바 스크립트 코드
					+ "<script>"
					+ "    alert('"+ message+"');" 
					+ "</script>";
					
			out.print(script);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 페이지를 넘길때 경고창을 띄욱 특정 페이지로 넘어가게 하는 함수
	public static void alertLocation(String message, String url, JspWriter out) {
		System.out.println(">> alertLocation 실행: "+ message);
		try{
			String script =
					" " // 삽입할 자바 스크립트 코드
					+ "<script>"
					+ "    alert('"+ message+"');" 
					+ "    location.href='"+ url+"';" // 지정된 페이지로 이동한다. 
					+ "</script>";
					
			out.print(script);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}// alertLocation
	
	// 메세지 알림창을 띄운 후 이전 페이지로 돌아갑니다. 
	public static void alertBack(String message, JspWriter out) {
		try{
			String script =
					" " // 삽입할 자바 스크립트 코드
					+ "<script>"
					+ "    alert('"+ message+"');"
					+ "    history.back();"         // 전페이지로 돌아간다. 
					+ "</script>";
					
			out.print(script);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
