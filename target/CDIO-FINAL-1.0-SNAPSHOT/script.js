var items = ["hej", "med", "dig", "4", "5"];
$.each(items, function (i, item) {
    console.log(i + " : " + item);
    $('#mySelect').append($('<option>', {
        value: item.value,
        text : item.text
    }));
});