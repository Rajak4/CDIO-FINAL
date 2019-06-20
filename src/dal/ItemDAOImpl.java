package dal;

import dto.Item;
import dto.SearchItem;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements IItemDAO {
    @Override
    public void createItem(Item item) {
        System.out.println(item);
        try {

            Connection c = MySQL_conn.getConnection();
            String userNameTest = item.getBuyersName();
            PreparedStatement prest = c.prepareStatement("SELECT ID FROM users WHERE userName = '" + userNameTest + "'");
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
            prest.setInt(7, categoryID);

            System.out.println(prest);
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
        PreparedStatement prest = c.prepareStatement("SELECT item.productName, item.price, item.amount, item.dateOfPurchase, users.userName as nameOfPurchaser, item.comment, category.categoryName as categoryName FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID WHERE ID = ?;");
        prest.setInt(1, ID);
        ResultSet result = prest.executeQuery();

        Item item = new Item();
        if (result.next()) {
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
    public List<Item> getItems(boolean category, boolean purchaser, boolean productName, boolean price, boolean amount, boolean date, boolean comment, String purchaserName, String categoryName, String date1, String date2) throws SQLException {
        boolean firstWhere = true;
        StringBuilder hej = new StringBuilder();
        StringBuilder test = new StringBuilder();
        hej.append("Select");

        if (category) {
            hej.append(" category.categoryName as categoryName");
        }
        if (purchaser) {
            hej.append(" users.userName as nameOfPurchaser");
        }
        if (productName) {
            hej.append(" item.productName");
        }
        if (price) {
            hej.append(" item.price");
        }

        if (amount) {
            hej.append(" item.amount");
        }

        if (date) {
            hej.append(" item.dateOfPurchase");
        }

        if (comment) {
            hej.append(" item.comment");
        }

        hej.append(" FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID");

        if (!purchaserName.equals("")) {
            hej.append(" WHERE").append(" nameOfPurchaser = ?");
            test.append("1");
            firstWhere = false;
        } else test.append("0");

        if (!categoryName.equals("")) {
            hej.append(firstWhere ? " WHERE" : " AND").append(" categoryName = ?");
            test.append("1");
            firstWhere = false;
        } else test.append("0");

        if (!date1.equals("") || !date2.equals("")) {
            if(!date1.equals("") && !date2.equals("")) {
                test.append("11");
                hej.append(" BETWEEN ? AND ?");
            }

            if (!date1.equals("")) {
                hej.append(firstWhere ? " WHERE" : " AND").append(" item.dateOfPurchase > ?");
                test.append("10");
            }

            if (!date2.equals("")) {
                hej.append(firstWhere ? " WHERE" : " AND").append(" item.dateOfPurchase < ?");
                test.append("01");
            }
        }

        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();
        PreparedStatement prest = c.prepareStatement(hej.toString());

        int curPos = 1;
        if(test.charAt(0) == '1') {
            prest.setString(curPos, purchaserName);
            curPos++;
        }
        if(test.charAt(1) == '1') {
            prest.setString(curPos, categoryName);
            curPos++;
        }
        if(test.charAt(2) == '1') {
            prest.setString(curPos, date1);
            curPos++;
        }
        if(test.charAt(3) == '1') {
            prest.setString(curPos, date2);
        }


        //PreparedStatement prest = c.prepareStatement("SELECT item.productName, item.price, item.amount, item.dateOfPurchase, users.userName as nameOfPurchaser, item.comment, category.categoryName as categoryName FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID");
        ResultSet result = prest.executeQuery();
        while (result.next()) {
            Item item = new Item();
            item.setProductName(result.getString("productName"));
            item.setPrice(result.getInt("price"));
            item.setAmount(result.getInt("amount"));
            item.setDateOfPurchase(result.getString("dateOfPurchase"));
            item.setBuyersName(result.getString("nameOfPurchaser"));
            item.setComment(result.getString("comment"));
            item.setCategory(result.getString("categoryName"));
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
        while (result.next()) {
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

    @Override
    public List<Item> searchForCategoryDB(SearchItem searchItem) throws SQLException {
        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();
        String sqlString = "select productName, price, amount, cat.categoryName, u.userName, comment, dateOfPurchase from item i inner join category cat on i.categoryNumber = cat.categoryNumber inner join users u on i.purchaser = u.ID where";
        String and = "and";
        String categoryName = " cat.categoryName = ? ";
        String purchaser = " u.userName = ? ";
        PreparedStatement prest = c.prepareStatement(
                sqlString + categoryName);

        prest.setString(1, searchItem.getCategorySearch().replaceAll(".*,", ""));
        ResultSet result = prest.executeQuery();
        while(result.next()) {
            Item item = new Item();
            item.setProductName(result.getString("productName"));
            item.setPrice(result.getDouble("price"));
            item.setAmount(result.getInt("amount"));
            item.setCategory(result.getString("cat.categoryName"));
            item.setBuyersName(result.getString("u.userName"));
            item.setComment(result.getString("comment"));
            item.setDateOfPurchase(result.getString("dateOfPurchase"));
            items.add(item);
        }
        return items;
    }

}
