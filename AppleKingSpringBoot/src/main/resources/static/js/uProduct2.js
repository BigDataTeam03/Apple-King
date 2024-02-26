/* ----------------------------------------------------------------------------------
 * Description: user product list 조회 ver2.0 완전히 새로 다시씀!!!
 * Author : DK, PDG
 * Date : 2024.02.14
 * Detail :
 *  -  window.onload
 *  - (func) loadPage(pageNumber) 
 *  - (func) displayProducts(data, pageNumber)
 *  - (func) displayPagination(totalItems)
 *  - (func) changePage(pageNumber)
 *  - (JQuery) $("#searchButton").click(function()
 *  - (func) saveProductInfo(productCode, productName, price, origin, size, weight) 
 * 			 => call : saveProductInfoServlet(session 저장용)
 * 	- jsp 에서 들고오는  pagesize select option  -> id : itemsPerPageSelect
 * 
 * Update --------------------------------------------------------------------------
 * <<2024.02.14 by pdg>>
 * 	1.기존의 version1  의 js 를 놔두고 version2 js 로 처음부터 다시 시작함. 
 * <<2024.02.15 by pdg>>
 * 	1. 메인 페이지 들어갈때 로그인 페이지에서 받은 세션값을 이용하여 환영합니다 alert 띄움
 * 
 * <<2024.02.23 by pdg>>
 * 	1.  firstChk 를 이용하여 처음 들어왔을때만 환영합니다 메세지 띄우는 기능 실현
 * 
 *----------------------------------------------------------------------------------*/

// 환영 메세지 
window.onload = function () {
	let name = $("#userName").val()
	let firstChk = $("#firstChk")
	if (!firstChk){
		alert(name+"님 환영합니다. ")
	}
}// window onload end

// 천단위 콤마 추가 Function
function numberWithCommas(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function saveProductInfo(productCode, productName, price, origin, size, weight, product_qty) {
	
	//alert(" saveProductInfo 실행 productname  :"+productName)
    $.ajax({
        type: "POST",
        url: "saveProductInfoServlet",
        data: {  productCode : productCode,
        		 productName : productName,
        		 price       : price,
        		 origin      : origin,
        		 size        : size,
        		 weight      : weight,
        		 product_qty : product_qty
        		  },
        success: function(response) {
            // 세션에 저장이 완료되면 상세 페이지로 이동합니다.
            //alert("상품정보를 세션에 저장합니다")
            window.location.href = "productDetail.do";
        },
        error: function(xhr, status, error) {
            console.error("AJAX 오류: " + status, error);
            alert("상품 정보를 저장하는 중 오류가 발생했습니다.");
        }
    });
}
// 검색 기능 
//---------------------------------------------------
//검색 기능과 정렬 기능을 동시에 사용하는 function
$(document).ready(function() {
	
	  // 검색 버튼에 대한 click 이벤트 핸들러 추가
    $("#searchButton").click(function() {
		//alert(" 검색벅튼 누름. ")
	    // 검색어 가져오기
        let searchContent  = $("#searchContent").val()
        let classifyOption = $("#classifyOption").val()
	    window.location.href = "cGoHome.do?pageNum=1&searchContent=" + searchContent+"&classifyOption="+classifyOption;
	    
        let currentPage = $("#currentPage").val()
        let startRow = $("#startRow").val();
		let pageSize = $("#pageSize").val();
		
		//alert("currentPage: "+ currentPage)
        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "uProductSearchServlet",
            data: { searchContent: searchContent,
			 		classifyOption: classifyOption,
			 		startRow: startRow,
			 	    pageSize:pageSize 
			 		
			 		},
            dataType: "json", // 서버에서 반환되는 데이터 유형을 JSON으로 기대합니다.
            success: function(response) {
                // 성공적으로 응답을 받으면 카드를 생성합니다.
                
                alert("uProductSearchServlet 쿼리 성공 "+response[1].product_name)
                JsonToDOM(response);
                
                //displayProducts(response, currentPage);
            },
            error: function(xhr, status, error) {
                // 에러 발생 시 메시지를 출력합니다.
                console.error("AJAX 오류: " + status, error);
                alert("검색 중 오류가 발생했습니다.");
            }// Error end 
        });// Ajax end 
    });// Search btn clik end	
	// 정렬 값 변경 이벤트 처리
	$("#classifyOption").change(function() {
		let classifyOption = $("#classifyOption").val();
		let searchContent = $("#searchContent").val();
		let startRow = $("#startRow").val();
		let pageSize = $("#pageSize").val();
        let currentPage = $("#currentPage").val()
		// AJAX 요청
		$.ajax({
			type: "POST",
			url: "uProductSearchServlet",
			data: { 
				searchContent: searchContent,
			 	classifyOption: classifyOption,
			 	startRow: startRow,
			 	pageSize:pageSize 
			 	
			 	}, // 검색어와 정렬 옵션 함께 전송
			dataType: "json",
			success: function(response) {
				alert("uProductSearchServlet 쿼리 성공 "+response)

				
				
				
				// 서버로부터 받은 데이터를 사용하여 화면을 갱신하거나 다른 동작을 수행
			} // success function end
		}); // ajax end
	}); // classifyOption change event end
	
}); // document ready function end


function JsonToDOM(searchedData){
	alert("JsonToDom 실행 ")
	// 서블릿을 불러서 받은 json 데이터를 세션에 저장하는 함수. 
	$.ajax({
		type : "post",
		url : "dtoSavedIntoSessionServlet",
		data : {
			searchedData : searchedData},
		dataType: "simson",
	    success: function(response) {
			alert(" session 저장 성공 ")
			
			
		}
			
		
		
	})
	

}













    