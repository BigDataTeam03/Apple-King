   
   
   /* Description: 모달을 사용해서 문의를 등록한다
 * Author : KBS
 * Date : 2024.02.13
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.13 by KBS>>
 *	1. 주석 달음.
 *	2. 모달 팝업
 *----------------------------------------------------------
 */
   
   
   
   
   // 페이지 실행후 바로 상품 전체 조회
window.onload = function() {
	alert(" 문의 테이블 생성 ")
	
	$.ajax({
		
		// post method server request
		type: "POST",
		
		// target server page(Servlet) url
		url: "uProductquestionListServlet",
		
		// request data (JSON)
		//data: { product_code: '<%=session.getAttribute("product_code")%>' },
		
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
    dataReal = Array.from(data)

  

    if ( parseInt(dataReal.length) === 0) {
        $("#questions").html("<p>등록된 문의가 없습니다.</p>");
        return;
    }

  
  //$("#cartList").empty();
    let table =
        "<table border='1'>" +
        "<tr>" +
        "<th>작성자</th>" +
        "<th>내용      </th>" +
        "<th>날짜</th>" +       
        "</tr>";
        
 
    // insert data rows
    for(let i=0; i<data.length; i++)  {
        table += "<tr>" +
            "<td>" + data[i].cust_id + "</td>" + // col1 
            "<td>" + data[i].inquire_content + "</td>" + // col2
            "<td>" + data[i].inquire_date + "</td>" + // col3
            "<td>" + data[i].answer_content + "</td>" + // col4
            "</tr>"         
    }
    
    
    // 만약 데이터가 비어 있다면, 특정 메시지를 표시하고 함수 종료
    // table end
    table += "</table>"

    // html result <- table
   if ( dataReal.length > 0) {
    $("#questions").html(table);
  }
}


   $(document).ready(function() {
    $('#questionForm').submit(function(e) {
        // 폼 기본 제출 동작 막기
       e.preventDefault();

        // 폼 데이터 가져오기
	
		//let id = $("#userId").val()
		let question = $("#question").val()
		
        // AJAX 요청 보내기
        $.ajax({
            type: 'POST',
            url: 'aProductQuestionInsertSurvlet', // 서블릿의 URL
            data: { 
            		question : question    },
            success: function(response) {
		
                // 성공 시 모달 닫기
                createTable(response)
                closeModal();
               
            },
            error: function(xhr, status, error) {
                // 실패 시 에러 처리
                console.error(xhr.responseText);
            }
        });
    });
});

function closeModal() {
    var modal = document.getElementById("insertQuestion");
    modal.style.display = "none";
}

   
    // 문의 작성 버튼 클릭 시 모달 열기
    // 모달에 해당된 작성 폼을 변수에 집어넣는다
    var modal = document.getElementById("insertQuestion");
    //모달창을 열기위한 변수설정
    var openModalBtn = document.getElementById("openModalBtn");
    //모달창을 닫기 위한 변수설정(배열의 첫번째 요소를 선택하여 닫음)
    var closeModalBtn = document.getElementsByClassName("close")[0];
    //모달창을 화면에 보이게 함
    openModalBtn.onclick = function() {
        modal.style.display = "block";
    }
	//모달창을 화면에서 사라지게 함
    closeModalBtn.onclick = function() {
        modal.style.display = "none";
    }
	//모달창이 열려있을 때 화면 바깥을 클릭하면 모달창이 닫히는 기능
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

   

    