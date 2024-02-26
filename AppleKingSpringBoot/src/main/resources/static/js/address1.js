function addressForm(){
 new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('useraddress').value = data.address;
        }
    }).open();
}



function validateForm() {
    alert("정규식 입장!!!!!!!")
    let modifyForm = document.modifyForm;
  

    alert("1")

    // Validate Password
    let pw = modifyForm.cust_pw.value.trim();
    if (pw === "") {
        alert("비밀번호를 입력해주세요.");
        modifyForm.cust_pw.focus();
        return false;
    }

    alert("2")

    // Validate name
    let name = modifyForm.name.value.trim();
    if (name === "") {
        alert("이름을 입력해주세요.");
        modifyForm.name.focus();
        return false;
    }

    alert("3")

    // Validate tel
    let tel = modifyForm.tel.value.trim();
    if (tel === "") {
        alert("전화번호를 입력해주세요.");
        modifyForm.tel.focus();
        return false;
    }

    alert("4")

    // Validate email
    let email = modifyForm.email.value.trim();
    if (email === "") {
        alert("이메일을 입력해주세요.");
        modifyForm.email.focus();
        return false;
    }

    alert("5")


    // Validate Name (only Korean, up to 5 characters)
    let nameRegex = /^[가-힣]{1,5}$/;
    if (!nameRegex.test(name)) {
        alert("이름은 한글로만 5자 이내로 입력해주세요.");
        modifyForm.name.focus();
        return false;
    }

    // Validate Password (alphanumeric and special characters, up to 10 characters)
    let pwRegex = /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-])[a-z\d!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
    if (!pwRegex.test(pw)) {
        alert("비밀번호는 영어 소문자, 숫자, 특수문자를 모두 포함하여 10자 이내로 입력해주세요.");
        modifyForm.cust_pw.focus();
        return false;
    }


    alert("9")

    // Validate Tel (numeric 3 digits - numeric 3 or 4 digits - numeric 4 digits)
    let telRegex = /^\d{3}-\d{3,4}-\d{4}$/;
    if (!telRegex.test(tel)) {
        alert("전화번호는 '숫자 3자리-숫자3이나4자리-숫자4자리' 형식으로 입력해주세요.");
        modifyForm.tel.focus();
        return false;
    }

    alert("10")

    // Validate Email
    let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailRegex.test(email)) {
        alert("이메일 주소를 올바른 형식으로 입력해주세요.");
        modifyForm.email.focus();
        return false;
    }

    alert("11")

    // If all validations pass, submit the form
    modifyForm.submit();
   
}
