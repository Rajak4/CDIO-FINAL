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
            PreparedStatement prest = c.prepareStatement("insert into item (productName, amount, price, dateOfPurchase, nameOfPurchaser, comment) values(?,?,?,?,?,?)");
            prest.setString(1, item.getProductName());
            prest.setInt(2, item.getAmount());
            prest.setDouble(3, item.getPrice());
            prest.setString(4, item.getDateOfPurchase());
            prest.setString(5, item.getBuyersName());
            prest.setString(6, item.getComment());
            prest.executeUpdate();
        } catch (SQLException e) {
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
            PreparedStatement prest = c.prepareStatement("SELECT * FROM item WHERE ID = ?");
            prest.setInt(1, ID);
            ResultSet result = prest.executeQuery();

            Item item = new Item();
            if (result.next()){
                item.setCategory("Not rdy");
                item.setProductName(result.getString("productName"));
                item.setPrice(result.getInt("price"));
                item.setAmount(result.getInt("amount"));
                item.setDateOfPurchase(result.getString("dateOfPurchase"));
                item.setBuyersName(result.getString("nameOfPurchaser"));
                item.setComment(result.getString("comment"));
            }
            return item;
    }

    @Override
    public List<Item> getItems(String category, String purchaser, String productName, String date1, String date2) throws SQLException {
        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();

        StringBuilder q = new StringBuilder();
        q.append("select * from item WHERE");

        if (!category.equals("")) {
            q.append(" categoryNumber = ").append(category);
        }
        if (!purchaser.equals("")) {
            q.append(" nameOfPurchaser = ").append(purchaser);
        }
        if (!productName.equals("")) {
            q.append(" productName = ").append(productName);
        }
        if (!date1.equals("") && !date2.equals("")) {
            q.append(" dateOfPurchase BETWEEN '").append(date1).append("' AND '").append(date2).append("'");
        }

        Statement st = c.createStatement();
        ResultSet result = st.executeQuery(q.toString());
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
