package dto;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String category;
    private String productName;
    private double price;
    private int amount;
    private String dateOfPurchase;
    private String buyersName;
    private String comment;

    public Item() {}

    public Item(String category, String productName, double price, int amount, String dateOfPurchase, String buyersName, String comment) {
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.dateOfPurchase = dateOfPurchase;
        this.buyersName = buyersName;
        this.comment = comment;
    }

    public String toString() {
        return "category=" + category + " productname=" + productName + " price=" + price + " amount=" + amount + " dateOfPurchase=" + dateOfPurchase + " purchaser=" + buyersName + " comment=" + comment;
    }

    //item list
    private static List<Item> itemList = new ArrayList<>();

    static {
        itemList.add(new Item("IT", "MacBook Pro", 10900, 1, "2019-02-22", "Patrick Hansen", "Købt for sjov."));
        itemList.add(new Item("IT", "Huawei Matebook", 8899, 2, "2019-04-05", "Athusan Kugathasan", "Computer til kontor."));
        itemList.add(new Item("IT", "8k projektor", 12000, 1, "2019-04-26", "Athusan Kugathasan", "Projektor til møder."));
        itemList.add(new Item("Kørsel", "Lambo", 120000.5, 1, "2019-10-02", "Rasmus Jakobsen", "Firma bil."));
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    //returns a list of items bought in same category
    public List<Item> getItemsByCategory(String category) {
        List<Item> itemsToReturn = new ArrayList<>();
        for(Item item: itemList) {
            if(item.getCategory().equals(category)) {
                itemsToReturn.add(item);
            }
        }
        return itemsToReturn;
    }

    //returns a list of items bought by the same person
    public List<Item> getItemsByBuyersName(String name) {
        List<Item> itemsToReturn = new ArrayList<>();
        for(Item item: itemList) {
            if(item.getBuyersName().equals(name)) {
                itemsToReturn.add(item);
            }
        }
        return itemsToReturn;
    }

    //returns a list of items bought in a give day
    public List<Item> getItemDataRange(String date) {
        List<Item> itemsToReturn = new ArrayList<>();
        for(Item item: itemList) {
            if(item.getDateOfPurchase().equals(date)) {
                itemsToReturn.add(item);
            }
        }
        return itemsToReturn;
    }

    // TODO: 17-06-2019 list mellem to datoer.

    //returns the total price for a given list
    public double getPriceByList(List<Item> items) {
        double price = 0;
        for(Item item: items) {
            price += item.getPrice();
        }
        return price;
    }


    //getters and setters
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }
    public String getBuyersName() {
        return buyersName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
}