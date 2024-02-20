/* ----------------------------------------------------------------------------------
 * Description: user product list 조회  
 * Author : DK, PDG
 * Date : 2024.02.08
 * Detail :
 *  -  window.onload
 *  - (func) loadPage(pageNumber) 
 *  - (func) displayProducts(data, pageNumber)
 *  - (func) displayPagination(totalItems)
 *  - (func) changePage(pageNumber)
 *  - (JQuery) $("#searchButton").click(function()
 *  - (func) saveProductInfo(productCode, productName, price, origin, size, weight) 
 * 			 => call : saveProductInfoServlet(session 저장용)
 * 			
 * 	- jsp 에서 들고오는  pagesize select option  -> id : itemsPerPageSelect
 * 
 * 
 * 
 * Update --------------------------------------------------------------------------
 * <<2024.02.08 by DK>>
 *	1. 상품 목록 출력
 *	2. 상품 상세 페이지로 택한 상품의 정보를 보냄
 * << 2024.02.019 by pdg
 * 	1. 각 함수와 J 쿼리에대한 메타 주석을 달았음. 
 * <<2024.02.11 by DK>>
 *  1. 가격별 정렬 기능 추가. 
 *  2. (CSS)가격'원',천단위 콤마, 볼드, 빨간색으로 표시. 
 * 	3. 100g 당 몇원인지 추가. 
 * 	4. 몇개씩 보기 기능 추가. 
 * <<2024.02.13 by pdg , dk>>
 *  1. 검색후 정렬 버튼 누르면 검색결과가 사라지는문제 해결함.JS 하나에서 끝
 *  2. Paging 을 위해서 전면적으로 수정, 
 * 
 *----------------------------------------------------------------------------------*/

let currentPage = 1;
let pageSize = 10//$("#itemsPerPageSelect").val();

// 페이지 로드 시 처음 페이지를 로드한다
window.onload = function () {
	
	//alert(itemsPerPage)
    //loadPage(currentPage);
    startIndex = (currentPage-1)*pageSize  // startIndex = sql offset
    alert("startIndex : "+startIndex)
    $.ajax({
        type: "POST",
        url: "uProductSearchServlet",
        data: { 
				startIndex : startIndex,
        		pageSize   : pageSize
			},
        dataType: "json",
        success: function (response) {
			
			alert("uProductSearchServlet 에서 뿌려준 결과물의 개수 :"+ response.length)
            
            //displayPagination(response.length);
            // 11개의 데이터를 displayPagination 에 넣는게 아니고 개수를 넣네?
            
            // 전체 페이지를 구하자. 
            // 전체 상품 항목 / pageSize 을 올림 예) 105/10 =10.5 -> 11
            let totalProductNum = response.length
            
            let totalPage = totalProductNum/pageSize
           
            
            // 상품을 검색하거나 상품 개수가 변할때 다시 Initialize 
            $("#pagination").empty();
           	paginationSpace = $("#pagination")
            for(let p = 0 ; p<totalPage; p++){
				let pButton= `<button onclick="changePage(${p})"> ${p} </button>`;
				paginationSpace.append(pButton);
			}
           
            let resultSpace = $("#result");
            $("#result").empty(); //상품 목록 초기화 
            for( let i =startIndex; i< parseInt(startIndex)+parseInt(pageSize)-1; i++){
				//$("#result").value(i)
				//alert("startIndex: "+startIndex +" PageSize: "+pageSize+" currentPage :"+i)
				
				let priceWithCommas = numberWithCommas(response[i].price); // 가격에 콤마추가하기 

		        // 100g 당 몇원? 
		        let pricePer100g = (response[i].price / (response[i].weight * 100));
		
		        let cardHtml = `
		           <div class="card">            
		               <img src="image/${response[i].product_image_names}" > 
		                <div class="card-body">
		                    <h5 class="card-title">
		                       <a href="javascript:void(0);" onclick="saveProductInfo(${response[i].product_code},'${response[i].product_name}', ${response[i].price}, '${response[i].origin}', '${response[i].size}', ${response[i].weight})" class="bold">${response[i].product_name}</a>
		                    </h5>
		                    <p class="card-text">
		                        <span class="red-price bold">${priceWithCommas}원</span>
		                        <br>
		                        <span class="red-price">(100g당${pricePer100g.toFixed(0)}원)</span>
		                    </p>
		                </div>
		            </div>
		             `;
		        resultSpace.append(cardHtml);
			}
        },
    });//Ajax end
};// Window onload end



