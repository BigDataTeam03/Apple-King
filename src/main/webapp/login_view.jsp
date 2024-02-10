<%@ page import="com.javaproject.util.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
	
	<%
		/*--------------------------------------------------------------
		* Description 	: Login 화면
				Detail	:
		    		  	
		* Author 		: PDG, LS, Diana
		* Date 			: 2024.02.05
		* ---------------------------Update---------------------------		
		* <<2024.02.09>> by PDG
		    1.  로고 디자인 너음. 
		    2. 가운데 정렬하고 css 파일 만듬. 
		    3. 버튼 클릭하지 않고 엔터만 처도 들어가게 만듬. 
		    4. MVC 가아니라 JS format 으로 다시세팅함. 
		    
		   <<2024.02.10>> by PDG
		    1. 공지사항 layer pop up 기능 추가함. 
		    2. 쿠키를 이용한 로그인 방식으로 수정함. 
		    
		--------------------------------------------------------------*/
		// Layer pop up 띄울지 여부
		String popupMode = "on"; 
		
		// Cookie 설정
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie c : cookies){
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				if (cookieName.equals("PopupClose")){
					popupMode = cookieValue; // popup mode 갱신
				}//If end
			}//For end
		}//If end
		
		String loginId = CookieManager.readCookie(request,"loginId");
		String cookieCheck = "";
		if (!loginId.equals("")){
			cookieCheck = "checked";
		}//If end
	%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Apple King</title>
    <link rel="stylesheet" href="login.css" />
    <style>
		div#popup{
			position : absolute; top:100px; left:50px; color:yellow;
			width:400px ; height : 100px; background-color :#96c0bc;
		}
		div#popup>div{
			position : relative; background-color : #ffffff; top: 0px;
			border : 1px; padding: 10px; color: black;
}		
    </style>
    <!-- POPUP -->
	
</head>
<body>
	<%
		// POP UP 
		if (popupMode.equals("on")){
	%>
			<!-- html region -->
			<div id ="popup">
				<h2 align ="center">공지사항  </h2>
				<h3> 안녕하세요 Apple king 입니다. </h3>
				<div align = "right">
					<form name = "popFrm">
						<input type="checkbox" id ="inactiveToday" value ="1" />
							하루동안 열지 않음. 
						<input type = "button" value = "닫기" id = "closeBtn"/>
					</form>
				</div>
			</div>
	<%
		} //If end
	%>
	<div class= "container">
	  	<div>
	    	<h1><img src="image/logo.png"></h1>
	  	</div>
    	<form name="loginForm" action="IdSaveProcess.jsp" method="post"> 
        	<div>
        		<!--  ID 입력란 -->
            	<input type="text" 
                   size="25" 
                   class="form-control" 
                   id="id" 
                   name="userId"
                   placeholder="아이디"
                   value ="<%= loginId %>"
                   required />
                <input type="checkbox" 
                	name ="save_check"
                	value ="Y" <%=cookieCheck %> >아이디저장하기    
        	</div>
        	<div>
        		<!--  PW 입력란 -->
            	<input type="password"
                   size="25"
                   class="form-control"
                   id="pw" 
                   name="userPw" 
                   placeholder="비밀번호"
                   required>
        	</div>
        	<div>
            	<!-- 로그인  버튼 -->
            	<input 
	            	type ="submit" 
	            	size ="40" 
	            	id="loginBtn" 
	            	value ="Log In"
	            	style="width: 100%; background-color: #33CC33; color: white;
            		  	   font-size: 18px;	">
            	<div><br></div>
        	</div>
        	<div>
            	<!-- 회원가입 버튼 -->
            	<button 
            		id="signupBtn"
            		style="width: 100%; background-color: #6633CC; color: white;
            		font-size: 18px;"
            	>Sign Up</button>
        	</div>
    	</form>
    </div>
 
    <!-- login 정규식 및 버튼 JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery. min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="login.js"></script>
</body>
</html>
