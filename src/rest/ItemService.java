package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dal.*;
import dto.Item;
import dto.SearchItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemService {

    public Item itemList = new Item();
    private IItemDAO itemDAO = new ItemDAOImpl();
    private static ICategoryDAO categoryDAO = new CategoryDAOImpl();
    private static IUserDAO userDAO = new UserDAOImpl();
    private static List<String[]> categories = new ArrayList<>();
    private static List<String[]> buyerNames = new ArrayList<>();
    public Item oneItem = new Item();
    public SearchItem oneSearchItem = new SearchItem();


    //This runs every time the server starts
    static {
/*        categories.add("IT");
        categories.add("Kørsel");
        categories.add("Cafeteriet");
        categories.add("Frynsegoder");
        categories.add("Kontor");

        buyerNames.add("Andreas Nøhr");
        buyerNames.add("Rasmus Jakobsen");
        buyerNames.add("Martin Rylund");
        buyerNames.add("Athusan Kugathasan");
        buyerNames.add("Patrick Hansen");*/

        categories = categoryDAO.getCategories();

        buyerNames = userDAO.getUsers();
    }

    public void removeNameFromArray(int posToDel) {
        buyerNames.remove(posToDel);
    }
    public void removeCategoryFromArray(int posToDel) {
        categories.remove(posToDel);
    }

    @Path("receiveForm")
    @POST
    public void getItem(String body) {
        JSONObject jsonObject = new JSONObject(body);
        //removes every before, and also the string itself " - ".
        //fx "10 - IT" becomes "IT". "10 - " is removed.
        String category = (jsonObject.getString("category")).replaceAll(".* - ", "");
        System.out.println("test ja " + category);
        String buyersName = (jsonObject.getString("buyersName")).replaceAll(".* - ", "");

        //creating an item and adding it to the array
        Item item = new Item(
                category,
                jsonObject.getString("productName"),
                jsonObject.getDouble("price"),
                jsonObject.getInt("amount"),
                jsonObject.getString("dateOfPurchase"),
                buyersName,
                jsonObject.getString("comment")
        );
        //itemList.addItem(item);

        itemDAO.createItem(item);
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
        categoryDAO.createCategory(catString);
        categories = categoryDAO.getCategories();
    }

    private static List<Item> itemsToReturn;
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getShowData")
    @POST
    public List<Item> getShowData(SearchItem data) throws SQLException {
        System.out.println(data);

        String categorySearch = data.getCategorySearch().replaceAll(".*,", "");
        String name = data.getBuyersNameSearch().replaceAll(".*,", "");
        String startDate = data.getDateOfPurchaseStart();
        String endDate = data.getDateOfPurchaseEnd();
        boolean category = data.isCategory();
        boolean purchaser= data.isPurchaser();
        boolean productName = data.isProductName();
        boolean price = data.isPrice();
        boolean amount = data.isAmount();
        boolean comment = data.isComment();
        boolean dateOfPurchase = data.isDateOfPurchase();

//        boolean start = false;
//        boolean end = false;
//
//        List<String> searchList = new ArrayList<>();
//        if(!category.equals("Ingen kategori")) searchList.add(category);
//        if(!name.equals("Intet navn")) searchList.add(name);
//        if(!startDate.equals("")) {
//            searchList.add(startDate);
//            start = true;
//        }
//        if(!endDate.equals("")) {
//            searchList.add(endDate);
//            end = true;
//        }
//        if(start && end) {
//
//        }

        itemsToReturn = itemDAO.getItems(data);

        return itemsToReturn;
    }

    @Path("addBuyersName")
    @POST
    public void addBuyersName(String buyersName) {
        //creating JSON object of the string we get
        JSONObject nameObj = new JSONObject(buyersName);
        String name = nameObj.getString("buyersName");
        userDAO.createUser(name);
        buyerNames = userDAO.getUsers();
    }

    @Path("deleteCategory")
    @POST
    public void deleteCategory(String data) {
        JSONObject obj = new JSONObject(data);
        int numToDel = obj.getInt("numToDel");
        removeCategoryFromArray(numToDel);
    }

    @Path("deleteName")
    @POST
    public void deleteName(String data) {
        JSONObject obj = new JSONObject(data);
        int numToDel = obj.getInt("numToDel");
        removeNameFromArray(numToDel);
    }

}
