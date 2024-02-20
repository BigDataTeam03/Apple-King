/*
 *  Description: login page java script  
 *  Author : PDG
 *  Date : 2024.02.06
 *  Document tag id 정보-------------------------------------- 
 * 		userID input : id
 * 		userPW input : pw
 * 		로그인 버튼 : loginBtn
 * 		회원가입 버튼: singupBtn
 * 		팝업 창 오늘하루안보기 체크 : inactiveToday
 * 		
 *  Update --------------------------------------------------
 *  <<2024.02.09 by pdg>>
 *	1. 로그인 버튼이아니라 엔터를 처도 로그인이 되게끔.
 * 	<<2024.02.10 by pdg>>
 *	1. 팝업창 닫기 버튼 추가 
 	2. login validation Form 수정
 *----------------------------------------------------------
 */
// 팝업 닫기 버튼 
$(document).ready(function() {
    $('#closeBtn').click(function() {
        $('#popup').hide();
        
        // 오늘 하루 안보기 체크 여부( 문법 중요!! )
        var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
        $.ajax({
			url : 'uPopupCookie.jsp', //쿠키 페이지에 요청 
			type : 'get',
			data : {inactiveToday: chkVal},
			dataType: "text",
			success: function(responseData){// 응답시 호출 
				if(responseData !='') {
					//alert(responseData)
					//location.reload();// page 새로고침
					}
			}//Success end
		})//Ajax end
    });//Click end
});//Document end

$(document).ready(function(){
	$('#signupBtn').click(function(){
		alert("회원가입창으로 이동합니다. ")
		location.href = "signup_view.jsp"
		
	})//Btn cliked end
	
})// Document ready end














