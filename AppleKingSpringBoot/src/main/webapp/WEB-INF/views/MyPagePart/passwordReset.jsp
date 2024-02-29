<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%/*
--------------------------------------------------------------
* Description 	: PasswordReset
* Detail		: 비밀번호 재설정을 위한 페이지
* Author 		: DK
* Date 			: 2024.02.28
* ---------------------------Update---------------------------	
* <<2024.02.22> by DK
*	1. AJAX를 사용하여 입력한 비밀번호가 올바른지 확인
*	2. 새로운 비밀번호와 확인 비밀번호를 입력하면, 두 입력 값이 일치하는지 확인하여 메시지를 표시
*	3. "비밀번호 변경" 버튼을 클릭 -> 입력한 정보가 서버로 전송되어 비밀번호가 변경.
--------------------------------------------------------------
*/ %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Reset</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">비밀번호 재설정</h2>
        
        <form action="updatePassword" method="post">
            <div class="form-group">
                <label for="oldPassword">기존 비밀번호:</label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                <p id="dbpasswordMessage"></p>    
            </div>
            <div class="form-group">
                <label for="newPassword">새로운 비밀번호:</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">새로운 비밀번호 확인:</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                <p id="passwordMessage"></p>    
            </div>
            <button type="submit" class="btn btn-primary">비밀번호 변경</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
    var typingTimer;
    var doneTypingInterval = 400; // Time in milliseconds (1 second)

    $(document).ready(function(){
        $('#oldPassword').keyup(function(){
            clearTimeout(typingTimer); // Clear the previous timer

            typingTimer = setTimeout(function(){
                var oldPassword = $('#oldPassword').val(); // Get the value of the old password input field

                // AJAX call
                $.ajax({
                    type: 'POST',
                    url: 'validateOldPassword',
                    data: { oldPassword: oldPassword },
                    success: function(response) {
                        if (response === 'match') {
                            
                            $('#dbpasswordMessage').html('기존 비밀번호 일치').css('color', 'green');
                        } else {
                            
                            $('#dbpasswordMessage').html('기존 비밀번호 불일치').css('color', 'red');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                        $('#dbpasswordMessage').html('에러가 발생했습니다').css('color', 'red');
                    }
                });
            }, doneTypingInterval); // Set the timer to trigger after the specified delay
        });
    });
    
    
          $(document).ready(function(){
            $('#confirmPassword').on('input', function(){
                var newPassword = $('#newPassword').val();
                var confirmPassword = $(this).val();

                if(newPassword === confirmPassword){
                    $('#passwordMessage').html('비밀번호 일치').css('color', 'green');
                } else {
                    $('#passwordMessage').html('비밀번호 불일치').css('color', 'red');
                }
            });
        });
    </script>
</body>
</html>