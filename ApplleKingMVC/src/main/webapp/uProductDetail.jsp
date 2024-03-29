<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ='top_user.jsp' %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<%/*
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
* <<2024.02.14>> by LS
		 1. 상품 목록에 있는 상품명을 클릭했을 때 db에 있는 다중 detailimagename 불러오기
--------------------------------------------------------------
*/ %>
<!--producrtDetailCommand 에서 세션에 저장한 상품 상세 정보 dto  -->
<c:set var="dto" value="${detailSession}" />

<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	    <link rel = "stylesheet" href ="proDetailStyle.css"> 
	</head>
	<body>
		<input type= "hidden" id ="product_qty" value ="${product_qty}" />
    	<div class="detailContainer">
		    <div class="row justify-content-center"> <!-- 가운데 정렬을 위한 클래스 추가 -->
		        <div class="col-md-7"> <!-- 가운데 정렬을 위해 너비를 조정 -->
		            <div class="product-details">
		                <div class="product-image">
		                  <c:set var="detailImageNames" value="${dto.detail_image_name}" />
								 <c:forTokens items="${detailImageNames}" delims="," var="imageName">
                        				<img src="image/${imageName}" alt="상품 이미지">
                    			 </c:forTokens>
							</div>
		                <div class="product-info">
		                	<span style="font-weight: bold; font-size: 35px;">
   								  <%= session.getAttribute("product_name") %>
							</span><br>
		                    원산지: <%= session.getAttribute("origin") %><br>
		                    <span style="font-weight: bold; font-size: 25px; color: red;">
		                        <fmt:formatNumber value="${price}" type="number" pattern="###,###"/>원
		                    </span>
	                        <span style="color: red;">(100g당 <fmt:formatNumber value="${dto.price /(dto.weight *100)}" pattern= "#" />원)<br /></span>
	                        <span style="font-weight: bold;">
		                    크기:  <%= session.getAttribute("size") %><br>
		                    무게:  <%= session.getAttribute("weight") %>kg<br><br><br>
		                    </span>
		                    
		                    <form name="cartForm" action="cartInsert.do" method ="post">
		                    <span style="font-weight: bold;">	
	                        구매 수량 :<input type="number" id ="cart_qty"  name="cart_qty" min="1" max ="${product_qty }" value ="1" style="margin-left: 10px"><br><br>
	                        </span>
	                        		 <input type="submit" 
	                        		 	    id = "cart_button"
	                        		 	    class="btn btn-primary" 
	                        		        value="장바구니담기" />
		                    </form><br><br>
		                    <form action="purchase.do" method="post">
		                    		 <input type="submit" 
	                        		        value="바로결제" />
		                    </form>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	<br>
	<br>
	<br>
	<div>
	<!--  문의 게시판 출력 -->
  <jsp:include page="uProductDetailQuestions.jsp"/>
	 
	</div>
    <!-- 상세페이지 탭 -->
        <div class="container">
	        <ul class="nav nav-tabs" id="myTab" role="tablist">
	            <li class="nav-item" role="presentation">
	                <button class="nav-link active" 
	                		id="details-tab" 
	                		data-bs-toggle="tab" 
	                		data-bs-target="#details" 
	                		type="button" 
	                		role="tab" 
	                		aria-controls="상세정보" 
	                		aria-selected="true">상세정보</button>
	            </li>
	            <li class="nav-item" role="presentation">
	                <button class="nav-link" 
	                		id="reviews-tab" 
	                		data-bs-toggle="tab" 
	                		data-bs-target="#reviews" 
	                		type="button" 
	                		role="tab" 
	                		aria-controls="상품평" 
	                		aria-selected="false">상품평</button>
	            </li>
	            <li class="nav-item" role="presentation">
	                <button class = "nav-link" 
	                		id="questions-tab" 
	                		data-bs-toggle="tab" 
	                		data-bs-target="#questions" 
	                		type="button" 
	                		role="tab" 
	                		aria-controls="상품문의" 
	                		aria-selected="false">상품문의</button>
	            </li>
	        </ul>
	        <div class="tab-content" id="myTabContent">
	            <div class="tab-pane fade show active" 
	            	 id="상세정보" 
	            	 role="tabpanel" 
	            	 aria-labelledby="details-tab">
	                <!-- 상세정보 내용 -->
	                <p>Welcome to GeeksforGeek.</p>
	            </div>
	            <div class="tab-pane fade" 
	            	 id="상품평" 
	            	 role="tabpanel" 
	            	 aria-labelledby="reviews-tab">
	                <!-- 상품평 내용 -->
	                <p>Hello Everyone.</p>
	            </div>
	            <div class="tab-pane fade" 
	            	 id="상품문의" 
	            	 role="tabpanel" 
	            	 aria-labelledby="questions-tab">
	                <!-- 상품문의 내용 -->
        	        <p>수민씨는 집에가고싶다.</p>
	                
	            </div>
	        </div>
    	</div>
   	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="uProductDetail.js?var=1"></script>
  
<!--  	<script>
	// 카트에 넣기 전, 선택한 수량을 가져와서 재고와 비교 후 가능,불가능 여부 체크
	function qtyCheck(form) {
	//let form = document.cartForm
    // 선택한 상품의 수량 가져오기
        var quantity = parseInt(form.elements["cart_qty"].value);
        // 상품의 재고 수량 가져오기
        var stock = parseInt("${product_qty}");

    			alert("상품재고 " + stock)
        // 수량이 재고보다 큰지 확인
        if (quantity > stock) {
            alert("재고가  선택하신 상품보다 부족합니다.");
        } else {
            //form.submit();
        }
    }
	</script>	 -->	
	
</body>