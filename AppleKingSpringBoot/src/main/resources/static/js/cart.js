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
 * 
 * <<2024.02.22 by KBS>
 * 	1. spring 으로 변환 작업중(리스트 출력 기능만 있음)
 */
// 카트 수량 변경할때 가격 같이 변경되는 함수 by pdg
$(document).ready(function() {
	//Start message
	console.log("**<< JS START cart.js >>**")
	$('input[name="cartQty"]').change(function(){
		let cart_qty =$(this).val()
		console.log("수량 :"+ cart_qty)
	})
	
});
//선택된 상품 배열  -> 구매하기 
$(document).ready(function() {

	//ust_id, name,product_code, product_name, price, payment_method, used_point, order_qty 
	
	console.log("**<< JS START cart.js >>**")
	$("#purchaseBtn").click(function() {
	
		//let selected = [];
		let selectedProducts = [];
		
		// 체크된 상품의 상품코드를 배열에 추가. 
  	  	$("input[name='selectProduct']:checked").each(function() {
   	    	//selected.push($(this).val());
   	    	let product = {
				cart_code: $(this).val(),
				cart_qty: $(this).closest('tr').find('.quantity-input').val()
			};
			selectedProducts.push(product);
			console.log(product)
   		});
		$.ajax({
			type: "POST",
			url: "purchaseCart", // where to go: CartController 
			contentType: "application/json", // 데이터 유형 설정
			data: JSON.stringify({ selectedProducts: selectedProducts }), 
			success: function(response) {
				alert("구매되었습니다")
				// 장바구니 삭제 
				window.location.href ="ProductDisplay"
			},
			error : function(error){
				alert("구매 시 문제가 발생되었습니다."+ error)
			}//ERROR END
		})//AJAX END
	})//CLICKED END
})//DOCUMENT READY END








// 페이지 실행후 바로 장바구니 전체 조회
window.onload = function() {
	$.ajax({			
		// post method server request
		type: "post",
		// target server page
		url: "/showCartList", // where to go : CartController
		// server response success  -> response(Json data)
		success: function(response) {
			createTable(response); //cart code
		},	
	});
};


// 테이블 생성하는 함수
function createTable(data) {
	let cartQtyArray = [];
	
    let table =
        "<table class='cart-table'>" +
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
          	"<td><strong>" + data[i].product_name + "</strong></td>" + // col1
            "<td>" +
            "<button  onclick='decreaseQuantity(this)'>-</button>" + // "-" 버튼
            "<input type='text' class='quantity-input' name='cartQty"+i+"' value='" + data[i].cart_qty + "' min='1' readonly>" + // 수량을 입력할 수 있는 input 태그
            "<button  onclick='increaseQuantity(this)'>+</button>" + // "+" 버튼
            "</td>" + 
            "<td>" + "<img src='resources/image/" + data[i].product_image + "'>" + "</td>" + // col3
            "<td>" + data[i].price.toLocaleString() + "</td>" + // col4
            "<td><input type='checkbox' name='selectProduct' checked='checked' value='" + data[i].cart_code + "'></td>" + // 체크박스 열
            "</tr>"
            //상품의 가격을 수량만큼 곱한다
             var productTotalPrice = data[i].price * data[i].cart_qty;
    	    // 총 가격 합산
             totalPrice += productTotalPrice;
             
            let cartQty = data[i].cart_qty;
    		cartQtyArray.push(cartQty);
             
    }
    
    // table end
    table += "</table>"
      
    // html result <- table
    $("#cartList").html(table);
      
    // html prouct count 컨텐츠에 총 상품 수량을 넣어줌. 
    document.querySelector('#cartTot').innerText = data.length;    
     // 총 가격 출력
    document.querySelector('#totalPrice').innerText = totalPrice.toLocaleString();  
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

// 수량 변경 시 컨트롤러에 전송하는 함수
function updateQuantity(input) {
    var cartCode = $(input).closest("tr").find("input[name='selectProduct']").val();
    var quantity = $(input).val();
   
    $.ajax({
        type: "post",
        url: "qtyUpdate",
        data: {
            cartCode: cartCode,
            quantity: quantity
        },
        success: function(response) {  
            if(response == 1) {
                alert("선택하신 상품의 재고가 부족합니다");
                input.value = parseInt(quantity) - 1;
            } else {		
                // 테이블 업데이트   
                $.ajax({
                    type: "POST",
                    //다시 테이블 조회
                    url: "/showCartList",
                    success: function(response) {
                        /* 서버에서 받은 응답 처리 */
                        createTable(response); // JSON
                    }
                });		
            }		
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
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
				//삭제 기능이 있는 컨트롤러의 메소드로 보낸다
				url: "/deleteCart", 
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
						url: "/showCartList",
						
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							createTable(response)//jason
						}
					})
					alert("삭제 되었습니다")
					
				},
				error : function(xhr, ststus,error){
					alert("삭제 시 문제가 발생되었습니다."+ error)
				}//ERROR END
			})//AJAX END
		})//CLICKED END
})//DOCUMENT READY END










