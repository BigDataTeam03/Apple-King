let currentPage = 1;
const itemsPerPage = 6;

// 페이지 로드 시 처음 페이지 로드
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

// 상품 목록 출력 함수
function displayProducts(data, pageNumber) {
    let resultContainer = $("#result");
    resultContainer.empty(); // 이전 내용 비우기

    const startIndex = (pageNumber - 1) * itemsPerPage;
    const endIndex = pageNumber * itemsPerPage;
    const pageData = data.slice(startIndex, endIndex);

    for (let i = 0; i < pageData.length; i++) {
        let cardHtml = `
           <div class="card">            
               <img src="image/${pageData[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                       <a href="productDetail.do?product_name=${pageData[i].product_name}&price=${pageData[i].price}
                              &origin=${pageData[i].origin}&size=${pageData[i].size}&weight=${pageData[i].weight}"> 
                           ${pageData[i].product_name}
                          </a>
                    </h5>
                    <p class="card-text">가격: ${pageData[i].price}</p>
                </div>
            </div>
             `;
        resultContainer.append(cardHtml);
    }
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
    let resultContainer = $("#result");
    
    // 결과를 표시하기 전에 이전 결과를 지웁니다.
    resultContainer.empty();

    // 받은 데이터를 기반으로 각각의 상품에 대한 카드를 생성합니다.
    for (let i = 0; i < data.length; i++) {
        let cardHtml = `
            <div class="card">            
                <img src="image/${data[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="productDetail.do?product_name=${data[i].product_name}&price=${data[i].price}&origin=${data[i].origin}&size=${data[i].size}&weight=${data[i].weight}">${data[i].product_name}</a>
                    </h5>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
        `;
        resultContainer.append(cardHtml);
    }
}


//-------------------------------------------------------------------

	//정렬기능과 검색기능을 같이 서버에 보내야 검색후 정렬을 시행해도 검색이 유지된다
$(document).ready(function() {
    // 정렬 콤보박스 값 변경 이벤트 처리
    $("#classifyOption").change(function() {
        // 선택된 정렬 옵션을 가져옴
        let classifyOption = $(this).val();
        let name = $("#product_name").val(); // 현재 검색어 가져오기
        
       alert("정렬되었습니다")

        // AJAX 요청
        $.ajax({
            type: "POST",
            url: "uProductListServlet",
            data: { name: name, classifyOption: classifyOption }, // 검색어와 정렬 옵션 함께 전송
            dataType: "json",
            success: function(response) {
                // 서버에서 받은 응답 처리
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
    let resultContainer = $("#result");
    
    // 결과를 표시하기 전에 이전 결과를 지웁니다.
    resultContainer.empty();

    // 받은 데이터를 기반으로 각각의 상품에 대한 카드를 생성합니다.
    for (let i = 0; i < data.length; i++) {
        let cardHtml = `
            <div class="card">            
                <img src="image/${data[i].product_image_names}" > 
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="productDetail.do?product_name=${data[i].product_name}&price=${data[i].price}&origin=${data[i].origin}&size=${data[i].size}&weight=${data[i].weight}">${data[i].product_name}</a>
                    </h5>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
        `;
        resultContainer.append(cardHtml);
    }
}
   
    

    
    