package dto;

import java.util.ArrayList;
import java.util.List;

public class SearchItem {
    private String categorySearch;
    private String buyersNameSearch;
    private String dateOfPurchaseStart;
    private String dateOfPurchaseEnd;
    private boolean category;
    private boolean purchaser;
    private boolean productName;
    private boolean price;
    private boolean amount;
    private boolean comment;
    private boolean dateOfPurchase;


    public SearchItem() {
    }

    public SearchItem(String categorySearch,
                      String buyersNameSearch,
                      String dateOfPurchaseStart,
                      String dateOfPurchaseEnd,
                      boolean category,
                      boolean purchaser,
                      boolean productName,
                      boolean price,
                      boolean amount,
                      boolean comment,
                      boolean dateOfPurchase
        ) {
        this.categorySearch = categorySearch;
        this.buyersNameSearch = buyersNameSearch;
        this.dateOfPurchaseStart = dateOfPurchaseStart;
        this.dateOfPurchaseEnd = dateOfPurchaseEnd;
        this.category = category;
        this.purchaser = purchaser;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.comment = comment;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String toString() {
        return "categorySearch=" + categorySearch +
                " buyersNameSearch=" + buyersNameSearch +
                " dateStart=" + dateOfPurchaseStart +
                " dateEnd=" + dateOfPurchaseEnd +
                " category " + category +
                " purchaser " + purchaser +
                " productname " + productName +
                " price " + price +
                " amount " + amount +
                " comment " + comment +
                " dateOfPurchaser " + dateOfPurchase;
    }

    Item itemObj = new Item();

    List<SearchItem> searchItemList = new ArrayList<>();

    public void addToList(SearchItem searchItem) {
        searchItemList.add(searchItem);
    }

    public List<SearchItem> getItemsByCategory(String category) {
        List<SearchItem> itemsToReturn = new ArrayList<>();
        for (SearchItem searchItem : searchItemList) {
            if (searchItem.getCategorySearch().equals(category)) {
                itemsToReturn.add(searchItem);
            }
        }
        return itemsToReturn;
    }

    public String getCategorySearch() {
        return categorySearch;
    }

    public void setCategorySearch(String categorySearch) {
        this.categorySearch = categorySearch;
    }

    public String getBuyersNameSearch() {
        return buyersNameSearch;
    }

    public void setBuyersNameSearch(String buyersNameSearch) {
        this.buyersNameSearch = buyersNameSearch;
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

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
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
