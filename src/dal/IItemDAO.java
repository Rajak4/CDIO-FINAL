package dal;

import dto.Item;
import dto.SearchItem;

import java.sql.SQLException;
import java.util.List;

public interface IItemDAO {
    void createItem(Item item);

    void deleteItem(int ID) throws SQLException;

    void updateItem(int ID, Item item);

    Item getItem(int ID) throws SQLException;

    List<Item> getItems(boolean category, boolean purchaser, boolean productName, boolean price, boolean amount, boolean date, boolean comment, String purchaserName, String categoryName, String date1, String date2) throws SQLException;

    List<Item> getItems() throws SQLException;

    List<Item> searchForCategoryDB(SearchItem searchItem) throws SQLException;

}
