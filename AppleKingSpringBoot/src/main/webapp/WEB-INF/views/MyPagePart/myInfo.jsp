<%@page import="java.util.ArrayList"%>
<%-- <%@page import="dto.MemberDto"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%/*
--------------------------------------------------------------
* Description 	: MyInfo Page
* Detail		: 회원정보 
* Author 		: DK
* Date 			: 2024.02.23
* ---------------------------Update---------------------------
Update : 2024.02.23 DK
*   1. 기존 jsp 로 만든 것을 jstl 을 사용하고 controller 단에서 해결하도록 변경함.
*   2. c:forEach 태그를 사용하여 myInfo 세션에 저장된 정보를 반복하여 표시함.
*	3. 주소찾기에 필요한 address1.js 는 따로 js foler에 넣어줌. 
*	4. 정보수정/회원탈퇴 기능 아직 아.무.것.도. 안함. 로그인한 유저의 정보 띄우는것만 해줌. 
Update: 2024.02.26 DK
	1. 회원정보수정/회원탈퇴 기능 구현.
	2. 회원탈퇴버튼 FORM 따로 빼줌.
--------------------------------------------------------------
*/ %>
<html>
<head>
<meta charset="UTF-8">
<title>My Info</title>
<!-- Bootstrap CSS -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/js/address1.js"></script>

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
        <h3 class="text-center mb-4">${sessionScope.userName}님의 회원정보</h3>

       <form name="modifyForm" action="modify" method="post" onsubmit="return validateForm()">
            <div class="form-group">
			    <label for="name">성명 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='name' name='name' value='${info.name}'>
			    </c:forEach>
			</div>
			<div class="form-group">
			    <label for="cust_id">아이디 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='cust_id' name='cust_id' value='${info.cust_id}' readonly>
			    </c:forEach>
			</div>
			<div class="form-group">
			    <label for="cust_pw">비밀번호 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='cust_pw' name='cust_pw' value='${info.cust_pw}'>
			    </c:forEach>
			</div>
			<div class="form-group">
			    <label for="email">이메일 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='email' name='email' value='${info.email}'>
			    </c:forEach>
			</div>
			<div class="form-group">
			    <label for="tel">전화번호 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='tel' name='tel' value='${info.tel}'>
			    </c:forEach>
			</div>
			<div class="form-group">
			    <label for="address">주소 :</label>
			    <c:forEach var="info" items="${myInfo}">
			        <input type='text' class='form-control short-text-input' id='useraddress' name='useraddress' value='${info.address}'readonly>
			        <div class="input-group-append">
			            <button type="button" class="btn btn-primary" id="editaddress" onclick="addressForm()">수정하기</button>
			        </div>
			    </c:forEach>
			</div>
			*수정 후 완료 버튼을 누르세요!<br><br>
			<div class="text-center">
			    <button type="submit" class="btn btn-primary mr-2" onclick="validateForm()">수정완료</button>
			</div>
        </form>
		<form action="deactivate" method="post">        
			<button type="submit" class="btn btn-danger">회원탈퇴</button>
		</form>
		<button type="button" class="btn btn-primary mr-2" onClick="redirectToMyPage()">뒤로가기</button>
    </div>
</div>

<script>
    
    function redirectToMyPage() {
        window.location.href = 'myPage';
    }

</script>
</body>
</html>
