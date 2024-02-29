
/*
		Description : 회원 가입 페이지 정규식 JS
		Details		: 
		Date 		: 2024.02.25
		Author		: pdg
		Update 		: 2024.02.25 
			<<2024.02.25  by pdg>>
			1.  기존에 mvc 에서 servlet 호출 방식으로 중복체크하던것을 변환 -> SignUpUserOverlapChk
*/
// id 중복 체크 
$(document).ready(function() {
	//버튼 클릭시 AJAX 요청
	$("#idCheckBtn").click(function() {
		let id = $("#id").val()
		$.ajax({
			type: "POST",
			url: "SignUpUserOverlapChk",
			data: {
				id: id
			},
			success: function(response) {
				//서버에서 받은 응답 처리
				if (response === true) {
					alert("중복된 아이디입니다")
				} else {
					alert("사용 가능한 아이디입니다")
				}
			},
			error: function(xhr, status, error) {
				alert(" 문제가 발생했습니다." + error)
			}
		})
	})
})

// 회원가입 유효성 검사.
function validateForm() {
	let signupForm = document.signupForm;
	// Validate ID
	let id = signupForm.id.value.trim();
	if (id === "") {
		alert("아이디를 입력해주세요.");
		signupForm.id.focus();
		return false;
	}

	// Validate Password
	let pw = signupForm.pw.value.trim();
	if (pw === "") {
		alert("비밀번호를 입력해주세요.");
		signupForm.pw.focus();
		return false;
	}

	// Validate Passwordcheck
	let pwcheck = signupForm.confirmpw.value.trim();
	if (pwcheck === "") {
		alert("비밀번호 확인을 입력해주세요.");
		signupForm.confirmpw.focus();
		return false;
	}

	// Validate Passworderror
	if (pw != pwcheck) {
		alert("입력하신 비밀번호가 일치하지 않습니다.");
		signupForm.confirmpw.focus();
		return false;
	}

	// Validate name
	let name = signupForm.name.value.trim();
	if (name === "") {
		alert("이름을 입력해주세요.");
		signupForm.name.focus();
		return false;
	}

	// Validate tel
	let tel = signupForm.tel.value.trim();
	if (tel === "") {
		alert("전화번호를 입력해주세요.");
		signupForm.tel.focus();
		return false;
	}
	// Validate email
	let email = signupForm.email.value.trim() + signupForm.domain.value.trim();
	if (email === "") {
		alert("이메일을  입력해주세요.");
		signupForm.email.focus();
		return false;
	}

	// Validate address
	let address = signupForm.useraddress.value.trim()
	if (address === "") {
		alert("주소를  입력해주세요.");
		signupForm.useraddress.focus();
		return false;
	}
/*
	// Validate ID (only alphanumeric, up to 10 characters)
	let idRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,10}$/;
	if (!idRegex.test(id)) {
		alert("아이디는 영어와 숫자를 조합하여 10자 이내로 입력해주세요.");
		signupForm.id.focus();
		return;
	}

	// Validate Password (alphanumeric and special characters, up to 10 characters)
	let pwRegex = /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-])[a-z\d!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
	if (!pwRegex.test(pw)) {
		alert("비밀번호는 영어 소문자, 숫자, 특수문자를 모두 포함하여 10자 이내로 입력해주세요.");
		signupForm.pw.focus();
		return;
	}
*/
	// Validate Name (only Korean, up to 5 characters)
	let nameRegex = /^[가-힣]{1,5}$/;
	if (!nameRegex.test(name)) {
		alert("이름은 한글로만 5자 이내로 입력해주세요.");
		signupForm.name.select();
		return false;
	}

	// Validate Tel (numeric 3 digits - numeric 3 or 4 digits - numeric 4 digits)
	let telRegex = /^\d{3}-\d{3,4}-\d{4}$/;
	if (!telRegex.test(tel)) {
		alert("전화번호는 '숫자 3자리-숫자3이나4자리-숫자4자리' 형식으로 입력해주세요.");
		signupForm.tel.select();
		return false;
	}


	// Validate Email (numeric 3 digits - numeric 3 or 4 digits - numeric 4 digits)
	let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	if (!emailRegex.test(email)) {
		alert("이메일 주소를 올바른 형식으로 입력해주세요.");
		signupForm.email.select();
		return false;
	}

	signupForm.submit();

function addressForm(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('useraddress').value = data.address;
        }
    }).open();
}



}





