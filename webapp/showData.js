//global array. This way we can create other functions
//and still use this "categories" array.
var categoriesSearchArray = ["Ingen kategori"];
var nameSearchArray = ["Intet navn"];

//function is executed right after the website is loaded.
function loadFromServerToSearch() {
    $.getJSON("rest/item/getCategory/", function (data) {
        $.each(data, function (key, val) {
            //adding values to the global array
            console.log("Data: " + data);
            categoriesSearchArray.push(val);
        });
        makeCatSearchDropdown(categoriesSearchArray);
    });
    $.getJSON("rest/item/getBuyerNames/", function (data) {
        $.each(data, function (key, name) {
            console.log(key + ": " + name);
            nameSearchArray.push(name);
        });
        console.log(nameSearchArray);
        makeNameSearchDropdown(nameSearchArray);
    });
}

function makeCatSearchDropdown(data) {
    //getting jsonArray from server at "rest/item" as a GET.
    var dropdown = document.getElementById("categorySearch");
    console.log(data);
    //we loop over the data in jsonArray.
    $.each(data, function (key, val) {
        //creating our dropdown list of the data in the array
        var opt = document.createElement("option");
        opt.text = val;
        opt.value = val;
        dropdown.add(opt);
    })
}

function makeNameSearchDropdown(data) {
    var dropdown = document.getElementById("buyersNameSearch");
    $.each(data, function (key, name) {
        var opt = document.createElement("option");
        opt.text = name;
        opt.value = name;
        dropdown.add(opt);
    })
}

//sends json object to url="rest/item" when clicked on the button.
//this is for the server to "catch" it from that url.
function sendItemToServer() {
    event.preventDefault();
    var data = $('#itemForm').serializeJSON();
    console.log(data);
    $.ajax({
        url: 'rest/item/',
        method: 'POST',
        contentType: "application/json",
        data: data
    });
};