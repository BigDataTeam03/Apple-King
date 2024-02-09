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
--------------------------------------------------------------
-->		
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Apple King</title>
    <link rel="stylesheet" href="login.css" />

</head>
<body>
	<div class= "container">
	  	<div>
	    	<h1><img src="image/logo.png"></h1>
	  	</div>
    	<form name="loginForm" action="" method="post"> 
        	<div>
            	<input type="text" 
                   size="25" 
                   class="form-control" 
                   id="id" 
                   name="id"
                   placeholder="아이디"
                   required>
        	</div>
        	<div>
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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="login.js"></script>
</body>
</html>
