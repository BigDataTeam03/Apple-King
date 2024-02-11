<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>My Page</title>
<!-- Bootstrap CSS -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center mt-3">
        <div class="col-md-6 text-center"> 
            <img src="image/logo.png" class="img-fluid" style="max-width: 200px;">
        </div>
    </div>
</div>
<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-md-7"> 
      <div class="card">
        <div class="card-body text-center">
           <h4>안녕하세요, 회원님!</h4><br>
           <h5>등급: <input type="text" id="rank" name="rank" readonly style="border: none; background-color: transparent;"></h5><br>
          <button type="button" class="btn btn-outline-success btn-lg btn-block" onclick="redirectToMyInfo()">내 정보</button>
         <button type="button" class="btn btn-outline-info btn-lg btn-block" onclick="redirectToMyOrder()">주문 내역</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="text-center mt-3">
  <button type="button" class="btn btn-primary" onclick="redirectToMain()">뒤로가기</button>
</div>


<!-- Functions of the "내정보.주문내역.뒤로가기" buttons -->
<script>
    function redirectToMyInfo() {
        window.location.href = 'myInfo.do';
    }
    
    function redirectToMyOrder() {
        window.location.href = 'myOrder.do';
    }
    
    function redirectToMain() {
        window.location.href = 'cGoHome.do';
    }
</script>

</body>
</html>
