
/*
	
	Description : 구매페이지에서 수량 변경할때 실시간으로 구매 총금액이 바뀌게
	Date 		:2024.02.28
	Author		:pdg
	Udpate
*/



$(document).ready(function() {
   //Start message
   console.log("**<< JS START purchase.js >>**")
   
   $('input[name="order_qty"]').change(function(){
	    let order_qty = $(this).val()
		console.log("구매수량 : "+ order_qty)	 
		var product_price =  document.getElementById("product_price").value;
		console.log("상품가격 "+order_qty*product_price)
	    
	    let total_price = order_qty*product_price
	    $('#total_price').text(total_price.toLocaleString()+ '원');
	    
   })
   
   
   
   });
    
        
        
        
        
        
        
        
        
        
        