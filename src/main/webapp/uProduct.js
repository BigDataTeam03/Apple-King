window.onload = function () {
    $.ajax({
        type: "POST",
        url: "uProductListServlet",
        data: { name: "" },
        dataType: "json",
        success: function (response) {
            createCard(response);
        },
    });
};

function createCard(data) {
    let resultContainer = $("#result");
    //alert("나는 실행된다. ")

    for (let i = 0; i < data.length; i++) {
		
        let cardHtml = `
           <div class="card">            
               <img src="image/${data[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                       <a href="productDetail.do?product_name=${data[i].product_name}&price=${data[i].price}
                        		&origin=${data[i].origin}&size=${data[i].size}&weight=${data[i].weight}"> 
                        	${data[i].product_name}
                       	</a>
                    </h5>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
             `;
        resultContainer.append(cardHtml);
    }
}

// 검색버튼을 눌렀을 때 실행되는 JQuery, document(jsp)가 로드되었을때(ready)-> function (){} 을 실행한다.
$(document).ready(function() {
	
		// document 내부에 #html 중 queryButton(검색) 이라는 id 가  click 될떄 실행하는 function(){}
		$("#searchButton").click(function() {
			
			// 입력된 데이터 가져오기
			let name = $("#product_name").val()
			
			/* AJAX 요청 */
			$.ajax({
				//post 방식으로 보낸다
				type: "POST",
				//기능을 실행하는 Servlet 으로 보낸다
				url: "uProductListServlet",
				//name 값을 받아 name 으로 보낸다
				data: {name : name},
				//연결 성공시 테이블 목록도 바로 변경사항을 적용해야 하기위해 테이블을 다시 받아온다
				success: function(response) {
					/* 서버에서 받은 응답 처리 */
					createCard(response)
				}
			})
		})		
	})
