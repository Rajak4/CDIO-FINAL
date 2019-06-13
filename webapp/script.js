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
    var category = document.getElementById("testAddCategory").value;
    var dropdown = document.getElementById("category");
    var opt = document.createElement("option");
    items.push(category);
    console.log("cat: " + category + " itemslength: " + items.length);
    opt.text = category;
    opt.value = category;
    dropdown.add(opt);
    alert("Du tilføjede kategorien: " + category);

    event.preventDefault();
    var data = $('#testAddCategory').serializeJSON();
    console.log("send to java: " + data);
    $.ajax({
        url: 'rest/item/add/',
        method: 'POST',
        contentType: 'application/json',
        data: data
    });

    //clear input field
    document.getElementById("testAddCategory").value = "";
}