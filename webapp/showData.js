//global array. This way we can create other functions
//and still use this "categories" array.
var categoriesSearchArray = [["0","Alle kategorier"]];
var nameSearchArray = [["1", "Alle navne"]];

//function is executed right after the website is loaded.
function loadFromServerToSearch() {
    loadCategories();
    loadBuyers();
}

function loadCategories() {
    $.getJSON("rest/item/getCategory/", function (data) {
        $.each(data, function (key, val) {
            //adding values to the global array
            categoriesSearchArray.push(val);
        });
        makeCatSearchDropdown(categoriesSearchArray);
    });

    console.log(categoriesSearchArray);
}

function loadBuyers(){
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
    $.each(data, function (key, val) {;
        var catString = (val[0] + " - " + val[1]);
        //creating our dropdown list of the data in the array
        var opt = document.createElement("option");
        opt.text = catString;
        opt.value = catString;
        dropdown.add(opt);
    })
}

function makeNameSearchDropdown(data) {
    var dropdown = document.getElementById("buyersNameSearch");
    $.each(data, function (key, name) {
        var nameAndNum = name[1];
        var opt = document.createElement("option");
        opt.text = nameAndNum;
        opt.value = nameAndNum;
        dropdown.add(opt);
    })
}

//sends json object to url="rest/item" when clicked on the button.
//this is for the server to "catch" it from that url.
function sendItemToServer() {
    event.preventDefault();
    var data = $('#showDataForm').serializeJSON();
    console.log(data);
    $.ajax({
        url: 'rest/item/getShowData/',
        method: 'POST',
        contentType: "application/json",
        data: data,
        success: function (data) {
            localStorage.setItem("data", JSON.stringify(data));
            window.open("table.html", "_self");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    });
}