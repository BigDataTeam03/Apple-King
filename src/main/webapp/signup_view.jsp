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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="signup.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="address1.js"></script>
<script type="text/javascript">
    
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
            let pwcheck = signupForm.pwcheck.value.trim();
            if (pw === "") {
                alert("비밀번호 확인을 입력해주세요.");
                signupForm.pw.focus();
                return false;
            }
            
            // Validate Passworderror
            if (signupForm.pw.value != signupForm.pwcheck.value ) {
                alert("입력하신 비밀번호가 일치하지 않습니다.");
                signupForm.pw.focus();
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
            let email = signupForm.email.value.trim()+signupForm.domain.value.trim();
            if (email === "") {
                alert("이메일을  입력해주세요.");
                signupForm.email.focus();
                return false;
            }
            
         	// Validate address
            let address = signupForm.address.value.trim()
            if (address === "") {
                alert("주소를  입력해주세요.");
                signupForm.address.focus();
                return false;
            }
            
                    
            
	    	// Validate ID (only alphanumeric, up to 10 characters)
	        let idRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{1,10}$/;
			if (!idRegex.test(id)) {
  		   		alert("아이디는 영어와 숫자를 조합하여 10자 이내로 입력해주세요.");
    			form.id.focus();
    			return ;
	        }
	
	        // Validate Password (alphanumeric and special characters, up to 10 characters)
	        let pwRegex = /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-])[a-z\d!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{1,10}$/;
	  		 if (!pwRegex.test(pw)) {
    	   		alert("비밀번호는 영어 소문자, 숫자, 특수문자를 모두 포함하여 10자 이내로 입력해주세요.");
    	  		form.pw.focus();
    	 		return ;	
	        }
	    	
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
	      
	    }
		/////-------------------------------------------------////
	    // 아이디 중복체크
 	  /*   function checkDuplicateId() {
	    	 let signupForm = document.signupForm; */
		 /*        
	     	// Validate ID
            let id = form.id.value.trim();
            if (id === "") {
                alert("아이디를 입력해주세요.");
                signupForm.id.focus();
                return false;
            } */
	    	
	   /*  	
	    	window.location.href = "checkid.do?id="+id;
	    	
	    }	   */   
	 	
	    // Server-side value passed to client-side JavaScript
/*         let serverId = "${serverId}";
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
        */
	</script>
</head>
<body>
     <form name="signupForm" action="signup.do" method="post">
     	
         <div class="mb-3">
             <label for="id" class="form-label">아이디 :</label>
             <input type="text" class="form-control" id="id" name="id" value="sumink411" 
             		placeholder="아이디 중복체크를 이용하세요" required>
            <!-- <a href="javascript:checkDuplicateId()" class="btn btn-outline-secondary">중복체크</a>  -->
             <button type="button" id="idCheckBtn" class="btn btn-default">중복제크</button>
         </div>
         <div class="mb-3">
             <label for="pw" class="form-label">비밀번호 :</label>
             <input type="password" class="form-control" id="pw" name="pw" value="qwer!123" required>
         </div>
         <div class="mb-3">
             <label for="confirmpw" class="form-label">비밀번호 확인:</label>
             <input type="password" class="form-control" id="confirmpw" name="confirmpw" value="qwer!123" required>
  			 <p id="passwordMessage"></p>           
         </div>
         <div class="mb-3">
             <label for="name" class="form-label">이름 :</label>
             <input type="text" class="form-control" id="name" name="name" value ="김수민" required>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">전화번호 :</label>
             <input type="text" class="form-control" id="tel" name="tel" value="010-8765-9764" required>
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">이메일 :</label>
            <input type = "text" name = "email" size = "10" value = "411sumin" required> @
     
            <select name = "domain" style = vertical-align:middle>
					<option value = "@naver.com">naver.com</option>
					<option value = "@yahoo.com">yahoo.com</option>
					<option value = "@gmail.com">gmail.com</option>
					<option value = "@aumn.net">daumn.net</option>
						
				</select><br>
			
         </div>
         <div class="mb-3">
             <label for="tel" class="form-label">주소 :</label>
             <input type="button" name="address" id="address" size="100px" onClick="addressForm()" value ="주소찾기" >
             <input type="text" class="form-control" id="useraddress" name="useraddress" value="경기도 하남시" readonly="readonly" required>
         </div>
         
         	<button type="button" onClick="validateForm()" class="btn btn-primary">회원가입</button>
     </form>

	 <!-- 비밀번호 일치여부를 실시간으로 확인해주는 ajax -->
	 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
     <script>
        $(document).ready(function(){
            $('#confirmpw').on('input', function(){
                var password = $('#pw').val();
                var confirmPassword = $(this).val();

                if(password === confirmPassword){
                    $('#passwordMessage').html('비밀번호 일치').css('color', 'green');
                } else {
                    $('#passwordMessage').html('비밀번호 불일치').css('color', 'red');
                }
            });
        });
     </script>
</body>
</html>