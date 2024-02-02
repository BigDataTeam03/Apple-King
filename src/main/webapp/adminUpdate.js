

/*--------------------------------------
 * Description: DB 에서 불러온 상품들을 조회하고 검색함.
 * Author : PDG, KBS
 * Date : 2024.02.02
 * Warning : 
 * Update :
 * 		Update 2024.02.02 by PDG, KBS
 * 			1. 주석 달음. 
 * 			2.
 *-------------------------------------- 
 */

// 페이지 실행후 바로 상품 전체 조회
window.onload = function() {
	$.ajax({
		//post 방식으로 보내서 정보를 숨긴다
		type: "POST",
		//기능을 실행하는 Servlet 보낸다
		url: "aProductListServlet",
		//name 값을 받는다
		data: { name: "" },
		//json 타입으로 받는다
		dataType :"json",
		//연결 성공시 createTable 메소드로 보낸다
		success: function(response) {
			createTable(response);
		},	
	});

};
 
function createTable(data) {
	//검색해온 데이터를 배열로 변환
	dataReal 	= Array.from(data) 
	let table 	= "<table border='1'>"
	// 테이블 헤더 추가
	table 		+= "<tr><th>상품코드</th><th>상품명</th><th>수량</th><th>원산지</th> "                +
				   "<th>생산일자</th><th>무게</th><th>사이즈</th><th>상세 이미지</th><th>조회수</th>"    +
				   "<th>상품 등록일</th><th>품종</th><th>상품 섬네일 이미지</th></tr>"
	/* 데이터 행 추가 */
	for(let i=0; i<data.length; i++)  {
		table += "<tr>" +
		"<td id='" + data[i].product_name + "'>"+data[i].product_code +
		// 클릭 이벤트를 추가한다. 
		// <a> 는 anchor 라고 함. #은 하이퍼링크가 현재페이지에 머물도록 한다. 
		// onclick 은 하이퍼링크를 클릭하면 함수가 실행이 되게 한다. 
		"</td>" +"<td>" +
		"<a href='#' onclick='handleClick("+i+")'>" + data[i].product_name + "</a>"+ "</td>" +
		"<td>" + data[i].product_qty + "</td>" +
		"<td>" + data[i].origin + "</td>" +
		"<td>" + data[i].manufacture_date + "</td>" +
		"<td>" + data[i].weigtht + "</td>" +
		"<td>" + data[i].size + "</td>" +
		"<td>" + data[i].detail_image_name + "</td>" +
		"<td>" + data[i].view_count + "</td>" +
		"<td>" + data[i].product_reg_dat + "</td>" +
		"<td>" + data[i].kind + "</td>" +
		"<td>" + data[i].product_image_names + "</td>" +
		"</tr>"
	}
	
	table += "</table>"
	/* html에 있는 result에 table을 넣는다 */
	$("#result").html(table);
	
	   // 데이터를 화면에 출력
    let dataOutput = "<ul>";
    for (let i = 0; i < data.length; i++) {
        dataOutput += "<li>" + data[i].product_code + 
        ", " + data[i].product_name + ", " + data[i].product_qty + 
        ", " + data[i].origin + ", " + data[i].manufacture_date + 
        ", " + data[i].weigtht +", " + data[i].size +
        ", " + data[i].detail_image_name +", " + data[i].view_count +
        ", " + data[i].product_reg_dat +", " + data[i].kind +
        ", " + data[i].product_image_names +"</li>";
    }
    dataOutput += "</ul>";
    $("#Print_code").html("");
	
//수정을 위해 상품 이름을 클릭하면 수정텍스트 창에 선택한 정보가 보여지게 하기위한 문	
}	   
function handleClick(index){
	
	//jsp 에서 받은 값을 연결
	let product_code 	= document.getElementById("product_code")
	let product_name 	= document.getElementById("product_name")
	let product_qty 	= document.getElementById("product_qty")
	let origin = document.getElementById("origin")
	let manufacture_date = document.getElementById("manufacture_date")
	let weight = document.getElementById("weight")
	let size = document.getElementById("size")
	let detail_image_name = document.getElementById("detail_image_name")
	let view_count = document.getElementById("view_count")
	let product_reg_date = document.getElementById("product_reg_date")
	let kind = document.getElementById("kind")
	let product_image_names = document.getElementById("product_image_names")
	
	//연결된 값을 데이터값에 집어넣기
	product_code.value = dataReal[index].product_code
	product_name.value = dataReal[index].product_name
	product_qty.value = dataReal[index].product_qty
	origin.value = dataReal[index].origin
	manufacture_date.value = dataReal[index].manufacture_date
	weight.value = dataReal[index].weight
	size.value = dataReal[index].size
	detail_image_name.value = dataReal[index].detail_image_name
	view_count.value = dataReal[index].view_count
	product_reg_date.value = dataReal[index].product_reg_date
	kind.value = dataReal[index].kind
	product_image_names.value = dataReal[index].product_image_names
	
	
}
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#queryButton").click(function() {
			/* 입력된 데이터 가져오기 */
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
	
//상품 테이블 수정을 요청하는 메소드
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#updateBtn").click(function() {
			/* 입력된 데이터 가져오기 */
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
		
			
			/* AJAX 요청 */
			$.ajax({
				type: "POST",
				url: "aProductUpdateServlet",
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
					productImage : productImage
										
				},
				success: function(response) {
					/* 서버에서 받은 응답 처리 */
					$.ajax({
						type: "POST",
						url: "aProductListServlet",
						data: { name: "" },
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							createTable(response)//jason
							//수정완료 후 입력란을 비우기
							$("#product_code,#product_name,#product_qty,#origin, #manufacture_date,#weight,#size,#detail_image_name,#view_count, #product_reg_date,#kind,#product_image_names").val("")
							
						}
					})
					alert("수정 되었습니다")
					
				},
				error : function(xhr, ststus,error){
					alert("수정 시 문제가 발생되었습니다."+ error)
				}
			})
		})

})

//상품 테이블 입력을 요청하는 메소드
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#insertBtn").click(function() {
			/* 입력된 데이터 가져오기 */
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
					productImage : productImage
										
				},
				success: function(response) {
					/* 서버에서 받은 응답 처리 */
					$.ajax({
						type: "POST",
						url: "aProductListServlet",
						data: { name: "" },
						success: function(response) {
							/* 서버에서 받은 응답 처리 */
							createTable(response)//jason
							//입력완료 후 입력란을 비우기
							$("#product_code,#product_name,#product_qty,#origin, #manufacture_date,#weight,#size,#detail_image_name,#view_count, #product_reg_date,#kind,#product_image_names").val("")
							
						}
					})
					alert("수정 되었습니다")
					
				},
				error : function(xhr, ststus,error){
					alert("수정 시 문제가 발생되었습니다."+ error)
				}
			})
		})

})

