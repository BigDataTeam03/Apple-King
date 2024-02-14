   
   
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

   

    