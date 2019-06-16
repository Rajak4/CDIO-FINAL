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
    private static List<String> buyerNames = new ArrayList<>();

    //This runs every time the server starts
    static {
        categories.add("IT");
        categories.add("Kørsel");
        categories.add("Cafeteriet");
        categories.add("Frynsegoder");
        categories.add("Kontor");

        buyerNames.add("Andreas Nøhr");
        buyerNames.add("Rasmus Jakobsen");
        buyerNames.add("Martin Rylund");
        buyerNames.add("Athusan Kugathasan");
        buyerNames.add("Patrick Hansen");
    }

    public void addNameToArray(String name) {
        buyerNames.add(name);
    }
    public void addCategoryToArray(String category) {
        categories.add(category);
    }
    public void removeNameFromArray(int posToDel) {
        buyerNames.remove(posToDel);
    }
    public void removeCategoryFromArray(int posToDel) {
        categories.remove(posToDel);
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

    @Path("getBuyerNames")
    @GET
    public JSONArray buyerNamesArray() {
        return new JSONArray(buyerNames);
    }

    @Path("addCategory")
    @POST
    public void addCategory(String category) {
        //creating JSON object of the string we get
        JSONObject catObject = new JSONObject(category);
        //take the string out of our JSON object
        //here: json-key=testAddCategory and
        //json-value=value of the text field
        String catString = catObject.getString("category");
        addCategoryToArray(catString);
        System.out.println("added: " + catString);
        for(String s: categories) {
            System.out.println("add: "+s);
        }
    }

    @Path("addBuyersName")
    @POST
    public void addBuyersName(String buyersName) {
        //creating JSON object of the string we get
        JSONObject nameObj = new JSONObject(buyersName);
        String name = nameObj.getString("buyersName");
        addNameToArray(name);
        System.out.println("name add: " + name);
        for(String s: buyerNames) {
            System.out.println("name: " + s);
        }
    }

    @Path("deleteCategory")
    @POST
    public void deleteCategory(String data) {
        JSONObject obj = new JSONObject(data);
        int numToDel = obj.getInt("numToDel");
        categories.remove(numToDel);
        for(String s: categories) {
            System.out.println("del: "+s);
        }
    }

    @Path("deleteName")
    @POST
    public void deleteName(String data) {
        JSONObject obj = new JSONObject(data);
        int numToDel = obj.getInt("numToDel");
        removeNameFromArray(numToDel);
    }

}
