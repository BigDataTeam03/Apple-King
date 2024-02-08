/* Description: DB 에서 불러온 장바구니 리스트를 조회하고 수정 후 구매 페이지로 보낸다
 * Author : PDG, KBS
 * Date : 2024.02.08
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.08 by PDG, KBS>>
 *	1. 주석 달음.
 	2. 아이디에 해당하는 카트 조회 
 	3. 카트 삭제기능 추가
 	4. 수량선택 가능
 	5. 전체선택가능
 	
 	
 
 *----------------------------------------------------------
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
	dataReal 	= Array.from(data)
		
	 let table =
        "<table border='1'>" +
        "<tr>" +
        "<th>상품명</th>" +
        "<th>수량</th>" +
        "<th>이미지</th>" +
         "<th>가격</th>" +
        "</tr>";
        
	// insert data rows
	for(let i=0; i<data.length; i++)  {
		table += "<tr>" +
		//"<td><a href='uProductList?product_name=" + data[i].product_name + "'>" + data[i].product_name + "</a></td>" + // col1
		"<td>" +"<a href='backToProduct.do' onclick='handleClick("+i+")'>" + data[i].product_name + "</a>"+ "</td>" +	// col1
		 "<td><input type='number' name='cartQty' value='" + data[i].cart_qty +  "' min='1'></td>" + // col2 (수량을 입력할 수 있는 input 태그)
		"<td>" + data[i].product_image_names 				+ "</td>" +	// col3
		"<td>" + data[i].price 				+ "</td>" +	// col4
	    "<td><input type='checkbox' name='selectProduct' value='" + data[i].cart_code + "'></td>" + // 체크박스 열
	"</tr>"
	}
	
	// table end
	table += "</table>"
	
	// html result <- table
	$("#cartList").html(table);
	
	
	// html prouct count 컨텐츠에 총 상품 수량을 넣어줌. 
	document.querySelector('#cartTot').innerText = data.length;
  
  }
  
  
 	 // 상품 수량을 선택할 수 있도록 설정
    $("input[name='cartQty']").on("input", function() {
        // 입력된 값이 숫자가 아니거나 0 이하이면 1로 설정
        if (!(/^\d+$/.test($(this).val())) || parseInt($(this).val()) <= 0) {
            $(this).val(1);
            
    // 선택된 상품의 카트 코드 가져오기
    
    //	선택된 행을 가져온다					
    var cartCode = $(this).closest("tr").find("input[name='selectProduct']").val();
    // 선택된 수량 가져오기
    var quantity = $(this).val();
    	
    	
   		
            
            
        }
    });


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






