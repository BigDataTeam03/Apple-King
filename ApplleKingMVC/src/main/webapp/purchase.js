window.onload = function() {
	
	$.ajax({
		
		// post method server request
		type: "POST",
		
		// target server page(Servlet) url
		url: "purchaseServlet",
		
		// request data (JSON)
		//data: { cust_id: "s" }, 
		
		// response data type -> JSON
		dataType :"json",
		
		// server response success  -> response(Json data)
		success: function(response) {
			createTable(response);
	
		},	
		
		error: function(xhr, status, error) {
            console.error("Error occurred while sending AJAX request:", error);
        }
	});
};

// 테이블 생성하는 함수
function createTable(data) {
   // JSON 데이터를 파싱합니다.
    var purchaseData = JSON.parse(data);

  // 테이블 컨테이너에 대한 참조를 가져옵니다.
    var tableContainer = $("#purchase");
  
    var tableHtml =
        "<table border='1'>" +
        "<tr>" +
        "<th>이름</th>" +
        "<th>이메일</th>" +
        "<th>휴대폰 번호</th>" +
        "</tr>";
        
     // 데이터를 순회하며 테이블에 행을 추가합니다.
    for (var i = 0; i < purchaseData.length; i++) {
        var rowData = purchaseData[i];
        tableHtml += "<tr>" +
                     "<td>" + rowData.name + "</td>" +
                     "<td>" + rowData.email + "</td>" +
                     "<td>" + rowData.tel + "</td>" +
                     "</tr>";
    }    
        
          // table end
    tableHtml += "</table>";
        
     // html result <- table
    tableContainer.html(tableHtml);
         
         
         }
    
   
    
        
        
        
        
        
        
        
        
        
        