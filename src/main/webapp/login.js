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
					alert(responseData)
					location.reload();// page 새로고침
					}
			}//Success end
		})//Ajax end
    });//Click end
});//Document end

// 처음 화면 실행시 아이디 입력란 cursor focus. 
 window.onload = function() {
            document.getElementById("id").focus(); // id 입력란에 포커스 설정
        };
document.getElementById("id")
	.addEventListener("keyup", function(e){
		if (e.keyCode ===13){
			//document.getElementById("loginBtn").click();
		}//If end
	})//AddEvenetListener end

//ID pw 입력란에 아무것도 안쳤을때 나오는 경고 + 내용 입력됨을 확인 -> true return  
function validateForm(userID,userPW) {
    let form = document.loginForm;
	let resultIDchecked= false;
	let resultPWchecked= false;
    if (userID === "") {
        alert("아이디를 입력해주세요.");
        form.id.focus();
        return false
    }else{
		resultIDchecked= true;
	}
    if (userPW === "") {
        alert("비밀번호를 입력해주세요.");
        form.pw.focus();
        return false
    }else{
		resultPWchecked= true;
	}
    if(resultIDchecked && resultPWchecked){
		return true
	}else{
		return false
	}
}

// 로그인 버튼 클릭
$(document).ready(function() {
	
	$("#loginBtn").click(function() {
			let form = document.loginForm
			let userID = form.id.value.trim();
			let userPW = form.pw.value.trim();
		
		if(validateForm(userID,userPW)){
			alert("validation ")
			$.ajax({
				url :"",
				data:"",
				type: "post",
				dataType:"json",
				success :function(){
					alert("id 정보를 보냈습니다. ")	
				}//Success end
			})//Ajax end
		} // If end
	})
})





