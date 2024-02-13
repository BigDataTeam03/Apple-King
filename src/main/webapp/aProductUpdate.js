
/* Description: DB 에서 불러온 상품들을 조회하고 검색함.
 * Author : PDG, KBS
 * Date : 2024.02.02
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.02 by PDG, KBS>>
 *	1. 주석 달음.
 	2. 상품 조회 및 수정이 가능하게 함.
 * <<2024.02.03 by PDG
 * 	1. 주석을 친절하게 바꿈.
 *----------------------------------------------------------
 <<2024.02.12 by KBS
 * 	1. 정렬과 검색 연동 완료.
 */
// 페이지 실행후 바로 상품 전체 조회
window.onload = function() {

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
			let price = $("#price").val()
		
			
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
					productImage : productImage,
					price : price
										
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

//상품 테이블 삭제를 요청하는 메소드
$(document).ready(function() {
		/* 버튼 클릭시 AJAX 요청 */
		$("#deleteBtn").click(function() {
		alert("삭제되었습니다.")
			/* 입력된 데이터 가져오기 */
			let code = $("#product_code").val()
			//let name = $("#product_name").val()
			alert("code : "+ code )
			/* AJAX 요청 */
			$.ajax({
				type: "POST",
				url: "aProductDeleteServlet",
				data: {
					//name : name,
					code : code
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
      // 전체 검색 버튼 클릭 시 실행되는 함수
    $("#allBtn").click(function() {
        // AJAX 요청을 통해 검색어를 서버에 전달하여 데이터 조회
        $.ajax({
            type: "POST",
            url: "aProductListServlet",
            data: { name: "" }, // 검색어 전달
            success: function(response) {
                // 서버에서 받은 응답 처리
                createTable(response); // 검색 결과로 테이블 생성
            }
        });
    });

    // 검색 버튼 클릭 시 실행되는 함수
    $("#queryButton").click(function() {
        let name = $("#name").val(); // 검색어 가져오기


		//라디오버튼 밸류값 가져오기
        let origin = $("input[name='origin']:checked").val(); // 원산지 선택 값 가져오기
        let size = $("input[name='size']:checked").val(); // 사이즈 선택 값 가져오기
        let kind = $("input[name='kind']:checked").val(); // 품종 선택 값 가져오기
    
      
        // 정령 방식 밸류값 가져오기 
        let selectedSorting = $("#Sorting").val(); // 선택된 정렬 방식
        // AJAX 요청을 통해 검색어를 서버에 전달하여 데이터 조회
        $.ajax({
            type: "POST",
            url: "aProductListServlet",
            data: { name: name,
            		origin : origin,
            		size : size,
            		kind : kind,
            		sorting : selectedSorting }, // 검색어 전달
            success: function(response) {
                // 서버에서 받은 응답 처리
                createTable(response); // 검색 결과로 테이블 생성
            }
        });
    });

    // 상세 검색 버튼 클릭 시 실행되는 함수
    $("#confirmBtn").click(function() {	
		//라디오버튼 밸류값 가져오기
        let origin = $("input[name='origin']:checked").val(); // 원산지 선택 값 가져오기
        let size = $("input[name='size']:checked").val(); // 사이즈 선택 값 가져오기
        let kind = $("input[name='kind']:checked").val(); // 품종 선택 값 가져오기
        // 이름 검색 밸류값 가져오기
        let name = $("#name").val(); // 검색어 가져오기
        // 정령 방식 밸류값 가져오기 
        let selectedSorting = $("#Sorting").val(); // 선택된 정렬 방식 가져오기
         

        // AJAX 요청을 통해 상세 검색 조건을 서버에 전달하여 데이터 조회
        $.ajax({
            type: "POST",
            url: "aProductListServlet",
            data: { origin: origin,
           			  size: size, 
          		      kind: kind,
          		      name : name,
          		      sorting : selectedSorting
          		    }, // 상세 검색 조건 전달
            success: function(response) {
                // 서버에서 받은 응답 처리
                // 받아온 값이 없을 때 (0) 라디오 버튼 초기화
                if (response.length === 0) {
                    alert("조건에 해당하는 상품이 없습니다.");
                $("input[name='origin']").prop("checked", false);
                $("input[name='size']").prop("checked", false);
                $("input[name='kind']").prop("checked", false);
                } else {
                    createTable(response); // 상세 검색 결과로 테이블 생성           
                }
            
            },
            error: function(xhr, status, error) {
                console.error("상품 검색 중 오류가 발생했습니다: " + error);
            }
        });
    });

    // 라디오버튼 초기화 버튼을 눌렀을 시 실행되는 함수
    $("#clearRadioBtn").click(function() {
     			//라디오 버튼 초기화
                $("input[name='origin']").prop("checked", false);
                $("input[name='size']").prop("checked", false);
                $("input[name='kind']").prop("checked", false);
   
    });
           
            
          
         
        
   

    // 정렬 기능 변경 시 실행되는 함수
    $("#Sorting").change(function() {
        let selectedSorting = $("#Sorting").val(); // 선택된 정렬 방식 가져오기
        // 라디오 버튼으로 가져온 뱔류값
        let origin = $("input[name='origin']:checked").val(); // 원산지 선택 값 가져오기
        let size = $("input[name='size']:checked").val(); // 사이즈 선택 값 가져오기
        let kind = $("input[name='kind']:checked").val(); // 품종 선택 값 가져오기
        // 이름 검색 밸류값 가져오기
        let name = $("#name").val(); // 검색어 가져오기


        // AJAX 요청을 통해 선택된 정렬 방식을 서버에 전달하여 데이터 조회
        $.ajax({
            type: "POST",
            url: "aProductListServlet",
            data: {  sorting : selectedSorting,
             		 origin : origin,
             		 size : size,
             		 kind : kind,
             		 name : name
           		            		 
           		  }, // 검색어와 정렬 방식 전달
            success: function(response) {
                // 서버에서 받은 응답 처리
                createTable(response); // 정렬된 데이터로 테이블 생성
            },
            error: function(xhr, status, error) {
                console.error("정렬 요청 중 오류가 발생했습니다: " + error);
            }
        });
    });
