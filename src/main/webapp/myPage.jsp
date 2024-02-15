<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Page</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        padding-top: 100px; 
    }
    #userId {
        font-size: 24px; 
        font-weight: bold; 
    }
   
    .logo-img {
        max-height: 100px; 
    }
</style>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
	        <a class="navbar-brand" href="cGoHome.do">
	            <img src="image/logo.png" class="logo-img">
	        </a>
	    <div class="container">
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
	            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarNav">
	            <ul class="navbar-nav ml-auto">
	             	<li class="nav-item">
	                    <a class="nav-link" href="uCartList.do">장바구니</a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="myPage.do">마이페이지</a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="logout.do">로그아웃</a>
	                </li>
	            </ul>
	        </div>
	    </div>
	</nav>
	<br><br>
	<div class="container mt-5">
	    <div class="row justify-content-center">
	        <div class="col-md-8">
	            <div class="card">
	                <div class="card-body">
	                    <span id="userId" class="text-primary"><%=session.getAttribute("userId")%></span>님
	                    <h5 class="card-title mt-2">등급: <%=session.getAttribute("userRank") %></h5>
	                    <a class="nav-link" href="myInfo.do">내 정보 수정하기</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

</body>

</html>
