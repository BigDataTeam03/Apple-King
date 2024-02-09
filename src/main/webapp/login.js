/*
 *  Description: login js 
 *  Author : KBS
 *  Date : 2024.02.06
 *  Update --------------------------------------------------
 *  <<2024.02.09 by pdg>>
 *	1. 로그인 버튼이아니라 엔터를 처도 로그인이 되게끔. 
 *----------------------------------------------------------
 */

 
 document.getElementById("text")
    .addEventListener("keyup", function(e) {
        if (e.keyCode === 13) {
            document.getElementById("submit").click();
        }
    });
 
document.getElementById("submit").onclick = function() {
    alert('Clicked!');
}

document.getElementById("id")
	.addEventListener("keyup", function(e){
		if (e.keyCode ===13){
			document.getElementById("submit").click();
		}
	})
document.getElementById("submit").onclick = function(){

	
}





