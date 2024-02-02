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

        // Validate ID (only alphanumeric, up to 10 characters)
        let idRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,10}$/;
		if (!idRegex.test(id)) {
  		    alert("아이디는 영어와 숫자를 조합하여 10자 이내로 입력해주세요.");
    		form.id.focus();
    		return ;
        }

        // Validate Password (alphanumeric and special characters, up to 10 characters)
       let pwRegex = /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-])[a-z\d!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
	   if (!pwRegex.test(pw)) {
    	   alert("비밀번호는 영어 소문자, 숫자, 특수문자를 모두 포함하여 10자 이내로 입력해주세요.");
    	   form.pw.focus();
    	   return ;
        }

        	form.submit();
    }
    
    
</script>


</head>
<body>

	<h3>로그인</h3>
	
	<form name="loginForm" action="login.do" method="post"> 
	
	<div>
	아이디 : <input type="text" class="form-control" id="id" name="id" required><br>
	패스워드 : <input type="password" class="form-control" id="pw" name="pw" required><br>		
	</div>
	
	
	
	<input type="button" class="btn btn-primary" onclick="validateForm();" value="로그인"/>
	</form>
	<!-- 회원가입 페이지로 넘어가는 버튼 -->
	<form name="signupForm" action="signupStart.do">
		<input type="submit" class="btn btn-primary" value="회원가입"></input>
	</form>
    	
    	
</body>
</html>