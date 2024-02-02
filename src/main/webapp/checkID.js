$(document).ready(function() {
    // Assuming 'defaultId' is the initial value you want to check
    let defaultId = "sumink411";

    $.ajax({
        type: "POST",
        url: "checkid.do", // Adjust the URL to your server-side check endpoint
        data: { id: defaultId },
        success: function(response) {
            if (response === "available") {
                alert("아이디를 사용할 수 있습니다.");
                $("#id").prop("readonly", true); // Use jQuery to set readonly property
            } else if (response === "unavailable") {
                alert("아이디가 이미 사용 중입니다. 다른 아이디를 선택해주세요.");
            } else {
                alert("서버 오류: " + response);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("AJAX 오류: " + textStatus + " - " + errorThrown);
        }
    });
});