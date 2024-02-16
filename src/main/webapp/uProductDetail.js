/* ----------------------------------------------------------------------------------
 * Description: 상세페이지에서 사용하는 JS
 * 		Detail:
 * 				1. 상품명,상품상세 등등을 움직이는 css 관련 탭 기능
 * 				2. 상품수량 스피너를 선택할때 선택된 상품의 재고보다 클경우 alert 를 띄워주는 기능
 * 				3. 
 * Author : pdg, ls
 * Date : 2024.02.15
 * 
 * Update --------------------------------------------------------------------------
 * 
 * <<2024.02.15>> by pdg, ls
 *  1. 상품상세 탭 기능 분해및 수정, 디비 결과에 맞추어서 기능 추가. 
 * 	2.장바구니 담기를 클릭했을때 세션에 productSelectedCheck 에 true 가 저장되도록 하는 기능 
 *----------------------------------------------------------------------------------*/
 // 탭 노드리스트에 탭을 클릭했을 때 그 탭의 내용을 show active해주는 기
 // html document 가 클라이언트의 브라우저에 로딩 된후( DOMcontentLoaded) 이벤트를 처리하는 이벤트핸들러(function))
document.addEventListener('DOMContentLoaded', function() {
	
    // html document 에 있는 nav-link 라는 이름의 클래스 를 불러옴(querySelectorAll)
    // query :  정보를 요청하다.  
    // selector : 선택하다. 
    // query selector : 선택적으로 정보를 요청하다. 
    const tabs = document.querySelectorAll('.nav-link');
   	// tab 은 node list 를 가짐. node 는 html 내에서 실시간으로 변할수있는 정보를 의미한다. 
    
    // tabs 라는 노드리스트에 들어있는 노드인 tab 은 노드값의 타입을 자동으로 참조하므로 타입선언을 생략할수있다. 
    tabs.forEach(tab => {
		
		// 노드리스트에 있는 노드를 하나씩 가져오면서 그 tab node에 해당하는 eventLisner 를 선언하고 이에 해당하는 
        tab.addEventListener('click', function() { // 이벤트 핸들러를 실행함. 
			
            // 클릭된 탭의 aria-controls 속성 값을 가져옵니다.
            const tabContentId = this.getAttribute('aria-controls');
            
            // 내가 클릭한 탭 콘텐츠 요소를 가져옵니다.( 요소: object HTMLDivElement )
            const tabContent = document.getElementById(tabContentId);
            
            // 클릭하기 이전에 활성화되어있던 탭 콘텐츠 요소를 가져옵니다.
            const activeTab = document.querySelector('.tab-pane.show.active');
            const tabText = activeTab.textContent;
            
            // 현재 활성화된 탭 콘텐츠의 active 및 show 클래스를 제거합니다.
            activeTab.classList.remove('active', 'show');
            
            // 클릭된 탭 콘텐츠에 active 및 show 클래스를 추가하여 활성화합니다.
            tabContent.classList.add('active', 'show');
        });// tab.addEventListener end
    });//tabs.forEach end
});//document.eventLister end

document.addEventListener('DOMContentLoaded', function() {
     // input 요소 가져오기
     
	 var input = document.getElementById('cart_qty');
	 let cart_button = document.getElementById('cart_button');
	
     // 입력 이벤트 리스너 추가
     input.addEventListener('input', function() {	
		 let input_val = $("#cart_qty").val()
		 let product_qty = $('#product_qty').val()
		 if (parseInt(input_val) >= parseInt(product_qty)){
			 alert(" 재고가 부족합니다. ")
		 }
		 //alert(" input이 입력되었습니다. : "+ input.value)
    	 // 입력된 값에서 숫자 이외의 문자 제거
	     var sanitizedValue = input.value.replace(/\D/g, '');
	
	     // 최소값과 최대값 사이의 값으로 제한
	     sanitizedValue = Math.max(1, Math.min(product_qty-1, sanitizedValue));
	
	     // 입력된 값을 제한된 값으로 설정
	     input.value = sanitizedValue;
	 });//input.addEventListener end
	 
	
});//document.addEventListener end



document.addEventListener('DOMContentLoaded', function() {
 // 장바구니 버튼 눌렀을때 리스너
	cart_button.addEventListener('click', function(){
		alert("장바구니 버튼 click 됨 ")
		
		form = document.cartForm
		productSelectedCheck = true // 장바구니에 한번 insert 되는 순간  True 값이 저장 
		$.ajax({
			type : 'post',
			url:"checkedProductsServlet",
			data: {
					productName: productName,
					productSelectedCheck : productSelectedCheck
			},
			success: function(response) { 
				// session  저장이 완료됨
				alert(productName+"에대한 재실행 여부 가 세션 정보에 저장됨 ")
				form.submit();
				
				}
		})// Ajax end
	})// Cart btn event end
});//document.addEventListener end
