//should add <option> tags with values from the array.
//This way there can only be searched in those specific words.

//global array. This way we can create other functions
//and still use this "items" array.
var items = [];

//function is executed right after the website is loaded.
function makeDropdown() {
    //getting jsonArray from server at "rest/item" as a GET.
    var dropdown = document.getElementById("category");
    $.getJSON("rest/item/", function (data) {
        console.log(data);
        //we loop over the data in jsonArray.
        $.each(data, function (key, val) {
            console.log(key + " : " + val);
            //adding values to the global array
            items.push(val);
            //creating our dropdown list of the data in the array
            var opt = document.createElement("option");
            opt.text = val;
            opt.value = val;
            dropdown.add(opt);
            console.log("arraylength: " + items.length);
        })
    });
}

function addCategory() {
    var catText = document.getElementById("categoryText").value;
    var catNum = document.getElementById("categoryNumber").value;
    var catString = catNum + " - " + catText;
    var dropdown = document.getElementById("category");
    var opt = document.createElement("option");
    items.push(catString);
    console.log("cat: " + catString + " itemslength: " + items.length);
    opt.text = catString;
    opt.value = catString;
    dropdown.add(opt);
    alert("Du tilføjede kategorien: " + catString);

    event.preventDefault();
    var data = $('#catForm').serializeJSON();
    console.log("send to java: " + data);
    $.ajax({
        url: 'rest/item/add/',
        method: 'POST',
        contentType: 'application/json',
        data: data
    });

    //clear input field
    document.getElementById("categoryNumber").value = "";
    document.getElementById("categoryText").value = "";
}