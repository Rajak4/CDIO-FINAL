package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.sun.xml.internal.bind.v2.TODO;
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
    private static List<String> categorys = new ArrayList<>();

    static {
        categorys.add("10 - IT");
        categorys.add("11 - Kørsel");
        categorys.add("12 - Cafeteriet");
        categorys.add("13 - Frønsegoder");
        categorys.add("14 - Kontor");
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
        return new JSONArray(categorys);
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
        String cat = catObject.getString("testAddCategory");
        this.categorys.add(cat);
        System.out.println("added: " + cat);
        for(String s: categorys) {
            System.out.println(s);
        }
    }

}
