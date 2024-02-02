

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
	alert("첫 실행입니다!!!")
	$.ajax({
		type: "POST",
		url: "aProductListServlet",
		data: { name: "" },
		dataType :"json",
		success: function(response) {
			alert("json 성공함");
			createTable(response);
		},
		error: function(xhr, status, error) {
			console.error("AJAX 요청 실패:", error);
			alert("요청이 실패했습니다: " + error); // 에러 메시지도 함께 출력
			console.log("에러입니다.!")
			console.log(error)
			//createTable(response);
		},
		beforeSend: function(xhr, settings) {
			alert("AJAX 요청 URL: " + settings.url); // 요청 전에 URL을 확인
		}
	});

};
 
function createTable(data) {
	alert("creat Table 실행함. ")
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
		"<td id='" + data[i].product_name + "'>"+
		// 클릭 이벤트를 추가한다. 
		// <a> 는 anchor 라고 함. #은 하이퍼링크가 현재페이지에 머물도록 한다. 
		// onclick 은 하이퍼링크를 클릭하면 함수가 실행이 되게 한다. 
		"</td>" +
		"<td>" + data[i].product_code + "</td>" +
		"<td>" + data[i].product_name + 
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
    $("#Print_code").html("안녕하세요");
	
}

