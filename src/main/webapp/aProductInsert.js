
/* Description: DB 에 사진 을 포함하여 정보를 추가함. 
 * Author : PDG, KBS
 * Date : 2024.02.07
 * Warning :
 * Update --------------------------------------------------

 *----------------------------------------------------------
 */

// 페이지 실행후 바로 상품 전체 조회
window.onload = function() {
	alert("insert js 실행합니다. ")
	$.ajax({
		
		// post method server request
		type: "POST",
		
		// target server page(Servlet) url
		url: "aProductListServlet",
		
		// request data (JSON)
		data: { name: "" },
		
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
	let table 	=
	"<table border='1'>"
	
	// table 변수에 헤더 추가
	table 		+=
		"<tr>"+
		"<th>상품코드			</th>" + // col1
		"<th>상품명 			</th>" + // col2
		"<th>수량				</th>" + // col3
		"<th>원산지			</th>" + // col4
	    "<th>생산일자			</th>" + // col5
	    "<th>무게				</th>" + // col6
	    "<th>사이즈			</th>" + // col7
	    "<th>상세 이미지		</th>" + // col8
	    "<th>조회수			</th>" + // col9
	    "<th>상품 등록일		</th>" + // col10
	    "<th>품종				</th>" + // col11
	    "<th>상품 섬네일 이미지	</th>" + // col12
	    "<th>상품 가격	</th>" + // col12
	    "</tr>"
	   
	// insert data rows
	for(let i=0; i<data.length; i++)  {
		table += "<tr>" +
		"<td id='" + data[i].product_name + "'>"+data[i].product_code +"</td>" + 						// col1
		"<td>" +"<a href='#' onclick='handleClick("+i+")'>" + data[i].product_name + "</a>"+ "</td>" +	// col2
		"<td>" + data[i].product_qty  			+ "</td>" + // col3
		"<td>" + data[i].origin 				+ "</td>" +	// col4
		"<td>" + data[i].manufacture_date  		+ "</td>" + // col5
		"<td>" + data[i].weight  				+ "</td>" + // col6
		"<td>" + data[i].size  					+ "</td>" + // col7
		"<td>" + data[i].detail_image_name  	+ "</td>" + // col8
		"<td>" + data[i].view_count  			+ "</td>" + // col9
		"<td>" + data[i].product_reg_date 		+ "</td>" + // col10
		"<td>" + data[i].kind  					+ "</td>" + // col11
		"<td>" + data[i].product_image_names  	+ "</td>" + // col12
		"<td>" + data[i].price  	            + "</td>" + // col13
		"</tr>"
	}
	
	// table end
	table += "</table>"
	
	// html result <- table
	$("#result").html(table);
	
	// html prouct count 컨텐츠에 총 상품 수량을 넣어줌. 
	$("product_code").html(data.length +1)
	
	document.querySelector('#product_code').value = data.length +1
	
	$("#productCount").html(data.length +1)
	// 데이터를 화면에 출력
    let dataOutput = "<ul>";
    for (let i = 0; i < data.length; i++) {
        dataOutput += "<li>" + data[i].product_code +
        ", " + data[i].product_name + ", " + data[i].product_qty +
        ", " + data[i].origin + ", " + data[i].manufacture_date +
        ", " + data[i].weight +", " + data[i].size +
        ", " + data[i].detail_image_name +", " + data[i].view_count +
        ", " + data[i].product_reg_date +", " + data[i].kind +
        ", " + data[i].product_image_names +
        ", " + data[i].price +"</li>";
    }
    dataOutput += "</ul>";
    $("#Print_code").html("");
	
//수정을 위해 상품 이름을 클릭하면 수정텍스트 창에 선택한 정보가 보여지게 하기위한 문	
}
// 테이블에서 클릭된 상품 정보를 가져오는 함수
function handleClick(index){ //index : table cell number
	
	// adminMain.jsp 안의 테이블에서 마우스로 클릭된 상품의 코드에 대한 table cell id 로 값을 불러옴.
	let product_code 			= document.getElementById("product_code")
	let product_name 			= document.getElementById("product_name")
	let product_qty 			= document.getElementById("product_qty")
	let origin 					= document.getElementById("origin")
	let manufacture_date 		= document.getElementById("manufacture_date")
	let weight 					= document.getElementById("weight")
	let size 					= document.getElementById("size")
	let detail_image_name 		= document.getElementById("detail_image_name")
	let view_count 				= document.getElementById("view_count")
	let product_reg_date 		= document.getElementById("product_reg_date")
	let kind 					= document.getElementById("kind")
	let product_image_names 	= document.getElementById("product_image_names")
	let price				 	= document.getElementById("price")
	
	//연결된 값을 데이터값에 집어넣기
	product_code.value 			= dataReal[index].product_code
	product_name.value 			= dataReal[index].product_name
	product_qty.value 			= dataReal[index].product_qty
	origin.value 				= dataReal[index].origin
	manufacture_date.value 		= dataReal[index].manufacture_date
	weight.value 				= dataReal[index].weight
	size.value 					= dataReal[index].size
	detail_image_name.value 	= dataReal[index].detail_image_name
	view_count.value 			= dataReal[index].view_count
	product_reg_date.value 		= dataReal[index].product_reg_date
	kind.value 					= dataReal[index].kind
	product_image_names.value 	= dataReal[index].product_image_names
	price.value 	= dataReal[index].price
	/*
		이해가 안갈것 같아서 설명합니다.
		data real 은 위에 함수에서 server 에서 받은 json 파일을 array 로 변환한 형태이다
		그러므로 dataReal은
		[
			{product_code : 1,
			 product_name : "가사과",
			 ~
			 product_image_names : -
			},
			{product_code : 2,
			 product_name : "나사과",
			 ~
			 product_image_names : -
			},
			....
		]
		의 형태일것이다. 그러므로 dataReal[0] 의 값은 가사과에 대한 정보를 가져오게된다.
		이때 product_name.value = dataReal.product_name  의 의미는
		product_name 은 현재 adminMain.jsp 에서id 가prodcut_name 인 input tag 를 가리킨다
		그 인풋 태그의 값을 dataReal 의 첫번째 원소에 위치한 jason 에서 Key 가 prodcut_name 인 것의 value 를 가져와서
		넣는 다는 뜻이다.
		
	.*/
	
}

// 등록 버튼 클릭
$(document).ready(function() {
	
		// 
		$("#queryButton").click(function() {
			
			// 입력된 데이터 가져오기
			let name = $("#name").val()
			
			/* AJAX 요청 */
			$.ajax({
				//post 방식으로 보낸다
				type: "POST",
				//기능을 실행하는 Servlet 으로 보낸다
				url: "aProductListServlet",
				//name 값을 받아 name 으로 보낸다
				data: {name : name},
				//연결 성공시 테이블 목록도 바로 변경사항을 적용해야 하기위해 테이블을 다시 받아온다
				success: function(response) {
					/* 서버에서 받은 응답 처리 */
					createTable(response)
				}
			})
		})		
	})

//상품 테이블 입력을 요청하는 메소드
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#insertBtn").click(function() {
			alert("입력버튼이 눌렸습니다.")
			/* 입력된 데이터 가져오기 */
			let form = document.insertForm
			let code = $("#product_code").val()
			let name = $("#product_name").val()
			let qty = $("#product_qty").val()
			let origin = $("#origin").val()
			let manufacture = $("#manufacture_date").val()
			let weight = $("#weight").val()
			let size = $("#size").val()
			let detailImage = $("#detail_image_name").val()
			let view = $("#view_count").val()
			let regDate = $("#product_reg_date").val()
			let kind = $("#kind").val()
			let productImage = $("#product_image_names").val()
			let price = $("#price").val()
		
			
			/* AJAX 요청 */
			$.ajax({
				type: "POST",
				url: "aProductInsertServlet",
				data: {
					code: code,
					name: name,
					qty: qty,
					origin: origin,
					manufacture: manufacture,
					weight : weight,
					size : size,
					detailImage : detailImage,
					view : view,
					regDate : regDate,
					kind : kind,
					productImage : productImage,
					price : price
										
				},
				success: function(response) {
					alert("aProductInsert servlet 으로 넘어갔습니다. ")
					/* 서버에서 받은 응답 처리 */
					$.ajax({
						type: "POST",
						url: "aProductListServlet",
						data: { name:"" },
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							
							//입력완료 후 입력란을 비우기
							$("#product_code,#product_name,#product_qty,#origin, #manufacture_date,#weight,#size,#detail_image_name,#view_count, #product_reg_date,#kind,#product_image_names,#price").val("")
						}
					})
					alert("입력 되었습니다")
					form.submit();
					
				},
				error : function(xhr, ststus,error){
					alert("입력 시 문제가 발생되었습니다."+ error)
				}
			})
		})
		
})
