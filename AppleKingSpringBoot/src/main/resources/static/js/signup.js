$(document).ready(function(){
		//버튼 클릭시 AJAX 요청
		$("#idCheckBtn").click(function(){
			
		
			//입력된 데이터 가져오기
			let id =$("#id").val()
			
			//AJAX 요청
			$.ajax({
				 type: "POST",
				 url: "idUserOverlapCheck",
				 data: {
					 id : id
					 
					 },
				 success: function(response){
					 //서버에서 받은 응답 처리
					 if(response === true){
						 alert("중복된 아이디입니다")
					
					 }else {
						 alert("사용 가능한 아이디입니다")
					 }
					
				
				 },
				 error : function(xhr, status, error){
					 alert(" 문제가 발생했습니다." + error)
				 }
			})
		})		
	})
	