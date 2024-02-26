 /* Description: 모달을 사용해서 문의를 등록한다
 * Author : KBS
 * Date : 2024.02.16
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.16 by KBS>>
 *	1. 주석 달음.
 *	2. 모달창 팝업 완료
 Update --------------------------------------------------
 * <<2024.02.26 by KBS>>
 * 	1. 문의게시판 답변 달기 기능 완료
 *  
 *----------------------------------------------------------
 */
   
   
 // 페이지 실행후 바로 답글이 안달린 문의 출력
window.onload = function() {
    $.ajax({
        type: "POST",
        url: "/inqueireList",
        dataType: "json",
        success: function(response) {
            createTable(response);
        },
    });
};

// 문의 내용을 클릭하면 모달 창 띄우기
$(document).on("click", "#answerList tr", function() {
    // 현재 클릭한 행("tr") 에서 두번째 열 ("td") 를 찾는다
    let inquireContent = $(this).find("td:eq(2)").text(); // 문의 내용 가져오기
    let inquireCode = $(this).find("td:eq(0)").text(); // 문의 코드 가져오기

    // 모달 창에 문의 내용 표시
    $("#modalInquireContent").text(inquireContent);
    // 문의 코드를 모달에 추가
    $("#modalInquireCode").text(inquireCode);
    // 모달 창 띄우기
    $("#replyModal").modal("show");
});

// 확인 버튼 클릭 시 답글 전송
$("#answerBtn").click(function() {
    let answer = $("#answer").val(); // 답글 내용 가져오기
    let code = $("#modalInquireCode").text(); // 문의 코드 가져오기
	
    $.ajax({
        type: "POST",
        url: "/insertAnswer",
        data: {
            answer: answer,
            code: code
        },
     
        success: function(response) {
            // 테이블 다시 불러오기
            $.ajax({
                type: "post",
                url: "/inqueireList",
                dataType: "json",
                success: function(response) {
                    createTable(response); // 답글이 등록된 후 문의 목록을 다시 가져와서 테이블을 갱신
                  // 답변 등록 완료 메시지 표시
                    alert("답변이 성공적으로 등록되었습니다.");
                    // 모달 창 닫기
                    $("#replyModal").modal("hide");
                }
                
            });
        }
     
    });
});

// 테이블 생성하는 함수
function createTable(data) {
    //검색해온 데이터(dtos -> json -> Array  변환)
    dataReal = Array.from(data)

    if (parseInt(dataReal.length) === 0) {
        $("#answerList").html("<p>등록된 문의가 없습니다.</p>");
        return;
    }

    let table =
        "<table border='1'>" +
        "<tr>" +
        "<th>문의 코드</th>" +
        "<th>작성자</th>" +
        "<th>내용</th>" +
        "<th>날짜</th>" +
        "<th>상품명</th>" +
        "</tr>";

    // insert data rows
    for (let i = 0; i < data.length; i++) {
        table += "<tr>" +
            "<td>" + data[i].inquire_code + "</td>" + // col1 
            "<td>" + data[i].cust_id + "</td>" + // col2 
            "<td>" + data[i].inquire_content + "</td>" + // col3
            "<td>" + data[i].inquire_date + "</td>" + // col4
            "<td>" + data[i].product_name + "</td>" + // col5
            "</tr>"
    }
    // table end
    table += "</table>"

    // html result <- table
    $("#answerList").html(table);
}

// 모달창을 띄우고 닫는 기능
$(document).ready(function() {
    // 모달 닫기 이벤트
    $(document).on("click", ".modal .close, .modal .modal-footer button[data-dismiss='modal']", function() {
        $(this).closest(".modal").modal("hide"); // 모달 닫기
    });
});

    
    
    

