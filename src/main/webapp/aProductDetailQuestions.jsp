<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 문의 게시판</title>



<!--
--------------------------------------------------------------
* Description 	: User ProductDEtailQuestions page
*  Detail		: 상품 상세페이지(문의 부분)
* Author 		: KBS
* Date 			: 2024.02.13
* ---------------------------Update---------------------------		
* <<2024.02.13>> by KBS
*		 1. 주석  및 정리
         2. 문의 작성을 위해 모달 사용
         
--------------------------------------------------------------
-->	
<style>
    /* CSS 스타일링 */
    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }
    .question {
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-bottom: 20px;
        padding: 10px;
    }
    .question h3 {
        margin-top: 0;
    }
    .question p {
        margin-bottom: 5px;
    }
    .question .answer {
        background-color: #f2f2f2;
        padding: 10px;
        border-radius: 5px;
    }
    
    .modal {   
   /* 모달의 초기값은 안보이는 상태로    */
        display: none;
        position: fixed;
        z-index: 1000;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.5); /* 어두운 배경 */
    }
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        border-radius: 5px;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
    }
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>상품 문의 게시판</h2>
        
        <!-- 문의 작성 버튼 -->
        <button id="openModalBtn">문의 작성</button>
        
        <!-- 문의 내용을 보여주는 영역 -->
        <div id="questions">
            <!-- 여기에 각 문의 내용과 답변이 나타날 것입니다. -->
        </div>
    </div>
     
    <!-- 모달 -->
    <!--모달은 웹 페이지에서 새로운 작은 창을 열어서 입력을 받거나 정보를 부여줄 때 사용함-->
   <!-- 문의 를 할 때 버튼을 누루면 모달 창이 팝업 되면서 배경이 암전된다 -->
   <!-- 배경이 암전되는 기능을 CSS 로 표현 -->
    <div id="insertQuestion" class="modal">
        <!-- 모달 컨텐츠 -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>문의 작성</h3>
            <form id="questionForm">
                <label for="name">상품 이름: <%=session.getAttribute("product_name") %></label>                        
                <label for="question">문의 내용:</label><br>
                <textarea id="question" name="question" rows="4" required></textarea><br>
                <input type="submit" value="작성">
            </form>
        </div>
    </div>
 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="aProductDetailQuestion.js"></script>

</body>
</html>
