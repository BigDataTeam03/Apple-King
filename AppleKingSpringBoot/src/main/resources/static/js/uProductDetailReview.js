   
   /* Description: 모달을 사용해서 문의를 등록한다
 * Author : KBS
 * Date : 2024.02.27
 * Warning :
 * Update --------------------------------------------------
 * <<2024.02.27 by KBS>>
 *	1. 주석 달음.
 *	2. 모달 팝업
    3. 좋아요 +1 기능 추가
    4. 인서트시 이동 추가
   <<2024.02.29 by KBS>>
    1. 인서트시 상품 정보 null 나오던 문제 해결
 *----------------------------------------------------------
 */
   
   
   
 $(document).ready(function() {
    // 도움이 되요 버튼 클릭 이벤트
    $('button[type="submit"]').on('click', function(e) {
        e.preventDefault(); // 기본 이벤트 제거

        var form = $(this).closest('form'); // 가장 가까운 form 요소 선택
        var reviewCode = form.find('input[name="review"]').val(); // 리뷰 코드 가져오기

        $.ajax({
            type: 'post',
            url: '/upGood', // 도움이 되요 요청을 처리할 URL
            data: {
                review: reviewCode
            },
            success: function(response) {
         	   	 
                // 도움이 되요 카운트 업데이트 등 추가 작업 수행 가능
            },
            error: function(xhr, status, error) {
				var errorMessage = xhr.responseText; // 오류 메시지 가져오기
                alert(errorMessage); // 오류 시 메시지 출력
            }
        });
    });
    
      // 모달 열기
    $('#writeReview').on('click', function() {
        $('#modal-container').show();
    });

    // X 클릭시 모달 닫기
    $('.close').on('click', function() {
        $('#modal-container').hide();
    });
 // 모달 외부 클릭 시 모달 닫기
    $(window).on('click', function(event) {
        if (event.target == $('#modal-container')[0]) {
            $('#modal-container').hide();
        }
    });
    // 리뷰 작성 모달에서 상품 이름 설정
    $('#modal-container #product-name').val('${product.name}');
    
    
});

 $(document).ready(function() {
    // 리뷰 작성 폼 submit 이벤트
    $('#closeModal').click(function() {
  

        // 리뷰 작성 후에 모달 닫기
        $('#modal-container').hide();

    });
});



