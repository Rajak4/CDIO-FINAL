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
    private List<Item> itemList = new ArrayList<>();

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