function createTable() {
    var totalPrice = 0;
    var data = JSON.parse(localStorage.getItem("data"));
    $("#dataTable").empty();
    $.each(data, function (key, val) {
        totalPrice += val.price * (val.amount);
        $('#dataTable').append(generateTable(val));
    });
    $('#tableTotalPrice').append(showTotalPrice(totalPrice));
}

function generateTable(item) {
    return '<tr><td>' + item.productName + '</td>' +
        '<td>' + item.price + '</td>' +
        '<td>' + item.amount + '</td>' +
        '<td>' + item.category + '</td>' +
        '<td>' + item.buyersName + '</td>' +
        '<td>' + item.comment + '</td>' +
        '<td>' + item.dateOfPurchase + '</td></tr>'
}

function showTotalPrice(totalPrice) {
    return '<tr><td>Pris i alt:</td>' +
        '<td>' + totalPrice + " kr. (excl. moms)" + '</td></tr>'
}