package com.javaproject.util;

import javax.servlet.jsp.JspWriter;

public class JSFunction {
		/*--------------------------------------
		 * Description : Java script 에서 중복되는 기능모음 
		 * Author 	   : pdg
		 * Date 	   : 2024.02.07
		 * Update------------------------------- 
		 * <2024.02.07> by PDG
		 *	1. alertLocation : 경고창 메세지 알림 이후 특정 페이지로 넘어가는 함수 
		 *	2. alertBack     : 경고ㅊ아 메세지 알림 이후 이전 페이지로 넘가는 함수
		 *-------------------------------------- 
		 */
		
		// Field
	
		// Constructor
	
		// Method 
	
		// 페이지를 넘길때 경고창을 띄욱 특정 페이지로 넘어가게 하는 함수
		public static void alertLocation(String massage, String url, JspWriter out) {
			System.out.println(">> alertLocation 실행");
			try{
				String script =
						" " // 삽입할 자바 스크립트 코드
						+ "<script>"
						+ "    alert('"+ massage+"');" 
						+ "    location.href='"+ url+"';" // 지정된 페이지로 이동한다. 
						+ "</script>";
						
				out.print(script);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 메세지 알림창을 띄운 후 이전 페이지로 돌아갑니다. 
		public static void alertBack(String massage, JspWriter out) {
			try{
				String script =
						" " // 삽입할 자바 스크립트 코드
						+ "<script>"
						+ "    alert('"+ massage+"');"
						+ "    history.back();"         // 전페이지로 돌아간다. 
						+ "</script>";
						
				out.print(script);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
	
}
