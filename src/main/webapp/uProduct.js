window.onload = function () {
    $.ajax({
        type: "POST",
        url: "uProductListServlet",
        data: { name: "" },
        dataType: "json",
        success: function (response) {
            createCard(response);
        },
    });
};

function createCard(data) {
    let resultContainer = $("#result");
    //alert("나는 실행된다. ")

    for (let i = 0; i < data.length; i++) {
		
        let cardHtml = `
      
        	
            <div class="card">
            
               <img src="image/${data[i].product_image_names}" > 
                
               
              			
                
                <div class="card-body">
                    <h5 class="card-title">
                    
                    
                    
                        <a href="productDetail.do?product_name=${data[i].product_name}&price=${data[i].price}
                        						  &origin=${data[i].origin}&size=${data[i].size}&weight=${data[i].weight}"> 
                        	${data[i].product_name}
                      
                        	
                       	</a>
                    </h5>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
            
            
            
            
            
        `;
        resultContainer.append(cardHtml);
    }
}
