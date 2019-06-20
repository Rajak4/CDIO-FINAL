package dto;

public class SearchItem {
    private String category;
    private String buyersName;
    private String dateOfPurchaseStart;
    private String dateOfPurchaseEnd;
    private boolean categoryc;
    private boolean purchaser;
    private boolean productName;
    private boolean price;
    private boolean amount;
    private boolean comment;
    private boolean dateOfPurchase;

    public SearchItem(){}

    public SearchItem(String category, String buyersName, String dateOfPurchaseStart, String dateOfPurchaseEnd, boolean categoryc, boolean purchaser, boolean productName, boolean price, boolean amount, boolean comment, boolean dateOfPurchase)
    {
        this.category = category;
        this.buyersName = buyersName;
        this.dateOfPurchaseStart = dateOfPurchaseStart;
        this.dateOfPurchaseEnd = dateOfPurchaseEnd;
        this.categoryc = categoryc;
        this.purchaser = purchaser;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.comment = comment;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String toString() {
        return "category=" + category +
                " buyersName=" + buyersName +
                " dateStart=" + dateOfPurchaseStart +
                " dateEnd=" + dateOfPurchaseEnd +
                " category " + categoryc +
                " purchaser " + purchaser +
                " productname " + productName +
                " price " + price +
                " amount " + amount +
                " comment " + comment +
                " dateOfPurchaser " + dateOfPurchase;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categorySearch) {
        this.category = categorySearch;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getDateOfPurchaseStart() {
        return dateOfPurchaseStart;
    }

    public void setDateOfPurchaseStart(String dateOfPurchaseStart) {
        this.dateOfPurchaseStart = dateOfPurchaseStart;
    }

    public String getDateOfPurchaseEnd() {
        return dateOfPurchaseEnd;
    }

    public void setDateOfPurchaseEnd(String dateOfPurchaseEnd) {
        this.dateOfPurchaseEnd = dateOfPurchaseEnd;
    }

    public boolean isCategoryc() {
        return categoryc;
    }

    public void setCategoryc(boolean category) {
        this.categoryc = category;
    }

    public boolean isPurchaser() {
        return purchaser;
    }

    public void setPurchaser(boolean purchaser) {
        this.purchaser = purchaser;
    }

    public boolean isProductName() {
        return productName;
    }

    public void setProductName(boolean productName) {
        this.productName = productName;
    }

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }

    public boolean isAmount() {
        return amount;
    }

    public void setAmount(boolean amount) {
        this.amount = amount;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(boolean dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
