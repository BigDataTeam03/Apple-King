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
window.onload = function() {
	let name = $("#userName").val()
	//J query 객체는 불린으로만들수 없다. 따라서 스트링으로 값을 비교한다. 
	let first_check = $("#first_check").val()
	
	//alert("first_check : "+first_check)
	
	if (first_check == "1") {
		//alert(name + "님 환영합니다. ")
		//Controller 호출하여 firstChk -> false 

	}
}// window onload end

// 천단위 콤마 추가 Function
function numberWithCommas(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 상품 선택시 Session 에 선택 상품의 정보를 저장하는 코드 
function saveProductInfo(productCode, productName, price, origin, size, weight, product_qty) {

	//alert(" saveProductInfo 실행 productname  :"+productName)
	$.ajax({
		type: "POST",
		url: "saveProductInfoServlet",
		data: {
			productCode: productCode,
			productName: productName,
			price: price,
			origin: origin,
			size: size,
			weight: weight,
			product_qty: product_qty
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
	$("#searchButton").click(productSearch); 	// 검색 버튼 클릭시 상품검색 함수 실행 
	$("#sortingOption").change(productSearch)	// 정렬 option 바뀌면 상품 검색 함수 실행
	$("#searchContent").keypress(function(e) {  // 엔터키를 눌렀을때 상품 검색 함수 실행 
		if (e.keyCode === 13) { // 엔터 키 keyCode = 13.
			productSearch();
		}
	});

	// 검색함수 
	function productSearch() {

		// 검색정렬 조건
		let searchContent = $("#searchContent").val() 	// 검색 내용
		let sortingOption = $("#sortingOption").val()	// 정렬 조건

		// 페이지 조건
		let currentPage = $("#currentPage").val() 	//현재페이지
		let startProduct = $("#startProduct").val();//페이지 첫상품
		let pageSize = $("#pageSize").val(); 		//페이지당 상품 개수

		//페이지 이동 
		window.location.href =
			"ProductDisplay?" +
			"pageNum=1" +
			"&searchContent=" + searchContent +
			"&SortingOption=" + sortingOption
	}
});
