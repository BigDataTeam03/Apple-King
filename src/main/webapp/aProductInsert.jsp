
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ='top_admin.jsp' %>

<%
	// jsp 내장객체
	// 파일의 절대경로 구하는 
	ServletContext context = getServletContext();
	//String realfolder = context.getRealPath(savefolder);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">

        <style>
            body {
            text-align: center;
            background-color: rgb(255, 255,200); 
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 50%; /* 테이블의 너비를 조절해 화면 가운데에 위치시킵니다. */
        }

        table, th, td {
            border: 1px solid black;
            white-space: nowrap; /* 텍스트가 줄 바꿈되지 않도록 설정 */
            padding: 8px; /* 셀의 내부 여백 설정 */
        }

        td input[type="text"] {
            width: 400px; /* 텍스트 입력란의 너비를 넓혀 사용자가 텍스트를 입력할 수 있도록 합니다. */
        }  
        td:first-child { /* 첫 번째 열의 너비를 조정 */
            width: 150px; /* 원하는 너비로 설정 */
        }
        
        td:nth-child(2) input[type="text"] {
            text-align: left;
        }
        .input-style input[type="text"] {
            width: calc(100% - 300px); /* 텍스트 입력란을 셀에 꽉 차게 설정 */
            box-sizing: border-box; /* 너비에 패딩과 테두리를 포함 */
            text-align: left; /* 입력란 텍스트를 왼쪽으로 정렬 */
            float: left; /* 입력란을 왼쪽으로 부유(floating)시킵니다. */
        }

        </style>
	
	</head>
	<body>
		
		<!--상품평 전체 조회 및 검색 결과 -->
		<p><strong><h2>상품 목록 </h2></strong> 
		<div 	id = "result"></div> 
		
		<div>
			<br>
			<input 	type ="text" id ="name" placeholder ="상품 검색">
			<button id ="queryButton">검색</button>
		</div>
		<!--  -->
		<hr>
		<p><strong>선택 상품 수정</strong> 
		<div>
			<table border="1">
			
                <tr>
                    <td>상품코드:</td>
                    <td class="input-style"><input type="text" id="product_code" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>상품명:</td>
                    <td class="input-style"><input type="text" id="product_name"></td>
                </tr>
                <tr>
                    <td>수량:</td>
                    <td class="input-style"><input type="text" id="product_qty"></td>
                </tr>
                <tr>
                    <td>원산지:</td>
                    <td class="input-style"><input type="text" id="origin"></td>
                </tr>
                <tr>
                    <td>생산일:</td>
                    <td class="input-style"><input type="text" id="manufacture_date"></td>
                </tr>
                <tr>
                    <td>무게:</td>
                    <td class="input-style"><input type="text" id="weight"></td>
                </tr>
                <tr>
                    <td>사이즈:</td>
                    <td class="input-style"><input type="text" id="size"></td>
                </tr>
                <tr>
                    <td>상세 이미지:</td>
                    <td class="input-style"><input type="text" id="detail_image_name"></td>
                </tr>
                <tr>
                    <td>조회수:</td>
                    <td class="input-style"><input type="text" id="view_count" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>상품 등록일:</td>
                    <td class="input-style"><input type="text" id="product_reg_date"></td>
                </tr>
                <tr>
                    <td>품종:</td>
                    <td class="input-style"><input type="text" id="kind"></td>
                </tr>
                <tr>
                    <td>섬네일 이미지:</td>
                    <td class="input-style"><input type="text" id="product_image_names"></td>
                </tr>
            </table>
		</div>
		<br>
		<!--  submit 을 누르면 s~ 어쩌구가 submit 되어 js 에서 받아줌.  -->
		<button type ="button" id = "updateBtn">수정</button>
		
		<button type ="button" id = "insertBtn">입력</button>
		
		<button type ="button" id = "deleteBtn">삭제</button>
		
		<div 	id = "Print_code"></div> <!--  // result 부분을 Js 가 만들어주는구나 --> 
		
		
		<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src = "adminUpdate.js"></script>
   	    <footer>
        	<p>&copy; 2024 Apple Store. All rights reserved.</p>
    	</footer>
	  <%--   <%@ include file ='footer_admin.jsp' %> --%>  

	</body>
</html>
