<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	 <style>
    .logo-img {
        max-height: 100px; 
    }
     .navbar-custom {
    background-color: #FDE5B8;
  	}

</style>
		<meta charset="UTF-8">
	</head>
	<body>
	  <nav class="navbar navbar-expand-lg navbar-light navbar-custom fixed-top">
	        <a class="navbar-brand" href="cGoHome.do">
	            <img src="image/logo.png" class="logo-img">
	        </a>
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
	</nav>
	</body>	
</html>