//should add <option> tags with values from the array.
//This way there can only be searched in those specific words.

//global array. This way we can create other functions
//and still use this "categories" array.
var categories = [];
var nameArray = [];
//value that our numbers start at
var catStartVal = 10;

//function is executed right after the website is loaded.
function loadFromServer() {
    $.getJSON("rest/item/getCategory/", function (data) {
        $.each(data, function (key, val) {
            //adding values to the global array
            categories.push(val);
        });
        makeCatDropdown(categories);
    });
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
    $.each(data, function (key, val) {
        var catString = ((catStartVal + key) + " - " + val);
        console.log(key + " : " + val + " nr: " + (catStartVal+key));
        //creating our dropdown list of the data in the array
        var opt = document.createElement("option");
        opt.text = catString;
        opt.value = catString;
        dropdown.add(opt);
        console.log("arraylength: " + categories.length);
    })
}

function makeNameDropdown(data) {
    var dropdown = document.getElementById("buyersName");
    $.each(data, function (key, name) {
        var nameAndNum = (key + 1) + " - " + name;
        var opt = document.createElement("option");
        opt.text = nameAndNum;
        opt.value = nameAndNum;
        dropdown.add(opt);
    })
}

function addCategory() {
    var categoryName = prompt("Hvad skal kategorien være?", "");
    if(categoryName == null || categoryName == "") {
        //do nothing when cancel.
    } else {
        //get elements of the select aka. dropdown
        var dropdown = document.getElementById("category");
        var opt = document.createElement("option");

        //creating a string that looks like "'num' - 'name'" fx "10 - IT".
        var catString = (catStartVal + categories.length) + " - " + categoryName;
        categories.push(catString);
        console.log("cat: " + catString + " itemslength: " + categories.length);
        opt.text = catString;
        opt.value = catString;
        dropdown.add(opt);
        alert("Du tilføjede kategorien: \"" + categoryName + "\" med nummer: " + (catStartVal + categories.length-1));

        event.preventDefault();
        //creating a javascript object that we then make a JSON string
        //We only give the name to the server. Number adding is only in JS
        var obj = {
            "category" : categoryName
        };
        var data = JSON.stringify(obj);
        console.log("send to java: " + data);
        $.ajax({
            url: 'rest/item/addCategory/',
            method: 'POST',
            contentType: 'application/json',
            data: data
        });
    }
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

function addBuyer() {
    var buyersName = prompt("Købers navn?", "");
    if(buyersName == null || buyersName == "") {
        //do nothing when cancel.
    } else {
        //get elements of the select aka. dropdown
        var dropdown = document.getElementById("buyersName");
        var opt = document.createElement("option");

        var nameAndNum = (nameArray.length+1) + " - " + buyersName;
        nameArray.push(nameAndNum);
        console.log("køber: " + nameAndNum);
        opt.text = nameAndNum;
        opt.value = nameAndNum;
        dropdown.add(opt);
        alert("Du tilføjede personen: \"" + buyersName + "\" som køber med nummeret: " + (nameArray.length));

        event.preventDefault();
        //creating a javascript object that we then make a JSON string
        //We only give the name to the server. Number adding is only in JS
        var obj = {
            "buyersName" : buyersName
        };
        var data = JSON.stringify(obj);
        console.log("send to java: " + data);
        $.ajax({
            url: 'rest/item/addBuyersName/',
            method: 'POST',
            contentType: 'application/json',
            data: data
        });
    }
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