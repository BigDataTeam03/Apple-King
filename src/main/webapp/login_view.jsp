<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
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
    1. 공지사항 pop up
    
--------------------------------------------------------------
-->		
	<%
		String popupMode = "on"; // layer pop up 띄울지 여부
		
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
			width:400px ; height : 100px; background-color :gray;
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
    	<form name="loginForm" action="loginProcess.do" method="post"> 
        	<div>
        		<!--  ID 입력란 -->
            	<input type="text" 
                   size="25" 
                   class="form-control" 
                   id="id" 
                   name="id"
                   placeholder="아이디"
                   required>
        	</div>
        	<div>
        		<!--  PW 입력란 -->
            	<input type="password"
                   size="25"
                   class="form-control"
                   id="pw" 
                   name="pw" 
                   placeholder="비밀번호"
                   required>
        	</div>
        	<div>
            	<!-- 로그인  버튼 -->
            	<button id="loginBtn">Log In</button>
        	</div>
        	<div>
            	<!-- 회원가입 버튼 -->
            	<button id="signupBtn">Sign Up</button>
        	</div>
    	</form>
    </div>
 
    <!-- login 정규식 및 버튼 JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery. min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="login.js"></script>
</body>
</html>
