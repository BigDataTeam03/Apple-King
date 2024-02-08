/* Description: DB 에서 불러온 고객 정보들을 조회하고 검색함.
 * Author : KBS
 * Date : 2024.02.06
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.06 by KBS>>
 *	1. 고객 테이블 출력
    2. 고객 테이블 이름검색기능추가
    3. 고객 테이블 정렬기능 추가
 *----------------------------------------------------------
 */
// 페이지 실행후 바로 상품 전체 조회
window.onload = function() {
	$.ajax({
		
		// post method server request
		type: "POST",
		
		// target server page(Servlet) url
		url: "aCustomerListServlet",
		
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
		"<th>고객 아이디		</th>" + // col1
		"<th>성명 			</th>" + // col2
		"<th>전화번호			</th>" + // col3
		"<th>이메일			</th>" + // col4
	    "<th>주소				</th>" + // col5
	    "<th>가입일자			</th>" + // col6
	    "<th>등급				</th>" + // col7
	    "</th>" + "</tr>"
	    

	// insert data rows
	for(let i=0; i<data.length; i++)  {
		
		table += "<tr>" +
		"<td id='" + data[i].name + "'>"+data[i].cust_id +"</td>" + 						// col1
		"<td>" +"<a href='#' onclick='handleClick("+i+")'>" + data[i].name + "</a>"+ "</td>" +	// col2
		"<td>" + data[i].tel  		          	+ "</td>" + // col3
		"<td>" + data[i].email 				    + "</td>" +	// col4
		"<td>" + data[i].address  		        + "</td>" + // col5
		"<td>" + data[i].reg_date 				+ "</td>" + // col6
		"<td>" + data[i].cust_rank  			+ "</td>" + // col7
		"</tr>"
	}
	
	// table end
	table += "</table>"
	
	// html result <- table
	$("#custList").html(table);
	
	// html cust_id 컨텐츠에 총 고객 수를 넣어줌. 
	$("cust_id").html(data.length)
	
	document.querySelector('#cust_id').value = data.length +1
	
	// 데이터를 화면에 출력
    let dataOutput = "<ul>";
    for (let i = 0; i < data.length; i++) {
        dataOutput += "<li>" + data[i].cust_id +
        ", " + data[i].name + ", " + data[i].tel +
        ", " + data[i].email + ", " + data[i].address +
        ", " + data[i].reg_date +", " + data[i].rank 
        + "</li>"
    }
    dataOutput += "</ul>";
    $("#Print_code").html("");
	
//수정을 위해 상품 이름을 클릭하면 수정텍스트 창에 선택한 정보가 보여지게 하기위한 문	
}
// 테이블에서 클릭된 상품 정보를 가져오는 함수
function handleClick(index){ //index : table cell number
	
	// adminMain.jsp 안의 테이블에서 마우스로 클릭된 상품의 코드에 대한 table cell id 로 값을 불러옴.
	let cust_id 		= document.getElementById("cust_id")
	let name 			= document.getElementById("name")
	let tel 			= document.getElementById("tel")
	let email 			= document.getElementById("email")
	let address 		= document.getElementById("address")
	let reg_date 		= document.getElementById("reg_date")
	let cust_rank 			= document.getElementById("cust_rank")
	
	
	//연결된 값을 데이터값에 집어넣기
	cust_id.value 	    = dataReal[index].cust_id
	name.value 			= dataReal[index].name
	tel.value 			= dataReal[index].tel
	email.value 		= dataReal[index].email
	address.value 		= dataReal[index].address
	reg_date.value 		= dataReal[index].reg_date
	cust_rank.value 	= dataReal[index].cust_rank
	}
	
	//정렬기능과 검색기능을 같이 서버에 보내야 검색후 정렬을 시행해도 검색이 유지된다
$(document).ready(function() {
    // 정렬 콤보박스 값 변경 이벤트 처리
    $("#sortOption").change(function() {
        // 선택된 정렬 옵션을 가져옴
        let sortOption = $(this).val();
        let name = $("#name").val(); // 현재 검색어 가져오기

        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "aCustomerListServlet",
            data: { name: name, 
              sortOption: sortOption }, // 검색어와 정렬 옵션 함께 전송
            dataType: "json",
            success: function(response) {
                // 서버에서 받은 응답 처리
                createTable(response);
            }
        });
    });

    // 검색 버튼 클릭 이벤트 처리	
    $("#searchBtn").click(function() {
        // 입력된 데이터 가져오기
        let name = $("#name").val();

        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "aCustomerListServlet",
            data: { name: name },
            success: function(response) {
                // 서버에서 받은 응답 처리
                createTable(response);
            }
        });
    });
});
	
		