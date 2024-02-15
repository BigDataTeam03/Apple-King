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
 *----------------------------------------------------------------------------------*/

// 환영 메세지 
window.onload = function () {
	let name = $("#userName").val()
	//let userName =  document.getElementById("userName").value()
	//alert(name+"님 환영합니다. ")
}// window onload end

// 천단위 콤마 추가 Function
function numberWithCommas(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


function saveProductInfo(productCode, productName, price, origin, size, weight, product_qty) {
	
	alert(" saveProductInfo 실행 productname  :"+productName)
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











    