
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ='top_admin.jsp' %>

	<!--
	--------------------------------------------------------------
	* Description 	: Admin CRUD
	* Author 		: PDG & KBS
	* Date 			: 2024.02.05
	* ---------------------------Update---------------------------		
	* <<2024.02.04>> by PDG
		1. css 좀 함.. footer, top 추가 하고 사과 색깔로 맞춤. 
		2. 입력하다 수정하다 왔다 갔다 할수있게끔 어떻게 해야할까?
	 <<2024.02.05>> by PDG &KBS
		1. 입력기능 추 
		2.  
		3.  
		
			기능 설명 : 상품코드를 불러오기 위해 js 를 사
	--------------------------------------------------------------
	-->
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

	<div 	id = "productCount"></div> 
		<!--상품평 전체 조회 및 검색 결과 -->
		<p><strong><h2>상품 목록 </h2></strong> 
		
		<hr>
		<p><strong> 상품 입력</strong> 
		<div>
			<table border="1">
			
                <tr>
                    <td>상품코드:</td>
                    <td class="input-style"><input type="text" id="product_code"  placeholder="수정불가" ></td>
                </tr>
                <tr>
                    <td>상품명:</td>
                    <td class="input-style"><input type="text" id="product_name" placeholder="상품명을 입력하세요" value="다사과"></td>
                </tr>
                <tr>
                    <td>수량:</td>
                    <td class="input-style"><input type="text" id="product_qty" placeholder="수량 입력" value="0"></td>
                </tr>
                <tr>
                    <td>원산지:</td>
                    <td class="input-style"><input type="text" id="origin" placeholder="원산지" value="한국"></td>
                </tr>
                <tr>
                    <td>생산일:</td>
                    <td class="input-style"><input type="text" id="manufacture_date" placeholder="생산일" value="2024-02-02"></td>
                </tr>
                <tr>
                    <td>무게(kg):</td>
                    <td class="input-style"><input type="text" id="weight" placeholder="무게" value="15"></td>
                </tr>
                <tr>
                    <td>사이즈:</td>
                    <td class="input-style"><input type="text" id="size" placeholder="사이즈" value="대"></td>
                </tr>
                <tr>
                    <td>상세 이미지:</td>
                    <td class="input-style"><input type="text" id="detail_image_name" placeholder="수정불가" value="asdf.png"></td>
                </tr>
                <tr>
                    <td>상품 등록일:</td>
                    <td class="input-style"><input type="text" id="product_reg_date"  readonly="readonly" placeholder="등록일 (자동)"></td>
                </tr>
                <tr>
                    <td>품종:</td>
                    <td class="input-style"><input type="text" id="kind" placeholder="품종" value="부사"></td>
                </tr>
                <tr>
                    <td>섬네일 이미지:</td>
                    <td class="input-style"><input type="text" id="product_image_names" placeholder="이미지파일" value="asdf.png"></td>
                </tr>
                 <tr>
                    <td>가격 :</td>
                    <td class="input-style"><input type="text" id="price" placeholder="가격" value="15000"></td>
                </tr>
            </table>
		</div>
		<br>
		<!--  submit 을 누르면 s~ 어쩌구가 submit 되어 js 에서 받아줌.  -->		
		<button type ="button" id = "insertBtn">입력</button>
		
		<div 	id = "Print_code"></div> <!--  // result 부분을 Js 가 만들어주는구나 --> 
		
		
		<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src = "aProductUpdate.js"></script>
   	    <footer>
        	<p>&copy; 2024 Apple Store. All rights reserved.</p>
    	</footer>
	  <%--   <%@ include file ='footer_admin.jsp' %> --%>  

	</body>
</html>
