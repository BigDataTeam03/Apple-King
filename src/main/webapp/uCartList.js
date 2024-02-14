/* Description: DB 에서 불러온 장바구니 리스트를 조회하고 수정 후 구매 페이지로 보낸다
 * Author : PDG, KBS
 * Date : 2024.02.08
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.08 by KBS>>
 *	1. 주석 달음.
 *	2. 아이디에 해당하는 카트 조회 
 *	3. 카트 삭제기능 추가
 *	4. 수량선택 가능
 *	5. 전체선택가능 	
 *----------------------------------------------------------
 *
 * Update --------------------------------------------------
 * <<2024.02.09 by KBS>>
 *  1. 수량선택기능을 데이터베이스에 즉각반영시킴
 *  2. 상품의 총 가격을 표시
 * 
 * <<2024.02.09 by pdg>
 *  1. cust id 를 세션값에서 받아오게끔 만듬. 
 *  
 * <<2024.02.11 by KBS>
 *  1. 수량 수정, 총 가격문제 해결
 *  2  재고 초과시 메세지 출력 (추후 기능 추가 : 상품의 이름, 재고량 표시)
 */
// 페이지 실행후 바로 장바구니 전체 조회
window.onload = function() {
	
	$.ajax({
		
		// post method server request
		type: "POST",
		
		// target server page(Servlet) url
		url: "uCartListServlet",
		
		// request data (JSON)
		data: { cust_id: "sumin123" }, 
		
		// response data type -> JSON
		dataType :"json",
		
		// server response success  -> response(Json data)
		success: function(response) {
			createTable(response);
	
		},	
	});
};


// 테이블 생성하는 함수
function createTable(data) {
    //검색해온 데이터(dtos -> json -> Array  변환)
    dataReal = Array.from(data)

  //$("#cartList").empty();
  
    let table =
        "<table border='1'>" +
        "<tr>" +
        "<th>상품명</th>" +
        "<th>수량</th>" +
        "<th>이미지</th>" +
         "<th>가격</th>" +
        "</tr>";
        
    //총 가격 변수지정
  var totalPrice = 0;
    // insert data rows
    for(let i=0; i<data.length; i++)  {
        table += "<tr>" +
            "<td>" + data[i].product_name + "</td>" + // col1
            "<td>" +
            "<button  onclick='decreaseQuantity(this)'>-</button>" + // "-" 버튼
            "<input type='number' class='quantity-input' name='cartQty' value='" + data[i].cart_qty + "' min='1' readonly>" + // 수량을 입력할 수 있는 input 태그
            "<button  onclick='increaseQuantity(this)'>+</button>" + // "+" 버튼
            "</td>" + 
            "<td>" + data[i].product_image_names + "</td>" + // col3
            "<td>" + data[i].price + "</td>" + // col4
            "<td><input type='checkbox' name='selectProduct' value='" + data[i].cart_code + "'></td>" + // 체크박스 열
            "</tr>"
            //상품의 가격을 수량만큼 곱한다
             var productTotalPrice = data[i].price * data[i].cart_qty;
    	    // 총 가격 합산
             totalPrice += productTotalPrice;
    }
    
    // table end
    table += "</table>"
    
    	
    
    
    // html result <- table
    $("#cartList").html(table);
    
    
    // html prouct count 컨텐츠에 총 상품 수량을 넣어줌. 
    document.querySelector('#cartTot').innerText = data.length;
    
     // 총 가격 출력
    document.querySelector('#totalPrice').innerText = totalPrice;
    
    
    
    
  
}

// "+" 버튼 클릭 시 수량 증가
function increaseQuantity(button) {
    var input = button.parentNode.querySelector('.quantity-input');
    //변수지정
    var currentValue = parseInt(input.value);
    //input 에 +1 추가
    input.value = currentValue + 1;
    //수량체크 매소드로 보낸다
    updateQuantity(input);
}

// "-" 버튼 클릭 시 수량 감소
function decreaseQuantity(button) {
    var input = button.parentNode.querySelector('.quantity-input');
    //변수지정
    var currentValue = parseInt(input.value);
    //0일수는 없다
    if (currentValue > 1) {
		//input 에 -1 추가
        input.value = currentValue - 1;
        // 수량체크 메소드로 보낸다
        updateQuantity(input);
    }
}

// 수량 변경 시 서블릿에 전송하는 함수
function updateQuantity(input) {
    var cartCode = $(input).closest("tr").find("input[name='selectProduct']").val();
    var quantity = $(input).val();
    $.ajax({
        type: "post",
        url: "uCartQtyUpdateServlet",
        data: {
            cartCode: cartCode,
            quantity: quantity
        },
        success: function(response) {       
            // 테이블 업데이트   
             	$.ajax({
						type: "POST",
						//다시 테이블 조회
						url: "uCartListServlet",
						data: { cust_id: "sumin123" },
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							createTable(response)//jason
						}
					})		       
        },
        error: function(xhr, status, error) {
            // 에러 처리
            console.error("선택하신 상품의 재고가 부족합니다:", error);
        },   complete: function(response) {
			// 서블릿에서 준 실패 프린트
            if (response.responseText === "수량초과") {
                alert("재고가 부족해요.");
            }
        }
        
    });
}


 // 전체 선택 버튼 클릭 시 동작
    $(document).on("click", "#cartAllSelectBtn", function() {
        // 체크박스 선택 여부에 따라 전체 선택 또는 해제
        $("input[name='selectProduct']").prop("checked", true);
    });



//상품 테이블 삭제를 요청하는 메소드
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#cartDeleteBtn").click(function() {
		alert("삭제클릭.")
			/* 입력된 데이터 가져오기 */
			//  몇개를 선택할지 모르니 배열에 집어넣는다
			let selected = [];
		  
			 // 체크된 체크박스들을 반복하여 선택된 상품 코드를 배열에 추가
      	  $("input[name='selectProduct']:checked").each(function() {
       	     selected.push($(this).val());
       		 });
       	     alert("삭제할 물건품목" +selected)
			 // 만약 선택된 상품이 없다면 알림을 표시하고 함수 종료
       	 if (selected.length === 0) {
            alert("삭제할 상품을 선택하세요.");
            return;
        }
			/* AJAX 요청 */
			$.ajax({
				type: "POST",
				//삭제 기능이 있는 서블렛으로 보낸다
				url: "uCartDeleteServlet",
				data: {
					//카트 코드만 보내면 전부 사라진다
					code : selected
				},			
				traditional: true,
				success: function(response) {
					/* 서버에서 받은 응답 처리 */
					$.ajax({
						type: "POST",
						//다시 테이블 조회
						url: "uCartListServlet",
						data: { cust_id: "sumin123" },
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							createTable(response)//jason
						}
					})
					alert("삭제 되었습니다")
					
				},
				error : function(xhr, ststus,error){
					alert("삭제 시 문제가 발생되었습니다."+ error)
				}
			})
		})
		

		
})






