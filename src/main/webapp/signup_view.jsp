<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    body {
        text-align: center;}
    form {
        display: inline-block;
        text-align: left; }
    div {
        margin-bottom: 10px;}
</style>

<script type="text/javascript">
    
    	// 회원가입 유효성 검사.
	    function validateForm() {
	        let form = document.signupForm;
	        
	     	// Validate ID
            let id = form.id.value.trim();
            if (id === "") {
                alert("아이디를 입력해주세요.");
                form.id.focus();
                return false;
            }

            // Validate Password
            let pw = form.pw.value.trim();
            if (pw === "") {
                alert("비밀번호를 입력해주세요.");
                form.pw.focus();
                return false;
            }
            
            // Validate name
            let name = form.name.value.trim();
            if (name === "") {
                alert("이름을 입력해주세요.");
                form.name.focus();
                return false;
            }

            // Validate tel
            let tel = form.tel.value.trim();
            if (tel === "") {
                alert("전화번호를 입력해주세요.");
                form.tel.focus();
                return false;
            }
	        
	    	// Validate ID (only alphanumeric, up to 10 characters)
	        let idRegex = /^[a-zA-Z0-9]{1,10}$/;
	        if (!idRegex.test(id)) {
	            alert("아이디는 숫자와 영어로만 10자 이내로 입력해주세요.");
	            form.id.select();
	            return false;
	        }
	
	        // Validate Password (alphanumeric and special characters, up to 10 characters)
	        let pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
	        if (!pwRegex.test(pw)) {
	            alert("비밀번호는 숫자, 영어, 특수문자로 10자 이내로 입력해주세요.");
	            form.pw.select();
	            return false;
	        }
	
	        // Validate Name (only Korean, up to 5 characters)
	        let nameRegex = /^[가-힣]{1,5}$/;
	        if (!nameRegex.test(name)) {
	            alert("이름은 한글로만 5자 이내로 입력해주세요.");
	            form.name.select();
	            return false;
	        }
	
	        // Validate Tel (numeric 3 digits - numeric 3 or 4 digits - numeric 4 digits)
            let telRegex = /^\d{3}-\d{3,4}-\d{4}$/;
            if (!telRegex.test(tel)) {
                alert("전화번호는 '숫자 3자리-숫자3이나4자리-숫자4자리' 형식으로 입력해주세요.");
                form.tel.select();
                return false;
            }
	        //return true; // Form is valid
	        form.submit();
	    }
		
	    // 아이디 중복체크
	    function checkDuplicateId() {
	    	 let form = document.signupForm;
		        
	     	// Validate ID
            let id = form.id.value.trim();
            if (id === "") {
                alert("아이디를 입력해주세요.");
                form.id.focus();
                return false;
            }
	    	
	    	// Validate ID (only alphanumeric, up to 10 characters)
	        let idRegex = /^[a-zA-Z0-9]{1,10}$/;
	        if (!idRegex.test(id)) {
	            alert("아이디는 숫자와 영어로만 10자 이내로 입력해주세요.");
	            form.id.select();
	            return false;
	        }
	    	
	    	window.location.href = "checkid.do?id="+id;
	    	
	    }	    
	 	
	    // Server-side value passed to client-side JavaScript
        let serverId = "${serverId}";
        let clientId = "${clientId}";
        
        window.onload = function() {
        	
	        // Compare the values
	        if(serverId != "" && serverId == clientId){
	            alert("아이디를 사용할 수 있습니다.");
	            document.getElementById("id").readOnly = true;
	        }else if (serverId == "" && clientId != "") {
	            alert("아이디가 이미 사용 중입니다. 다른 아이디를 선택해주세요.");
	        }
    	}
	</script>
</head>
<body>
     <form name="signupForm" action="signup.do" method="post">
         <div class="mb-3">
             <label for="id" class="form-label">아이디 :</label>
             <input type="text" class="form-control" id="id" name="id" value="${clientId}" required>
             <a href="javascript:checkDuplicateId()" class="btn btn-outline-secondary">중복체크</a>
         </div>
         <div class="mb-3">
             <label for="pw" class="form-label">비밀번호 :</label>
             <input type="password" class="form-control" id="pw" name="pw" required>
         </div>
         <div class="mb-3">
             <label for="name" class="form-label">이름 :</label>
             <input type="text" class="form-control" id="name" name="name" required>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">전화번호 :</label>
             <input type="text" class="form-control" id="tel" name="tel" required>
         </div>
         	<button type="button" onClick="validateForm()" class="btn btn-primary">회원가입</button>
     </form>

</body>
</html>