<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ='top_user.jsp' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: User product detail page
*  Detail		: 상품 상세 정보 페이지
* Author 		: PDG, LS, Diana
* Date 			: 2024.02.07
* ---------------------------Update---------------------------		
* <<2024.02.09>> by PDG
*		 1. 주석 정리
		 2. 가격 -> 컴마로 바꾸어줌 JSTL 사용 
		 3. 카트로 넘어가기 전에 디비에 저장되게끔 하는 기능 추가. 
		 
--------------------------------------------------------------
-->	
<c:set var="dto" value="${detailSession}" />
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>상품 상세 페이지</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
	    <link rel = "stylesheet" href ="uProductDetail.css">
	</head>
	<body>
	<%
	String price = session.getAttribute("price").toString();
	
	%>
    	<div class="container">
		    <div class="row justify-content-center"> <!-- 가운데 정렬을 위한 클래스 추가 -->
		        <div class="col-md-6"> <!-- 가운데 정렬을 위해 너비를 조정 -->
		            <div class="product-details">
		                <div class="product-image">
		                    <img src="image/${dto.detail_image_name}" alt="상품 이미지">
		                </div>
		                
		                <div class="product-info">
		                    상품명: <%= session.getAttribute("product_name") %><br>
		                    원산지: <%= session.getAttribute("origin") %><br>
		                    가격:  <fmt:formatNumber value="${price}" type="number" pattern="###,###"/> 원<br>
		                    크기:  <%= session.getAttribute("size") %><br>
		                    무게:  <%= session.getAttribute("weight") %>kg<br><br>
		                    
		                    <form name="cartForm" action="cartInsert.do" method ="post">
		                        구매 수량 :<input type="number" name="cart_qty" min="1" max="50" value ="1"><br><br>
		                        <input type="submit" class="btn btn-primary" value="장바구니담기"></input>
		                    </form><br><br>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	
	
    <!-- 상세페이지 탭 -->
        <div class="container">
	        <ul class="nav nav-tabs" id="myTab" role="tablist">
	            <li class="nav-item" role="presentation">
	                <button class="nav-link active" id="details-tab" data-bs-toggle="tab" data-bs-target="#details" type="button" role="tab" aria-controls="details" aria-selected="true">상세정보</button>
	            </li>
	            <li class="nav-item" role="presentation">
	                <button class="nav-link" id="reviews-tab" data-bs-toggle="tab" data-bs-target="#reviews" type="button" role="tab" aria-controls="reviews" aria-selected="false">상품평</button>
	            </li>
	            <li class="nav-item" role="presentation">
	                <button class="nav-link" id="questions-tab" data-bs-toggle="tab" data-bs-target="#questions" type="button" role="tab" aria-controls="questions" aria-selected="false">상품문의</button>
	            </li>
	        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="details" role="tabpanel" aria-labelledby="details-tab">
                <!-- 상세정보 내용 -->
                <p>Welcome to GeeksforGeek.</p>
            </div>
            <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                <!-- 상품평 내용 -->
                <p>Hello Everyone.</p>
            </div>
            <div class="tab-pane fade" id="questions" role="tabpanel" aria-labelledby="questions-tab">
                <!-- 상품문의 내용 -->
                <p>Learn cool stuff.</p>
            </div>
        </div>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const tabs = document.querySelectorAll('.nav-link');
            tabs.forEach(tab => {
                tab.addEventListener('click', function() {
                    const tabContentId = this.getAttribute('aria-controls');
                    const tabContent = document.getElementById(tabContentId);
                    const activeTab = document.querySelector('.tab-pane.show.active');
                    activeTab.classList.remove('active', 'show');
                    tabContent.classList.add('active', 'show');
                });
            });
        });
    </script>
	
	<script>
		  	 document.addEventListener('DOMContentLoaded', function() {
		     // input 요소 가져오기
		     var input = document.getElementById('spinner');
			
		     // 입력 이벤트 리스너 추가
		     input.addEventListener('input', function(event) {
		     // 입력된 값에서 숫자 이외의 문자 제거
		     var sanitizedValue = input.value.replace(/\D/g, '');
		
		     // 최소값과 최대값 사이의 값으로 제한
		     sanitizedValue = Math.max(1, Math.min(50, sanitizedValue));
		
		     // 입력된 값을 제한된 값으로 설정
		     input.value = sanitizedValue;
    	});
	});
	</script>
</body>