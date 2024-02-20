<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 확인 체크</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="mb-3">
        <label for="confirmpw" class="form-label">비밀번호 확인:</label>
        <input type="password" class="form-control" id="confirmpw" name="confirmpw" value="qwer!123" required>
        <p id="passwordMessage" class="text-danger"></p>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#confirmpw').on('input', function(){
                var password = $('#confirmpw').val();
                var originalPassword = 'qwer!123'; // 이 부분에 원래 비밀번호를 넣어주세요

                if(password === originalPassword){
                    $('#passwordMessage').html('비밀번호 일치').removeClass('text-danger').addClass('text-success');
                } else {
                    $('#passwordMessage').html('비밀번호 불일치').removeClass('text-success').addClass('text-danger');
                }
            });
        });
    </script>
</body>
</html>

