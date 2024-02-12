
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ='top_admin.jsp' %>

<%
	// jsp 내장객체
	// 파일의 절대경로 구하는 
	ServletContext context = getServletContext();
	//String realfolder = context.getRealPath(savefolder);
%>
	<!--
	--------------------------------------------------------------
	* Description 	: Admin CRUD
	* Author 		: PDG & KBS
	* Date 			: 2024.02.02
	* ---------------------------Update---------------------------		
	* <<2024.02.04>> by PDG
		1. css 좀 함.. footer, top 추가 하고 사과 색깔로 맞춤. 
		2. 입력하다 수정하다 왔다 갔다 할수있게끔 어떻게 해야할까?
	 <<2024.02.04>> by PDG &KBS
		1. 삭제 기능 추가 
		2. 입력 기능 추가 
		3. undefined 고치기 
	* <<2024.02.12>> by KBS
		1. 검색기능과 정렬기능 연동
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
         #Sorting {
            position: absolute;
           top: 150px; /* 콤보박스의 초기 위치를 지정합니다. */
  		  
  		  right: 80px;
        }
        </style>
	</head>
	<body>
		<!--상품평 전체 조회 및 검색 결과 -->
		<p><strong><h2>등록된 상품 목록 </h2></strong> 
		
	 	총 상품 갯수 :<div 	id = "productCount"></div>  
		<div 	id = "result"></div> 
		<div>
				<!--  정렬 콤보 박스  -->
			   <select id="Sorting">
            		 <option value="stokHigh">	재고 많은순</option>
            		 <option value="stokLow"> 	재고 적은순</option>
            		 <option value="makeHigh">	생산일자 최신순</option>
           			 <option value="makeLow">	생산일자 오래된순</option>
           			 <option value="weightHigh">무게 높은순</option>
           			 <option value="weightLow">	무게 낮은순</option>
           			 <option value="viewHigh">	조회수 높은순</option>
           			 <option value="viewLow">	조회수 낮은순</option>
           			 <option value="insertHigh">등록일 최신순</option>
           			 <option value="insertLow">	등록일 오래된순</option>
           			 <option value="priceHigh">	가격 높은순</option>
           		     <option value="priceLow">	가격 낮은순</option>
           			 
               </select>
		</div>
		
<div>
    <p><strong>원산지:</strong></p>
    <input type="radio" id="korOrigin" name="origin" value="한국">
    <label for="korOrigin">한국</label>
    <input type="radio" id="chiOrigin" name="origin" value="중국">
    <label for="chiOrigin">중국</label>
    <input type="radio" id="jpOrigin" name="origin" value="일본">
    <label for="jpOrigin">일본</label>
    <input type="radio" id="amOrigin" name="origin" value="미국">
    <label for="amOrigin">미국</label>
</div>
<div>
    <p><strong>사이즈:</strong></p>
    <input type="radio" id="lSize" name="size" value="대과">
    <label for="lSize">대과</label>
    <input type="radio" id="mSize" name="size" value="중과">
    <label for="mSize">중과</label>
    <input type="radio" id="sSize" name="size" value="소과">
    <label for="sSize">소과</label>
</div>
<div>
    <p><strong>품종:</strong></p>
    <input type="radio" id="kind1" name="kind" value="부사">
    <label for="kind1">부사</label>
    <input type="radio" id="kind2" name="kind" value="홍옥">
    <label for="kind2">홍옥</label>
    <input type="radio" id="kind3" name="kind" value="홍로">
    <label for="kind3">홍로</label>
    <input type="radio" id="kind4" name="kind" value="아리수">
    <label for="kind4">아리수</label>
</div>

	<button id="clearRadioBtn">라디오버튼 초기화</button>
<!-- 확인 버튼 추가 -->
<button type="button" id="confirmBtn" onclick="applyFilters()">확인</button>
<div>
	<br>
	<input 	type ="text" id ="name" placeholder ="상품 검색">
	<button id ="queryButton">검색</button>
</div>
<div>
	<br>
	<button id =allBtn">전체 목록 보기</button>
</div>

<!--  -->
<hr>
<p><strong>선택 상품 수정  </strong> 
<div>
	<table border="1">
	
              <tr>
                  <td>상품코드:</td>
                  <td class="input-style"><input type="text" id="product_code" readonly="readonly" placeholder="수정불가" ></td>
              </tr>
              <tr>
                  <td>상품명:</td>
                  <td class="input-style"><input type="text" id="product_name" placeholder="상품명을 입력하세요" value="다사과"></td>
              </tr>
              <tr>
                  <td>재고수량:</td>
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
                  <td>조회수:</td>
                  <td class="input-style"><input type="text" id="view_count" readonly="readonly" placeholder="0" value="0"></td>
              </tr>
              <tr>
                  <td>상품 등록일:</td>
                  <td class="input-style"><input type="text" id="product_reg_date"  readonly="readonly" placeholder="등록일 (자동)" value = "2024-02-02"> </td>
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
                  <td>상품 가격 : </td>
                  <td class="input-style"><input type="text" id="price" placeholder="가격" value="15000"></td>
              </tr>
          </table>
</div>
		<br>
		<!--  submit 을 누르면 s~ 어쩌구가 submit 되어 js 에서 받아줌.  -->
		<button type ="button" id = "updateBtn">수정</button>
		
		<div 	id = "Print_code"></div> <!--  // result 부분을 Js 가 만들어주는구나 --> 
		
		
		<script src = "https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script src = "aProductUpdate.js"></script>
   	    <footer>
        	<p>&copy; 2024 Apple Store. All rights reserved.</p>
    	</footer>
	  <%--   <%@ include file ='footer_admin.jsp' %> --%>  

	</body>
</html>
