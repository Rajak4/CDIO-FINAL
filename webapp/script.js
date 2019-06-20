//global array. This way we can create other functions
//and still use this "categories" array.
var categories = [];
var nameArray = [];
//value that our numbers start at

//sends json object to url="rest/item" when clicked on the button.
//this is for the server to "catch" it from that url.


function sendItemToServer() {
    var data = $('#itemForm').serializeJSON();
    $.ajax({
        url: 'rest/item/receiveForm/',
        method: 'POST',
        contentType: "application/json",
        data: data,
        success: function(){
            Swal.fire({
                type: 'success',
                title: 'Information er blevet sendt!',
                timer: 1500,
                showConfirmButton: false
            });
            $('#itemForm')[0].reset();
        }

    });
}

function sendItemToServer2() {
    event.preventDefault();
    var data = $('#itemForm').serializeJSON();
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

async function addUserButton() {
    const {value: name}  = await swal.fire({
        input: 'text',
        inputPlaceholder: 'Indtast et navn',
        showCancelButton: true
    });

    if (name) {
        addBuyer(name)
    }
}

async function addCategoryButton() {
    const {value: category}  = await swal.fire({
        input: 'text',
        inputPlaceholder: 'Indtast en categori',
        showCancelButton: true
    });

    if (category) {
        addCategory(category)
    }
}


//function is executed right after the website is loaded.
function loadFromServer(startValue) {
    loadCategories(startValue);
    loadBuyers(startValue);
}

function loadCategories(startValue) {
    if (startValue) {
        categories = [["0","Alle kategorier"]];
    } else { categories = []; }
    $.getJSON("rest/item/getCategory/", function (data) {
        $.each(data, function (key, val) {
            //adding values to the global array
            categories.push(val);
        });
        makeCatDropdown(categories);
    });
}

function loadBuyers(startValue){
    if (startValue) {
        nameArray = [["0", "Alle navne"]];
    } else { nameArray = []; }
    $.getJSON("rest/item/getBuyerNames/", function (data) {
        $.each(data, function (key, name) {
            nameArray.push(name);
        });
        makeNameDropdown(nameArray);
    });
}

function makeCatDropdown(data) {
    //getting jsonArray from server at "rest/item" as a GET.
    var dropdown = document.getElementById("category");
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

function makeNameDropdown(data) {
    var dropdown = document.getElementById("buyersName");
    $.each(data, function (key, name) {
        var nameAndNum = name[1];
        var opt = document.createElement("option");
        opt.text = nameAndNum;
        opt.value = nameAndNum;
        dropdown.add(opt);
    })
}

function addCategory(name) {
    event.preventDefault();
    //creating a javascript object that we then make a JSON string
    //We only give the name to the server. Number adding is only in JS
    var obj = {
        "category" : name
    };
    var data = JSON.stringify(obj);
    $.ajax({
        url: 'rest/item/addCategory/',
        method: 'POST',
        contentType: 'application/json',
        data: data,
        success: function() {
            Swal.fire({
                type: 'success',
                title: name + ' er blevet tilføjet!',
                timer: 1500,
                showConfirmButton: false
            });
            $("#category").empty();
            loadCategories();
        }
    });
}


function addBuyer(name) {
        //get elements of the select aka. dropdown

        event.preventDefault();
        //creating a javascript object that we then make a JSON string
        //We only give the name to the server. Number adding is only in JS
        var obj = {
            "buyersName" : name
        };
        var data = JSON.stringify(obj);
        $.ajax({
            url: 'rest/item/addBuyersName/',
            method: 'POST',
            contentType: 'application/json',
            data: data,
            success: function() {
                Swal.fire({
                    type: 'success',
                    title: name + ' er blevet tilføjet!',
                    timer: 1500,
                    showConfirmButton: false
                });
                $("#buyersName").empty();
                loadBuyers();
            }
        });
}