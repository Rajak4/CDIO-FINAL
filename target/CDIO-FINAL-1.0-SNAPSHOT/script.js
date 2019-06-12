//suggestion by list in input=category
$(function () {
    var categories = ["c++", "java", "php", "coldfusion", "javascript", "asp", "ruby"];
    $("#category").autocomplete({
        source: categories,
        minLenght: 0
    }).focus(function () {
        $(this).autocomplete("Search", "");
    })
});



