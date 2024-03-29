<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
/*--------------------------------------------------------------
* Description  : 회원가입 
* Author       : PDG, DK, LS
* Date         : 2024.02.05
* ---------------------------Update---------------------------
* <<2024.02.28>> by PDG
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
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: #f0f0f0;
    }
    form {
        max-width: 700px;
        margin: 50px auto; /* 탑 마진을 50px로 설정 */
        padding: 15px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: left; /* 아이디, 비밀번호 등의 요소를 왼쪽 정렬로 변경 */
    }
    .form-group {
        margin-bottom: 20px;
        display: flex;
        align-items: center;
        flex-wrap: wrap; /* 줄 바꿈을 위해 추가 */
        justify-content: space-between; /* 요소들을 양쪽 정렬로 변경 */
    }
    .form-label {
        font-weight: bold;
        flex-basis: 120px; /* 열의 너비를 100px로 설정 */
        margin-right: 30px; /* 라벨과 입력 필드 사이의 간격 설정 */
    }
    .form-control {
        flex: 1;
        padding: 10px 20px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-right: 20px;
    }
    .btn-primary {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    .btn-primary:hover {
        background-color: #0056b3;
    }
    #passwordMessage {
        font-size: 14px;
        margin-top: 5px;
    }
</style>

</head>
<body>
     <form name="signupForm" action="signup" method="post">
         <div class="form-group">
             <label for="id" class="form-label">아이디 :</label>
             <input type="text" class="form-control" id="id" name="id" placeholder="아이디 중복체크를 이용하세요" required>
             &nbsp;<button type="button" id="idCheckBtn" class="btn btn-default">중복체크</button>
         </div>
         <div class="form-group">
             <label for="pw" class="form-label">비밀번호 :</label>
             <input type="password" class="form-control" id="pw" name="pw" required>
         </div>
         <div class="form-group">
             <label for="confirmpw" class="form-label">비밀번호 확인:</label>
             <input type="password" class="form-control" id="confirmpw" name="confirmpw" required>
             <p id="passwordMessage"></p>           
         </div>
         <div class="form-group">
             <label for="name" class="form-label">이름 :</label>
             <input type="text" class="form-control" id="name" name="name" required>
         </div>
         <div class="form-group">
             <label for="tel" class="form-label">전화번호 :</label>
             <input type="text" class="form-control" id="tel" name="tel" required>
         </div>
         <div class="form-group">
             <label for="eamil" class="form-label">이메일 :</label>
            <input type="text" class="form-control" id="email" name="email" required style="width: 200px;"> @&nbsp;
     
            <select name="domain" class="form-control" style="width: 150px;">
                <option value="@naver.com">naver.com</option>
                <option value="@yahoo.com">yahoo.com</option>
                <option value="@gmail.com">gmail.com</option>
                <option value="@daum.net">daum.net</option>
            </select><br>
         </div>
         <div class="form-group">
             <label for="tel" class="form-label">주소 :</label>
             <input type="text" class="form-control" id="useraddress" name="useraddress" readonly="readonly" required>
             &nbsp;<input type="button" name="address" id="address" size="100px" onClick="addressForm()" value="주소찾기">
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
