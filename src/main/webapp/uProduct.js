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

    for (let i = 0; i < data.length; i++) {
        let cardHtml = `
            <div class="card">
                <img src="${data[i].product_image_names}" class="card-img-top" alt="${data[i].product_name}">
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="productDetail.jsp?product_id=${data[i].product_id}">${data[i].product_name}</a>
                    </h5>
                    <p class="card-text">이미지: ${data[i].product_image_names}</p>
                    <p class="card-text">가격: ${data[i].price}</p>
                </div>
            </div>
        `;
        resultContainer.append(cardHtml);
    }
}
