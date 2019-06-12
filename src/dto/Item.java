package dto;

public class Item {
    private String category;
    private String productName;
    private double price;
    private int amount;
    private String dateOfPurchase;
    private String buyersName;
    private String comment;

    public Item() {}

    public Item(String category){
        this.category = category;
    }

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
        return "Cat.=" + category + " pro.Name=" + productName + " price=" + price + " amount=" + amount + " dateOfPurchase=" + dateOfPurchase + " purchaser=" + buyersName + " comment=" + comment;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}