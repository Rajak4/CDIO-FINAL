package dal;

import dto.Item;
import dto.SearchItem;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

public interface IItemDAO {
    void createItem(Item item);

    List<Item> getItems(boolean category, boolean purchaser, boolean productName, boolean price, boolean amount, boolean date, boolean comment, String purchaserName, String categoryName, String date1, String date2) throws SQLException;

    List<Item> getItems(SearchItem item) throws SQLException;

}
