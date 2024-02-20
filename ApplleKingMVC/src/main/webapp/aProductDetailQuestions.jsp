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
    body {
        font-family: Arial, sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border: 1px solid #ccc; /* 테두리 스타일 및 색상 */
    }
    h2 {
        margin-top: 0;
        font-size: 24px;
        color: #333;
    }
    #openModalBtn {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    #openModalBtn:hover {
        background-color: #0056b3;
    }
    #questions {
        margin-top: 20px;
    }
    .question {
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 20px;
        margin-bottom: 20px;
    }
    .question h3 {
        margin-top: 0;
        font-size: 18px;
        color: #333;
    }
    .question p {
        margin: 5px 0;
        color: #666;
    }
    .question .answer {
        background-color: #f7f7f7;
        padding: 10px;
        border-radius: 5px;
        margin-top: 10px;
    }
    .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
    }
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        border-radius: 5px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
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
    .modal-content form label {
        display: block;
        margin-bottom: 10px;
        color: #333;
    }
    .modal-content form textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        resize: vertical;
    }
    .modal-content form input[type="submit"] {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .modal-content form input[type="submit"]:hover {
        background-color: #0056b3;
    }
    
       table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
  
</style>
</head>
<body>
    <div class="container">
        <h2>상품 문의 게시판</h2>
        
        <!-- 문의 작성 버튼 -->
        <button id="openModalBtn">문의 작성</button>
        
        <!-- 문의 내용을 보여주는 영역 -->
        <div id="questions"></div>
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
