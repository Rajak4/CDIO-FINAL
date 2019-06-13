//should add <option> tags with values from the array.
//This way there can only be searched in those specific words.
var items = ["hej", "med", "dig", "4", "5"];
function makeDropdown() {
    var dropdown = document.getElementById("category");
    for (var i = 0; i < items.length; i++) {
        var opt = document.createElement("option");
        opt.text = items[i];
        opt.value = items[i];
        dropdown.add(opt);
    }
}
