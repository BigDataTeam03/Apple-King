<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file ='top_admin.jsp' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


			<!--
	--------------------------------------------------------------
	* Description 	: customer CRUD
	* Author 		: PDG & KBS
	* Date 			: 2024.02.02
	* ---------------------------Update---------------------------		
	* <<2024.02.06>> by KBS
		1. 고객 정보 테이블 출력
	
	--------------------------------------------------------------
	-->


<head>
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
         #sortOption {
        position: absolute;
        top: 0;
        right: 0;
        margin-top: 145px;
        margin-right: 280px;
    }
		
        </style>
</head>
<body>

		
		<p><strong><h2>고객 목록 </h2></strong> 
		<!-- 전체 고객 정보 리스트 출력 -->
		<div id = "custList"></div> 
		
		<div>
			<br>
			<input 	type ="text" id ="name" placeholder ="상품 검색">
			<button id ="searchBtn">검색</button>
			
		</div>
		
		<div>
			   <select id="sortOption">
            		 <option value="dateNew">가입최신순</option>
            		  <option value="dateLate">가입오래된순</option>
            		  <option value="rankHigh">등급높은순</option>
           			 <option value="rankLow">등급낮은순</option>
               </select>
		</div>
			
										
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js?ver=1"></script>
		<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>	
		<script src ="aCustomerList.js?ver=1"> </script>
	
 		<footer>
        	<p>&copy; 2024 Apple Store. All rights reserved.</p>
    	</footer>
			

</body>
</html>