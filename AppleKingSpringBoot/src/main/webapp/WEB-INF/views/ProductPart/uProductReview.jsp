<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리뷰 작성</title>

<!-- <link rel="stylesheet" type="text/css" href="review.css"> -->
</head>
<style>
.separator {
    border-bottom: 1px solid lightblue; 
    margin-bottom: 20px;
}
.title-separator {
    border-bottom: 2px solid black;
    margin-bottom: 20px;
}
.stars {
  display: inline-block;
  vertical-align: middle;
  font-size: 20px; /* 별 크기 조절 */
}

.stars input[type="radio"] {
  display: none;
}

.stars label {
  cursor: pointer;
  padding: 0 1px;
}

.stars label:before {
  content: '\2605'; /* 별 모양 (★) */
  margin: 0 5px;
  color: gray; /* 기본 별 색상 설정 */
}

.stars input[type="radio"]:checked ~ label:before {
  color: orange; /* 체크된 별 색상 변경 */
}

.stars label:last-child:before {
  margin-right: 0; /* 마지막 별의 오른쪽 여백 제거 */
}
/* 리뷰 작성 버튼 스타일 */
#writeReview {
    position: absolute;
    top: 10px; /* 맨 위에서 20px만큼 떨어진 위치 */
    right: 20px; /* 맨 오른쪽에서 20px만큼 떨어진 위치 */
    padding: 8px 16px; /* 버튼의 패딩 수정 */
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

#writeReview:hover {
    background-color: #0056b3;
}

.container {
    position: relative;
    padding-top: 50px; /* 버튼 위에 50px 만큼 여백을 줌 */
}
   .STAR {
        display: flex;
        align-items: center;
    }

    .STAR input[type="radio"] {
        display: none;
    }

    .STAR label {
        cursor: pointer;
        padding: 0 5px;
        color: gray; /* 기본 별 색상 설정 */
    }

    .STAR label:before {
        content: '\2605'; /* 별 모양 (★) */
        margin: 0 3px;
        font-size: 24px; /* 별 크기 조절 */
    }

    .STAR input[type="radio"]:checked ~ label:before,
    .STAR input[type="radio"]:checked ~ label {
        color: orange; /* 선택된 별 색상 변경 */
    }
</style>
<body>


<!--
--------------------------------------------------------------
* Description 	: 	상품리뷰 페이지
*  Detail		: 리뷰페이지에서 리뷰 입력과 리스트 출력
* Author 		: KBS
* Date 			: 2024.02.27
* ---------------------------Update---------------------------		
* <<2024.02.27>> by KBS
*		 1. 리뷰 리스트 출력완료
		 2. 리뷰 등록 완료
  
--------------------------------------------------------------
-->	

<div class="container">
    <h2>상품 리뷰</h2>
  	 					<button id="writeReview">리뷰 작성</button>
     <div class="title-separator"></div>    
     	<!-- 리뷰 작성 버튼 -->
    <!-- 리뷰 목록 -->   
    
    <c:choose>
        <c:when test="${empty ListReview}">
            <p>등록된 리뷰가 없습니다.</p>
        </c:when>
        <c:otherwise>
    <c:forEach var="review" items="${ListReview}">
        <tr>
            <td> 작성자 :${review.cust_id}</td> 
            <td><br><br></td>
         
              <td> <!-- 별점 표시 -->
                <div class="stars">
                    <!-- 5개의 별을 표시하기 위해 5번 반복 -->
                        <c:forEach var="i" begin="1" end="5">
                    <c:if test="${i <= review.rating}">
                        ★
                    </c:if>
                    <c:if test="${i > review.rating}">
                        ☆
                    </c:if>
                </c:forEach>
                </div>
            </td>
            <td><br><br></td>
          
            <td>${review.review_date}</td>
            <td><br><br></td>
           
            <td>${review.product_name}</td>
            <td><br><br></td>
           
            <td>${review.review_content}</td>
            <td><br><br></td>
            <td>${review.helpful_count} 명의 사용자가 도움됬어요 </td>
            <td> 
                <form action="/goodReview" method="post">
                    <input type="hidden" name="review" value="${review.review_code}">
                    <button type="submit">도움이 돼요</button>
                </form>
            </td>   
            
            <td><br><br></td>
        </tr>
      
       <div class="separator"></div>
    </c:forEach>
    </c:otherwise>
    </c:choose>
</div>

<!-- 리뷰 작성 모달 -->
<div id="modal-container" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>상품 리뷰 작성</h2>
        <form id="reviewWrite" action="/insertReview" method="Get">
            <input type="hidden" id="product_name" name="product_name" value="${review.product_name}">
            <input type="hidden" id="product_code" name="product_code" value="${review.product_code}">
            <div>
                <label for="rating">별점:</label>
    			  <select id="rating" name="rating">
                    <option value="1">★</option>
                    <option value="2">★★</option>
                    <option value="3">★★★</option>
                    <option value="4">★★★★</option>
                    <option value="5">★★★★★</option>
                </select>
            </div>
            <div>
                <label for="content">내용:</label>
                <textarea id="content" name="content"></textarea>
            </div>
 
             <input type="submit" value="작성 완료" id="closeModal"> 
           <!--  <button type="button" id="submitReview">작성 완료</button> -->
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="resources/js/uProductDetailReview.js"></script>
</body>
</html>
