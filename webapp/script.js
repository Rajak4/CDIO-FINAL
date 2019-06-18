//global array. This way we can create other functions
//and still use this "categories" array.
var categories = [];
var nameArray = [];
//value that our numbers start at
var catStartVal = 10;

//sends json object to url="rest/item" when clicked on the button.
//this is for the server to "catch" it from that url.
function sendItemToServer() {
    event.preventDefault();
    var data = $('#itemForm').serializeJSON();
    console.log(data);
    $.ajax({
        url: 'rest/item/receiveForm/',
        method: 'POST',
        contentType: "application/json",
        data: data
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
function loadFromServer() {
    loadCategories();
    loadBuyers();
}

function loadCategories() {
    categories = [];
    $.getJSON("rest/item/getCategory/", function (data) {
        $.each(data, function (key, val) {
            //adding values to the global array
            categories.push(val);
        });
        makeCatDropdown(categories);
    });

    console.log(categories);
}

function loadBuyers(){
    nameArray =[];
    $.getJSON("rest/item/getBuyerNames/", function (data) {
        $.each(data, function (key, name) {
            console.log(key + ": " + name);
            nameArray.push(name);
        });
        console.log(nameArray);
        makeNameDropdown(nameArray);
    });
}

function makeCatDropdown(data) {
    //getting jsonArray from server at "rest/item" as a GET.
    var dropdown = document.getElementById("category");
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
    console.log("send to java: " + data);
    $.ajax({
        url: 'rest/item/addCategory/',
        method: 'POST',
        contentType: 'application/json',
        data: data,
        success: function() {
            $("#category").empty();
            loadCategories();
        }
    });
}

function deleteCategory() {
    var numToDel = prompt("Nummer på kategori, der skal slettes?", "");
    if(numToDel == null || numToDel == "") {
        //do nothing when cancel.
    } else {
        //save the position and name of the element we are deleting
        var posToDel = numToDel - catStartVal;
        var catToDel = categories[posToDel];
        //delete 1 element starting at "posToDel"
        categories.splice(posToDel, 1);
        console.log(categories);

        //sending the position of the element we are deleting
        //as JSON to Java so we can delete from java arraylist as well
        event.preventDefault();
        var obj = {"numToDel" : posToDel};
        var data = JSON.stringify(obj);
        console.log("hej=" +data);
        $.ajax({
            url: 'rest/item/deleteCategory/',
            method: 'POST',
            contentType: 'application/json',
            data: data
        });
        //write to user. After we reload the page (update the select/dropdown)
        alert("Du slettede kategorien: " + catToDel);
        window.location.reload();
    }
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
        console.log("send to java: " + data);
        $.ajax({
            url: 'rest/item/addBuyersName/',
            method: 'POST',
            contentType: 'application/json',
            data: data,
            success: function() {
                $("#buyersName").empty();
                loadBuyers();
            }
        });
}

function deleteBuyer() {
    var numToDel = prompt("Nummer på personen, der skal slettes?", "");
    if(numToDel == null || numToDel == "") {
        //do nothing when cancel.
    } else {
        //save the position and name of the element we are deleting
        var posToDel = numToDel - 1;
        var nameToDel = nameArray[posToDel];
        //delete 1 element starting at "posToDel"
        nameArray.splice(posToDel, 1);
        console.log(nameArray);

        //sending the position of the element we are deleting
        //as JSON to Java so we can delete from java arraylist as well
        event.preventDefault();
        var obj = {"numToDel" : posToDel};
        var data = JSON.stringify(obj);
        console.log("hej=" +data);
        $.ajax({
            url: 'rest/item/deleteName/',
            method: 'POST',
            contentType: 'application/json',
            data: data
        });
        //write to user. After we reload the page (update the select/dropdown)
        alert("Du slettede køberen: " + nameToDel);
        window.location.reload();
    }
}