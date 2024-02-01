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
<script>
function validateForm() {
    let form = document.loginForm;
    
    // Validate ID
    let id = form.id.value.trim();
    if (id === "") {
        alert("아이디를 입력해주세요.");
        form.id.focus();
        return false;
    }

    // Validate Password
    let pw = form.pw.value.trim();
    if (pw === "") {
        alert("비밀번호를 입력해주세요.");
        form.pw.focus();
        return false;
    }
 function signupStartForm(){
	 
 }   
	// Validate ID (only alphanumeric, up to 10 characters)
    let idRegex = /^[a-zA-Z0-9]{1,10}$/;
    if (!idRegex.test(id)) {
        alert("아이디는 숫자와 영어로만 10자 이내로 입력해주세요.");
        form.id.select();
        return false;
    }

    // Validate Password (alphanumeric and special characters, up to 10 characters)
    let pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
    if (!pwRegex.test(pw)) {
        alert("비밀번호는 숫자, 영어, 특수문자로 10자 이내로 입력해주세요.");
        form.pw.select();
        return false;
    }

    form.submit();
}

</script>
</head>
<body>

	<h3>로그인</h3>
	
	<form name="loginForm" action="login.do" method="post"></form>
	
	<div>
		아이디 : <input type="text" class="form-control" id="id" name="id" required><br>
		패스워드 : <input type="password" class="form-control" id="pw" name="pw" required><br>		
	</div>
	
	
	<button type="button" class="btn btn-primary" onclick="validateForm()">로그인</button>
	<form action="signupStart.do">
	<input type="submit" class="btn btn-primary" value="회원가입"></input>
	</form>
    	
    	
</body>
</html>