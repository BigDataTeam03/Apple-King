package com.javalec.util;

public class ShareVar {
	

	/*--------------------------------------
	 * Description : Share variables 
	 * Author 	   :	 big 3
	 * Date 	   : 2024.02.02
	 * Warning 	   : 수정하면 슬랙에 공지해주세요.
	 * Update------------------------------- 
	 * <2024.02.03> by PDG
	 *	1. appleStore -> apple_store ( mysql export error 해결)
	 *	2.
	 *-------------------------------------- 
	 */
	// 학원에서 사용하는 url 
//	public static String url_mysql = "jdbc:mysql://192.168.50.4/apple_store?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
	
	// 개인 DB용 url 
	public static String url_mysql = "jdbc:mysql://localhost/apple_store?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
	public static String id_mysql = "root";
	public static String pw_mysql = "qwer1234";
	
	
	
	
}
