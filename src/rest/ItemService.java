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
    private List<String> categorys = new ArrayList<>();

    public void addCategory(String category) {
        this.categorys.add(category);
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
        return new JSONArray();
    }

}
