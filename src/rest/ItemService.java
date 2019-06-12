package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dto.Item;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemService {

    private List<Item> itemList = new ArrayList<>();

    @POST
    public void getItem(String body) {
        JSONObject jsonObject = new JSONObject(body);

        Item item = new Item(jsonObject.getString("category"));
        itemList.add(item);

        System.out.println("category is: " + jsonObject.getString("category"));
        /*
        for(Item item1 : itemList) {
            System.out.println(item1);
        }
        */
    }

}
