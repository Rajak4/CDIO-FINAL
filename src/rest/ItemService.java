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
        categories.add("IT");
        categories.add("Kørsel");
        categories.add("Cafeteriet");
        categories.add("Frynsegoder");
        categories.add("Kontor");
    }

    @POST
    public void getItem(String body) {
        JSONObject jsonObject = new JSONObject(body);

        Item item = new Item(jsonObject.getString("category"));
        itemList.add(item);

        System.out.println("date is: " + jsonObject.getString("dateOfPurchase"));
        /*
        for(Item item1 : itemList) {
            System.out.println(item1);
        }
        */
    }

    @Path("getCategory")
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
        String catString = catObject.getString("category");
        this.categories.add(catString);
        System.out.println("added: " + catString);
        for(String s: categories) {
            System.out.println("add: "+s);
        }
    }

    @Path("delete")
    @POST
    public void deleteCategory(String data) {
        JSONObject obj = new JSONObject(data);
        int numToDel = obj.getInt("numToDel");
        categories.remove(numToDel);
        for(String s: categories) {
            System.out.println("del: "+s);
        }
    }

}
