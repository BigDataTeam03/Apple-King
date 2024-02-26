 /* Description: 모달을 사용해서 문의를 등록한다
 * Author : KBS
 * Date : 2024.02.16
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.16 by KBS>>
 *	1. 주석 달음.
 *	
 *----------------------------------------------------------
 */
   
   
 // 페이지 실행후 바로 답글이 안달린 문의 출력
window.onload = function() {
    alert("문의 테이블 생성");

    $.ajax({

        // post method server request
        type: "POST",

        // target server page(Servlet) url
        url: "/inqueireList",

        // request data (JSON)
        //data: { product_code: '<%=session.getAttribute("product_code")%>' },

        // response data type -> JSON
        dataType: "json",

        // server response success  -> response(Json data)
        success: function(response) {
            createTable(response);
            console.log(response[0].cust_id)
            console.log(response[0].product_code)
            console.log(response[0].inquire_date)
            console.log(response[4].inquire_code)
        },
    });
};

// 문의 내용을 클릭하면 모달 창 띄우기
$(document).on("click", "#answerList tr", function() {
    // 현재 클릭한 행("tr") 에서 두번째 열 ("td") 를 찾는다
    let inquireContent = $(this).find("td:eq(1)").text(); // 문의 내용 가져오기

    // 모달 창에 문의 내용 표시
    $("#modalInquireContent").text(inquireContent);

    // 모달 창 띄우기
    $("#replyModal").modal("show");
});

// 확인 버튼 클릭 시 답글 전송
$("#answerBtn").click(function() {
    let answer = $("#answer").val(); // 답글 내용 가져오기
    					
     let code = $(this).find("td:eq(0)").text(); // 문의 코드 가져오기
    
    alert(" 답글 보내보자" + answer + " 문의코드" + code);

    $.ajax({

        // post method server request
        type: "POST",

        // target server page(Servlet) url
        url: "aProductAnswerInsertServlet",

        // request data (JSON)
        data: { answer : answer,
        		  code : code    },

        // response data type -> JSON
        dataType: "json",

        // server response success  -> response(Json data)
        success: function(response) {
      		// 테이블 다시 불러오기
            $.ajax({
                type: "POST",
                url: "aQuestionListServlet",
                dataType: "json",
                success: function(response) {
                    createTable(response); // 답글이 등록된 후 문의 목록을 다시 가져와서 테이블을 갱신
                },
                error: function(xhr, status, error) {
                 			alert(" error")
                }
            });
        },
        error: function(xhr, status, error) {
           alert(" error")
        }
    });
});

// 테이블 생성하는 함수
function createTable(data) {
    //검색해온 데이터(dtos -> json -> Array  변환)
    dataReal = Array.from(data)

    //$("#cartList").empty();
    let table =
        "<table border='1'>" +
        "<tr>" +
        "<th>문의 코드<th>" +
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
        // 문의 내용을 클릭하면 모달 창 띄우기
        $(document).on("click", "#answerList tr", function() {
            let inquireContent = $(this).find("td:eq(1)").text(); // 문의 내용 가져오기
            $("#modalInquireContent").text(inquireContent); // 모달 창에 문의 내용 표시
            $("#replyModal").modal("show"); // 모달 창 띄우기
        });

        // 모달 닫기 이벤트
        $(document).on("click", ".modal .close, .modal .modal-footer button[data-dismiss='modal']", function() {
            $(this).closest(".modal").modal("hide"); // 모달 닫기
        });
    });
    
    

    
    
    

