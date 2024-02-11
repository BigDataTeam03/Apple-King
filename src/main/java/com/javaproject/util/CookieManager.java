package com.javaproject.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	/*--------------------------------------
	 * Description : Cookie 를 관리함. 
	 * Author 	   : pdg
	 * Date 	   : 2024.02.10
	 * Details		
	 * 	1. makeCookie ( response,cName,cValue,cTime)
	 *  2. readCookie(request, cName)
	 *  3. deleteCookie(request,cName)
	 * Update------------------------------- 
	 * <2024.02.10> by PDG
	 *-------------------------------------- 
	 */
	
	
	// Method
	
	// 명시한 이름, 값, 유지 기간 조건으로 새로운 쿠키를 생성. 
	public static void makeCookie( HttpServletResponse response, 
			String cName,
			String cValue,
			int cTime) {
		
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}// Make cookie end
	
	//
	public static String readCookie(HttpServletRequest request, String cName) {
		
		String cookieValue =""; // return value
		
		Cookie [] cookies =request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies ) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {// 있는 쿠키중에 내가 찾는 쿠키
					cookieValue = c.getValue();
					
				}// If end
			}//For end
		}// If end
		return cookieValue;
	}// Readcookie end

	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "",0);
	}
	
} //END
