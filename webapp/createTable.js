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

function generateTableNew() {
    var totalPrice = 0;
    var data = JSON.parse(localStorage.getItem("data"));
    $("#dataTable").empty();
    var table_data =
        "        <table class = \"tableHeaders\">\n" +
        "            <thead>\n" +
        "            <tr>";
    if(data[0].productName) {
        table_data += "<th> Produktnavn </th>"
    }
    if(!isNaN(data[0].price)) {
        table_data += "<th> Pris i kr. (excl. moms) </th>"
    }
    if(data[0].amount !== -1) {
        table_data += "<th> Antal </th>"
    }
    if(data[0].category) {
        table_data += "<th> Kategori </th>"
    }
    if(data[0].buyersName) {
        table_data += "<th> Købers navn </th>"
    }
    if(data[0].comment) {
        table_data += "<th> Kommentar </th>"
    }
    if(data[0].dateOfPurchase) {
        table_data += "<th> Dato for køb </th>"
    }

    table_data +=
        "            </tr>\n" +
        "            </thead>\n" +
        "            <tbody id=\"dataTable\">\n";

    $.each(data, function (key, val) {
        table_data += generateTable(val);
        totalPrice += val.price * (val.amount);
    });

    table_data +=
        "            </tbody>\n" +
        "\n" +
        "        </table>";



    $("#dynamicTable").html(table_data);
    if (!isNaN(data[0].price) && data[0].amount !== -1) {
        $('#tableTotalPrice').append(showTotalPrice(totalPrice));
    }

}

function generateTable(item) {
    var line = "<tr>";

    if(item.productName) {
        line += '<td>' + item.productName + '</td>'
    }
    if(!isNaN(item.price)) {
        line += '<td>' + item.price + '</td>'
    }
    if(item.amount !== -1) {
        line += '<td>' + item.amount + '</td>'
    }
    if(item.category) {
        line += '<td>' + item.category + '</td>'
    }
    if(item.buyersName) {
        line += '<td>' + item.buyersName + '</td>'
    }
    if(item.comment) {
        line += '<td>' + item.comment + '</td>'
    }
    if(item.dateOfPurchase) {
        line += '<td>' + item.dateOfPurchase + '</td>'
    }
    line += '</tr>';

    return line;
}

function showTotalPrice(totalPrice) {
    return '<tr><td>Pris i alt:</td>' +
        '<td>' + totalPrice + " kr. (excl. moms)" + '</td></tr>'
}