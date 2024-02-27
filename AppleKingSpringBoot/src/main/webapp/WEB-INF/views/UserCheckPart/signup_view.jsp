<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	/*
		Description :  회원 가입 페이지 
			Details	: 
				Date: 2024.02.35
			Author	: diana
			Update 	: 
				<<2024.02.28 by pdg>>
				1. 스프링화 함. 
				2. 테스트를 위한 value 값들 삭제
	*/
%>

<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
     <form name="signupForm" action="signup" method="post">
         <div class="mb-3">
             <label for="id" class="form-label">아이디 :</label>
             <input type="text" class="form-control" id="id" name="id"  
             		placeholder="아이디 중복체크를 이용하세요" required>
            <!-- <a href="javascript:checkDuplicateId()" class="btn btn-outline-secondary">중복체크</a>  -->
             <button type="button" id="idCheckBtn" class="btn btn-default">중복제크</button>
         </div>
         <div class="mb-3">
             <label for="pw" class="form-label">비밀번호 :</label>
             <input type="password" class="form-control" id="pw" name="pw"  required>
         </div>
         <div class="mb-3">
             <label for="confirmpw" class="form-label">비밀번호 확인:</label>
             <input type="password" class="form-control" id="confirmpw" name="confirmpw"  required>
  			 <p id="passwordMessage"></p>           
         </div>
         <div class="mb-3">
             <label for="name" class="form-label">이름 :</label>
             <input type="text" class="form-control" id="name" name="name"  required>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">전화번호 :</label>
             <input type="text" class="form-control" id="tel" name="tel"  required>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">이메일 :</label>
            <input type = "text" name = "email" size = "10"  required> @
     
            <select name = "domain" style = vertical-align:middle>
					<option value = "@naver.com">naver.com</option>
					<option value = "@yahoo.com">yahoo.com</option>
					<option value = "@gmail.com">gmail.com</option>
					<option value = "@aumn.net">daumn.net</option>
			</select><br>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">주소 :</label>
             <input type="button" name="address" id="address" size="100px" onClick="addressForm()" value ="주소찾기" >
             <input type="text" class="form-control" id="useraddress" name="useraddress" value="경기도 하남시" readonly="readonly" required>
         </div>
         	<button type="button" onClick="validateForm()" class="btn btn-primary">회원가입</button>
     </form>
     <!-- signupjs 실행 -->
     
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	 <script src="resources/js/signup.js"></script>
	 
	 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	 <script src="resources/js/address1.js"></script>

	 <!-- 비밀번호 일치여부를 실시간으로 확인해주는 ajax -->
	 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
     <script>
        $(document).ready(function(){
            $('#confirmpw').on('input', function(){
                var password = $('#pw').val();
                var confirmPassword = $(this).val();

                if(password === confirmPassword){
                    $('#passwordMessage').html('비밀번호 일치').css('color', 'green');
                } else {
                    $('#passwordMessage').html('비밀번호 불일치').css('color', 'red');
                }
            });
        });
     </script>
</body>
</html>