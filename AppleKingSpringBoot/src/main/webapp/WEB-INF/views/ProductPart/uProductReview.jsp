<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리뷰 작성</title>
<!-- <link rel="stylesheet" type="text/css" href="review.css"> -->
</head>
<body>
    <div class="container">
        <h2>상품 리뷰</h2>
        <!-- 리뷰 목록 -->
               
      
     		<%--   	<c:if test="${empty reviewList}">
   					 <div>등록된 리뷰가 없습니다.</div>
				</c:if>  --%>
		
                <c:forEach var="review" items="${ListReview}">
                     <tr>           		
                        <td>${review.cust_id}</td> 
                        <td><br></td>
                        <td>${review.rating}</td>
                        <td><br></td>
                        <td>${review.review_date}</td>
                        <td><br></td>
                        <td>${review.product_name}</td>
                        <td><br></td>
                        <td>${review.review_image}</td>
                        <td><br></td>
                        <td><br></td>
                        <td>${review.review_content}</td>
                        <td><br></td>
                        <td><br></td>
                        <td>${review.helpful_count} 명의 사용자가 도움됬어요 <form action="goodReview" method="post">
             			   												<input type="hidden" name="review" value="${review.review_code}">
              				  											<button type="submit">도움이 돼요</button>
            															</form>
            			</td>   
                    	<td><br></td>
                        <td><br></td>
                         <td>
           				
        				</td>
                        </tr>
    				   
                </c:forEach>
            
    
    </div>
    <!-- 모달 -->
<%--     <div id="insertQuestion" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>문의 작성</h3>
            <form id="questionForm">
                <label for="productName">상품 이름: </label>
                <input type="text" id="productName" name="productName" value="${sessionScope.product_name}" readonly><br><br>
                <label for="question">질문 내용:</label><br>
                <textarea id="question" name="question" rows="4" required></textarea><br><br>
                <input type="submit" value="작성">
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="review.js"></script> --%>
</body>
</html>
