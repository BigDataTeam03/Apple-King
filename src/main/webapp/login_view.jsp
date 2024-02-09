<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<style>
    body {
        text-align: center;}
    form {
        display: inline-block;
        text-align: left; }
    div {
        margin-bottom: 10px;}
</style>

</head>
<body>
	<h3>로그인</h3>
	<form name="loginForm" action="login.do" method="post"> 
		<div>
			아이디 :  	<input type="text" size ="20" 
						class="form-control" 
						id="id" 
						name="id"
						placeholder ="아이디를 입력하세요 "
						required><br>
						<button id="submit">Submit</button>
			패스워드 :		<input type="password" size ="10" class="form-control" id="pw" name="pw" required><br>		
		</div>
		<input type="button" class="btn btn-primary" onclick="validateForm();" value="로그인"/>
	</form>
	<!-- 회원가입 페이지로 넘어가는 버튼 -->
	<form name="signupForm" action="signupStart.do">
		<input type="submit" class="btn btn-primary" value="회원가입"></input>
	</form>
	
<!--  login 정규식  및 버튼 JS  -->
		
		<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<script src = "login.js"></script>
<script>
    // script start
    function validateForm() {
        let form = document.loginForm;

        // Validate ID
        let id = form.id.value.trim();
        if (id === "") {
            alert("아이디를 입력해주세요.");
            form.id.focus();
            return ;
        }

        // Validate Password
        let pw = form.pw.value.trim();
        if (pw === "") {
            alert("비밀번호를 입력해주세요.");
            form.pw.focus();
            return ;
        }
        	form.submit();
    }
</script>
</body>
</html>