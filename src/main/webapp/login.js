/*
 *  Description: login js 
 *  Author : KBS
 *  Date : 2024.02.06
 *  tag id : 
 * 		userID input : id
 * 		userPW input : pw
 * 		로그인 버튼 : loginBtn
 * 		회원가입 버튼: singupBtn
 * 		
 *  Update --------------------------------------------------
 *  <<2024.02.09 by pdg>>
 *	1. 로그인 버튼이아니라 엔터를 처도 로그인이 되게끔. 
 *----------------------------------------------------------
 */
// 처음 화면 실행시 포커스가 아이디 입력란에 있도록. 
 window.onload = function() {
            document.getElementById('id').focus(); // id 입력란에 포커스 설정
        };
document.getElementById("id")
	.addEventListener("keyup", function(e){
		if (e.keyCode ===13){
			document.getElementById("loginBtn").click();
		}
	})
//


//아무것도 안쳤을때 나오는 경고 
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
    //form.submit();
}

// 로그인 버튼 클릭
$(document).ready(function() {
	
	
	$("#loginBtn").click(function() {
			let form = document.loginForm
			let userID = form.id.value.trim();
			let userPW = form.pw.value.trim();
		
		if(validateForm(userID,userPW)){
			alert("id, pw 를 비교합니다. ")
			

	
			// DB servlet 으로 id, pw Json 으로 전송. 
			$.ajax({
				type: "POST",
				url: "loginCheckServlet",
				data: {
						userID: userID,
						userPW: userPW
						},
				dataType: "json",
				success: function(response) {
					alert(" 환영합니다. ")
				}, // success
				error : function(xhr, ststus,error){
					alert("Id 와 비밀번호를 다시 확인하세요.")
				}
			}) // ajax
		} // if
		
		
	})
})





