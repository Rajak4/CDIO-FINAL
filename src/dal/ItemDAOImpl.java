package dal;

import dto.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements IItemDAO {
    @Override
    public void createItem(Item item)  {
        System.out.println(item);
        try {
            Connection c = MySQL_conn.getConnection();
            Thread.sleep(100);
            PreparedStatement prest = c.prepareStatement("SELECT ID FROM users WHERE userName = '" + item.getBuyersName() + "'");
            ResultSet result = prest.executeQuery();
            int userID = result.next() ? result.getInt("ID") : 0;

            prest = c.prepareStatement("SELECT categoryNumber FROM category WHERE categoryName = ?");
            prest.setString(1, item.getCategory());
            result = prest.executeQuery();
            int categoryID = result.next() ? result.getInt("categoryNumber") : 0;

            prest = c.prepareStatement("insert into item (productName, amount, price, dateOfPurchase, purchaser, comment, categoryNumber) values(?,?,?,?,?,?,?)");
            prest.setString(1, item.getProductName());
            prest.setInt(2, item.getAmount());
            prest.setDouble(3, item.getPrice());
            prest.setString(4, item.getDateOfPurchase());
            prest.setInt(5, userID);
            prest.setString(6, item.getComment());
            prest.setInt(7,categoryID);

            System.out.println(prest);
            prest.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int ID) throws SQLException {
        Connection c = MySQL_conn.getConnection();
        PreparedStatement prest = c.prepareStatement("delete from item where ID = ?");
        prest.setInt(1, ID);
        prest.executeUpdate();
    }

    @Override
    public void updateItem(int ID, Item item) {

    }

    @Override
    public Item getItem(int ID) throws SQLException {
            Connection c = MySQL_conn.getConnection();
            PreparedStatement prest = c.prepareStatement("SELECT item.productName, item.price, item.amount, item.dateOfPurchase, users.userName as nameOfPurchaser, item.comment, category.categoryName as categoryName FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID WHERE ID = ?;");
            prest.setInt(1, ID);
            ResultSet result = prest.executeQuery();

            Item item = new Item();
            if (result.next()){
                item.setProductName(result.getString("productName"));
                item.setPrice(result.getInt("price"));
                item.setAmount(result.getInt("amount"));
                item.setDateOfPurchase(result.getString("dateOfPurchase"));
                item.setBuyersName(result.getString("nameOfPurchaser"));
                item.setComment(result.getString("comment"));
                item.setCategory(result.getString("categoryName"));
            }
            return item;
    }

    @Override
    public List<Item> getItems(String category, String purchaser, String productName, String date1, String date2) throws SQLException {
        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();

        PreparedStatement prest = c.prepareStatement("SELECT item.productName, item.price, item.amount, item.dateOfPurchase, users.userName as nameOfPurchaser, item.comment, category.categoryName as categoryName FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID");
        ResultSet result = prest.executeQuery();
        while(result.next()) {
            Item item = new Item();
            item.setCategory("Not rdy");
            item.setProductName(result.getString("productName"));
            item.setPrice(result.getInt("price"));
            item.setAmount(result.getInt("amount"));
            item.setDateOfPurchase(result.getString("dateOfPurchase"));
            item.setBuyersName(result.getString("nameOfPurchaser"));
            item.setComment(result.getString("comment"));
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getItems() throws SQLException {
        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();
        Statement st = c.createStatement();
        ResultSet result = st.executeQuery("select * from item");
        while(result.next()) {
            Item item = new Item();
            item.setCategory("Not rdy");
            item.setProductName(result.getString("productName"));
            item.setPrice(result.getInt("price"));
            item.setAmount(result.getInt("amount"));
            item.setDateOfPurchase(result.getDate("dateOfPurchase").toString());
            item.setBuyersName(result.getString("nameOfPurchaser"));
            item.setComment(result.getString("comment"));

            items.add(item);
        }
        return items;
    }
}
