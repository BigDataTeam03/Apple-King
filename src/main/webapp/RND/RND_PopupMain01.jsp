<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
--------------------------------------------------------------
* Description 	: RND pop up 창 
		Detail	:
				1.
* Author 		: PDG 
* Date 			: 2024.02.10
* ---------------------------Update---------------------------		
-->	
	<%
		String popupMode = "on"; // layer pop up 띄울지 여부
		
		// Cookie 설정
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie c : cookies){
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				if (cookieName.equals("PopupClose")){
					popupMode = cookieValue; // popup mode 갱신
				}//If end
			}//For end
		}//If end
	%>
		
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키를 이용한 팝업관리 </title>
		<!--  팝업창 위치 조정 -->
		<style> 
			div#popup{
				position : absolute; top:100px; left:50px; color:yellow;
				width:270px ; height : 100px; background-color :gray;
			}
			div#popup>div{
				position : relative; background-color : #ffffff; top: 0px;
				border : 1px solid gray; padding: 10px; color: black;
			}	
	</style>
	</head>
<body>
	<h2>팝업 메인 페이지 </h2>
	<%
		for (int i =1 ;i <= 10 ; i++){
			
			out.print("현재 팝업창은 "+popupMode +"입니다.<br/>");
		}// For end
		if (popupMode.equals("on")){
	%>
			<!-- html region -->
			<div id ="popup">
				<h2 align ="center">공지사항 팝업입니다.  </h2>>
				<div align = "right">
					<form name = "popFrm">
						<input type="checkbox" id ="inactiveToday" value ="1" />
						하루동안 열지 않음. 
						<input type = "button" value = "닫기" id = "closeBtn"/>
					</form>
				</div>
			</div>
	<%
		} //If end
	%>
				<!--  POPUP 창의 닫기버튼, 하루동안 열지 않음 버튼 클릭시 Jquery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery. min.js"></script>
		<script>
			$(function(){
				$('#closeBtn').click(function(){
					$('#popup').hide();
				})// Click end
			})// Function end
		
		</script>

</body>
</html>