function displayProducts(data, pageNumber) {
    let resultSpace = $("#result");
    resultSpace.empty(); // Clear previous content
    const startIndex = (pageNumber - 1) * pageSize;
    const endIndex = pageNumber * pageSize;
    const pageData = data.slice(startIndex, endIndex);
    
    for (let i = 0; i < pageData.length; i++) {
        let priceWithCommas = numberWithCommas(pageData[i].price); // 가격에 콤마추가하기 

        // 100g 당 몇원? 
        let pricePer100g = (pageData[i].price / (pageData[i].weight * 100));

        let cardHtml = `
           <div class="card">            
               <img src="image/${pageData[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                       <a href="javascript:void(0);" onclick="saveProductInfo(${data[i].product_code},'${data[i].product_name}', ${data[i].price}, '${data[i].origin}', '${data[i].size}', ${data[i].weight})" class="bold">${data[i].product_name}</a>
                    </h5>
                    <p class="card-text">
                        <span class="red-price bold">${priceWithCommas}원</span>
                        <br>
                        <span class="red-price">(100g당${pricePer100g.toFixed(0)}원)</span>
                    </p>
                </div>
            </div>
             `;
        resultSpace.append(cardHtml);
    }
}






/*
// 페이지 변경 함수
function changePage(pageNumber) {
	alert("page "+pageNumber+"바꿈")
    currentPage = pageNumber;
    startIndex = (currentPage-1)*pageSize +1
    endIndex =   currentPage *pageSize
   // current page 의 start  와 end 가 servlet 에 들어가서 처리하게 하자. 
    
    $.ajax({
        type: "POST",
        url: "uProductSearchServlet",
        data: { startIndex : startIndex,
        		endIndex   : endIndex,
        		pageSize   : pageSize
			   },
        dataType: "json",
        success: function (response) {

        },
    });//Ajax end
    loadPage(currentPage);
}


*/





/*
// 페이지 로드 함수
function loadPage(pageNumber) {
    $.ajax({
        type: "POST",
        url: "uProductListServlet",
        data: { name: "" },
        dataType: "json",
        success: function (response) {
            displayPagination(response.length);
            displayProducts(response, pageNumber);
        },
    });//Ajax end
}// LoadPage end
*/



// 천단위 콤마 추가 Function
function numberWithCommas(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



// 한 페이지 당 항목 수를 설정하는 함수
function setItemsPerPage(value) {
    itemsPerPage = parseInt(value); // 값(value)을 정수형으로 변환한다.
    loadPage(currentPage); // 새로운 항목 수로 첫 번째 페이지를 다시 불러온다.
}

//-------------------------------------------------------------------


function saveProductInfo(productCode, productName, price, origin, size, weight) {
	 //alert(" saveProductInfo 실행 productname  :"+productName)
    $.ajax({
        type: "POST",
        url: "saveProductInfoServlet",
        data: {  productCode : productCode,
        		 productName : productName,
        		 price       : price,
        		 origin      : origin,
        		 size        : size,
        		 weight      : weight },
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
//---------------------------------------------------
//검색 기능과 정렬 기능을 동시에 사용하는 function
$(document).ready(function() {
	  // 검색 버튼에 대한 click 이벤트 핸들러 추가
    $("#searchButton").click(function() {
	 // 검색어 가져오기
        let searchContent  = $("#searchContent").val();
        let classifyOption = $("#classifyOption").val();
		alert("hello")
        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "uProductSearchServlet",
            data: { searchContent: searchContent,
			 		classifyOption: classifyOption},
            dataType: "json", // 서버에서 반환되는 데이터 유형을 JSON으로 기대합니다.
            success: function(response) {
                // 성공적으로 응답을 받으면 카드를 생성합니다.
                alert("hhi")
                displayProducts(response, currentPage);
               	
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
		// AJAX 요청
		$.ajax({
			type: "POST",
			url: "uProductSearchServlet",
			data: { 
				searchContent: searchContent,
			 	classifyOption: classifyOption }, // 검색어와 정렬 옵션 함께 전송
			dataType: "json",
			success: function(response) {
				displayProducts(response, currentPage);
				
				
				// 서버로부터 받은 데이터를 사용하여 화면을 갱신하거나 다른 동작을 수행
			} // success function end
		}); // ajax end
	}); // classifyOption change event end
}); // document ready function end

   
 
    