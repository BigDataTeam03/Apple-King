/* ----------------------------------------------------------------------------------
 *Description: user product list 조회  
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
 *----------------------------------------------------------------------------------*/
let currentPage = 1;
let itemsPerPage = 5; //'const'를 'let'으로 변경하여 재할당 가능하도록 수정

// 페이지 로드 시 처음 페이지를 로드한다
window.onload = function () {
    loadPage(currentPage);
};

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
    });
}


function displayProducts(data, pageNumber) {
    let resultContainer = $("#result");
    resultContainer.empty(); // Clear previous content
    const startIndex = (pageNumber - 1) * itemsPerPage;
    const endIndex = pageNumber * itemsPerPage;
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
        resultContainer.append(cardHtml);
    }
}



// 천단위 콤마 추가 Function
function numberWithCommas(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


// 페이지네이션 표시 함수
function displayPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    let paginationContainer = $("#pagination");
    paginationContainer.empty(); // 이전 내용 비우기
    for (let i = 1; i <= totalPages; i++) {
        let pageLink = `<button onclick="changePage(${i})">${i}</button>`;
        paginationContainer.append(pageLink);
    }
}

// 페이지 변경 함수
function changePage(pageNumber) {
    currentPage = pageNumber;
    loadPage(currentPage);
}

// 한 페이지 당 항목 수를 설정하는 함수
function setItemsPerPage(value) {
    itemsPerPage = parseInt(value); // 값(value)을 정수형으로 변환한다.
    loadPage(currentPage); // 새로운 항목 수로 첫 번째 페이지를 다시 불러온다.
}

//-------------------------------------------------------------------
// 검색 버튼을 눌렀을 때 실행되는 JQuery 코드
$(document).ready(function() {
    
    // 검색 버튼에 대한 click 이벤트 핸들러 추가
    $("#searchButton").click(function() {
		
        // 검색어 가져오기
        let name = $("#product_name").val();

        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "uProductSearchServlet",
            data: { name: name },
            dataType: "json", // 서버에서 반환되는 데이터 유형을 JSON으로 기대합니다.
            success: function(response) {
                // 성공적으로 응답을 받으면 카드를 생성합니다.
                createCard(response);
            },
            error: function(xhr, status, error) {
                // 에러 발생 시 메시지를 출력합니다.
                console.error("AJAX 오류: " + status, error);
                alert("검색 중 오류가 발생했습니다.");
            }
        });
    });
});

// createCard 함수: 상품 데이터를 받아 카드를 생성합니다.
function createCard(data) {
	
	//alert("creatCard 실")
    let resultContainer = $("#result");
    
    // 결과를 표시하기 전에 이전 결과를 지웁니다.
    resultContainer.empty();

    // 받은 데이터를 기반으로 각각의 상품에 대한 카드를 생성합니다.
    for (let i = 0; i < data.length; i++) { 
		//alert(data[i].product_name)
        let cardHtml = `
            <div class="card">            
                <img src="image/${data[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                    	<a href="javascript:void(0);" onclick="saveProductInfo(${data[i].product_code},'${data[i].product_name}', ${data[i].price}, '${data[i].origin}', '${data[i].size}', ${data[i].weight})">${data[i].product_name}</a>
                    </h5>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
        `;
        resultContainer.append(cardHtml);
    }
}

//-------------------------------------------------------------------

$(document).ready(function() {
    // 정렬 콤보박스 값 변경 이벤트 처리
    $("#classifyOption").change(function() {
        // 선택된 정렬 옵션을 가져옴
        let classifyOption = $(this).val();
        let name = $("#product_name").val(); // 현재 검색어 가져오기

        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "uProductListServlet",
            data: { name: name, classifyOption: classifyOption }, // 검색어와 정렬 옵션 함께 전송
            dataType: "json",
            success: function(response) {
                // 서버에서 받은 응답 처리
                displayProducts(response, currentPage);
                alert("정렬되었습니다");
            },
            error: function(xhr, status, error) {
                // 에러 발생 시 메시지를 출력합니다.
                console.error("AJAX 오류: " + status, error);
                alert("검색 중 오류가 발생했습니다.");
            }
        });
    });
});


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
    
 
    