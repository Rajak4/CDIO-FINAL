package dto;

import java.util.ArrayList;
import java.util.List;

public class SearchItem {
    private String categorySearch;
    private String buyersNameSearch;
    private String dateOfPurchaseStart;
    private String dateOfPurchaseEnd;
//    private boolean category;
//    private boolean productName;
//    private boolean price;
//    private boolean amount;
//    private boolean dateOfPurchase;


    public SearchItem() {
    }

    public SearchItem(String categorySearch,
                      String buyersNameSearch,
                      String dateOfPurchaseStart,
                      String dateOfPurchaseEnd
//                      boolean category,
//                      boolean productName,
//                      boolean price,
//                      boolean amount,
//                      boolean dateOfPurchase
        ) {
        this.categorySearch = categorySearch;
        this.buyersNameSearch = buyersNameSearch;
        this.dateOfPurchaseStart = dateOfPurchaseStart;
        this.dateOfPurchaseEnd = dateOfPurchaseEnd;
//        this.category = category;
//        this.productName = productName;
//        this.price = price;
//        this.amount = amount;
//        this.dateOfPurchase = dateOfPurchase;
    }

    public String toString() {
        return "categorySearch=" + categorySearch + " buyersNameSearch=" + buyersNameSearch + " dateStart=" + dateOfPurchaseStart + " dateEnd=" + dateOfPurchaseEnd;
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

}
