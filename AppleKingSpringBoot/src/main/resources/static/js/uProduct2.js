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
function saveProductInfo(product_code, product_name, price, origin, size, weight, product_qty) {

	//alert(" saveProductInfo 실행 productname  :"+product_name)
	$.ajax({
		type: "POST",
		url: "saveProductInfo",
		data: {
			product_code	: product_code,
			product_name	: product_name,
			price			: price,
			origin			: origin,
			size			: size,
			weight			: weight,
			product_qty		: product_qty
		},
		success: function() {
			window.location.href = "productDetail";
		},
	});
}

// 검색 기능 
//---------------------------------------------------
//검색 기능과 정렬 기능을 동시에 사용하는 function
$(document).ready(function() {
	let searchContent = $("#searchContent").val();
    // 정렬 옵션을 클릭했을 때
    $("#productRank, #highPrice, #lowPrice, #sold_qty, #product_reg_date").click(function(e) {
        e.preventDefault(); // 링크의 기본 동작 방지
        let sortingOption = $(this).attr("id"); // 클릭된 링크의 id 값을 가져옴
        //alert(sortingOption)
        productSort(sortingOption); // 정렬 옵션을 인자로 전달하여 검색 및 정렬 함수 호출
    });

    // 검색 버튼 클릭 시
    $("#searchButton").click(function(){
        let searchContent = $("#searchContent").val();
        let sortingOption = $("#sortingOption > .active").attr("value");
        productSearchSort(searchContent, sortingOption);
    }); 

    // 엔터키를 눌렀을 때
    $("#searchContent").keypress(function(e) {
        if (e.keyCode === 13) {
            let searchContent = $("#searchContent").val();
            let sortingOption = $("#sortingOption > .active").attr("value");
            productSearchSort(searchContent, sortingOption);
        }
    });

    // 검색 및 정렬 함수
    function productSearchSort(searchContent, sortingOption) {
		//alert(sortingOption)
        window.location.href =
            "ProductDisplay?" +
            "pageNum=1" +
            "&searchContent=" + searchContent +
            "&sortingOption=" + sortingOption;
    }
     function productSort(sortingOption) {
		
		let searchContent = $("#searchContent").val();
		//alert(sortingOption, searchContent)
        window.location.href =
            "ProductDisplay?" +
            "pageNum=1" +
            "&sortingOption=" +sortingOption+
            "&searchContent="+searchContent;
    }
    
    
    function setItemsPerPage (value){
		
		$.ajax({
			type: "GET",
			url:  "/updateItemsPerPage",
			data: {itemsPerPage : value},
			success: function(response){
				
			},
			error: function(xhr, status, error){
				
				
			}
		})
		
	}
    
    
    
    
});
