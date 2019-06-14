package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dto.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemService {

    private List<Item> itemList = new ArrayList<>();
    private static List<String> categories = new ArrayList<>();

    static {
        categories.add("10 - IT");
        categories.add("11 - Kørsel");
        categories.add("12 - Cafeteriet");
        categories.add("13 - Frynsegoder");
        categories.add("14 - Kontor");
    }

    @POST
    public void getItem(String body) {
        JSONObject jsonObject = new JSONObject(body);

        Item item = new Item(jsonObject.getString("category"));
        itemList.add(item);

        System.out.println("date is: " + jsonObject.getString("dateOfPurchase"));
    }

    @GET
    public JSONArray categoryArray() {
        return new JSONArray(categories);
    }

    // TODO: 13-06-2019 gør det samme for købers navn
    @Path("add")
    @POST
    public void addCategory(String category) {
        //creating JSON object of the string we get
        JSONObject catObject = new JSONObject(category);
        //take the string out of our JSON object
        //here: json-key=testAddCategory and
        //json-value=value of the text field
        int catNum = catObject.getInt("categoryNumber");
        String catText = catObject.getString("categoryText");
        String catString = catNum + " - " + catText;
        this.categories.add(catString);
        System.out.println("added: " + catString);
        for(String s: categories) {
            System.out.println(s);
        }
    }

}
