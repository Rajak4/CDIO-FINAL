package dal;

import dto.Item;

import java.sql.SQLException;
import java.util.List;

public interface IItemDAO {
    void createItem(Item item);

    void deleteItem(int ID) throws SQLException;

    void updateItem(int ID, Item item);

    Item getItem(int ID) throws SQLException;

    List<Item> getItems(String category, String purchaser, String productName, String date1, String date2) throws SQLException;

    List<Item> getItems() throws SQLException;

    List<Item> searchForCategoryDB(String category) throws SQLException;

}
