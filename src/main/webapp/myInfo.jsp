<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Info</title>
<!-- Bootstrap CSS -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="address1.js"></script>
<style>
    .short-text-input {
        width: 350px; /* Adjust width as needed */
    }
    .form-group label {
        font-weight: bold;
    }
</style>

</head>
<body>
<div class="row justify-content-center align-items-center h-100" style="margin-top: 5.5vh;">
       <div class="col-md-3">
        <h3 class="text-center mb-4">회원정보</h3>

        <form>
            <div class="form-group">
                <label for="name">성명 :</label>
                <input type="text" class="form-control short-text-input" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="cust_id">아이디 :</label>
                <input type="text" class="form-control short-text-input" id="cust_id" name="cust_id" readonly>
            </div>
            <div class="form-group">
                <label for="cust_pw">비밀번호 :</label>
                <input type="text" class="form-control short-text-input" id="cust_pw" name="cust_pw">
            </div>
            <div class="form-group">
                <label for="email">이메일 :</label>
                <input type="text" class="form-control short-text-input" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="tel">전화번호 :</label>
                <input type="text" class="form-control short-text-input" id="tel" name="tel">
            </div>
            <div class="form-group">
                <label for="address">주소 :</label>
                <div class="input-group">
                <input type="text" class="form-control short-text-input" id="useraddress" name="useraddress">
                <div class="input-group-append">
            	<button type="button" class="btn btn-primary" id="editaddress" onClick="addressForm()">수정하기</button>
        		</div>
        		</div>
            </div>
        	*수정 후 완료 버튼을 누르세요!<br><br> 

            <div class="text-center">
                <button type="button" class="btn btn-primary mr-2" id="submitBtn">완료</button>
                <button type="button" class="btn btn-danger" id="deactivateBtn">회원탈퇴</button>
  				<button type="button" class="btn btn-primary mr-2" onclick="redirectToMyPage()">뒤로가기</button>
            </div>
        </form>
    </div>
</div>


<script>
    
    function redirectToMyPage() {
        window.location.href = 'myPage.do';
    }

</script>

</body>
</html>

