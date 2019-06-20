package dal;

import dto.CreateCSV;
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
        boolean firstSelect = true;
        StringBuilder hej = new StringBuilder();
        StringBuilder test = new StringBuilder();
        hej.append("Select");

        if (category) {
            hej.append(" category.categoryName as categoryName");
            firstSelect = false;
        }
        if (purchaser) {
            hej.append(firstSelect ? "" : ",").append(" users.userName as nameOfPurchaser");
            firstSelect = false;
        }
        if (productName) {
            hej.append(firstSelect ? "" : ",").append(" item.productName");
            firstSelect = false;
        }
        if (price) {
            hej.append(firstSelect ? "" : ",").append(" item.price");
            firstSelect = false;
        }

        if (amount) {
            hej.append(firstSelect ? "" : ",").append(" item.amount");
            firstSelect = false;
        }

        if (date) {
            hej.append(firstSelect ? "" : ",").append(" item.dateOfPurchase");
            firstSelect = false;
        }

        if (comment) {
            hej.append(firstSelect ? "" : ",").append(" item.comment");
        }

        hej.append(" FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID");

        if (!purchaserName.equals("Alle navne")) {
            hej.append(" WHERE").append(" users.userName = ?");
            test.append("1");
            firstWhere = false;
        } else test.append("0");

        if (!categoryName.equals("Alle kategorier")) {
            hej.append(firstWhere ? " WHERE" : " AND").append(" categoryName = ?");
            test.append("1");
            firstWhere = false;
        } else test.append("0");

        if (!date1.equals("") || !date2.equals("")) {
            if(!date1.equals("") && !date2.equals("")) {
                test.append("11");
                hej.append(firstWhere ? " WHERE" : " AND").append(" item.dateOfPurchase BETWEEN ? AND ?");
            } else {
                if (!date1.equals("")) {
                    hej.append(firstWhere ? " WHERE" : " AND").append(" item.dateOfPurchase > ?");
                    test.append("10");
                }

                if (!date2.equals("")) {
                    hej.append(firstWhere ? " WHERE" : " AND").append(" item.dateOfPurchase < ?");
                    test.append("01");
                }
            }
        } else test.append("00");

        Connection c = MySQL_conn.getConnection();
        List<Item> items = new ArrayList<>();
        System.out.println("HEJ -1");

        PreparedStatement prest = c.prepareStatement(hej.toString());

        System.out.println("HEJ 0");


        int curPos = 1;
        System.out.println(test.toString());
        if(test.charAt(0) == '1') {
            prest.setString(curPos, purchaserName);
            curPos++;
        }
        if(test.charAt(1) == '1') {
            prest.setString(curPos, categoryName);
            curPos++;
        }
        if(test.charAt(2) == '1') {
            prest.setDate(curPos, java.sql.Date.valueOf(date1));
            curPos++;
        }
        if(test.charAt(3) == '1') {
            prest.setDate(curPos, java.sql.Date.valueOf(date2));
        }
        System.out.println(prest.toString());


        //PreparedStatement prest = c.prepareStatement("SELECT item.productName, item.price, item.amount, item.dateOfPurchase, users.userName as nameOfPurchaser, item.comment, category.categoryName as categoryName FROM item INNER JOIN category ON item.categoryNumber = category.categoryNumber INNER JOIN users ON purchaser = users.ID");
        ResultSet result = prest.executeQuery();

        System.out.println("HEJ 2");
        while (result.next()) {
            Item item = new Item();

            if (category) {
                item.setCategory(result.getString("categoryName"));
            }
            if (purchaser) {
                item.setBuyersName(result.getString("nameOfPurchaser"));
            }
            if (productName) {
                item.setProductName(result.getString("productName"));
            }
            if (price) {
                item.setPrice(result.getInt("price"));
            }

            if (amount) {
                item.setAmount(result.getInt("amount"));
            }

            if (date) {
                item.setDateOfPurchase(result.getString("dateOfPurchase"));
            }

            if (comment) {
                item.setComment(result.getString("comment"));
            }

            items.add(item);
            System.out.println(item.toString());
        }

        CreateCSV hej2 = new CreateCSV();
        hej2.create(items);

        return items;
    }

    @Override
    public List<Item> getItems(SearchItem item) throws SQLException {
        return getItems(item.isCategory(), item.isPurchaser(), item.isProductName(), item.isPrice(), item.isAmount(), item.isDateOfPurchase(), item.isComment(), item.getBuyersNameSearch(), item.getCategorySearch().replaceAll(".* - ", ""), item.getDateOfPurchaseStart(), item.getDateOfPurchaseEnd());
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
        //updateSheets("select * from item");
        updateSheets("" + sqlString + categoryName);
        return items;
    }

    public void updateSheets(String stm){
        try{
            Connection c = MySQL_conn.getConnection();
            Statement st = c.createStatement();

            st.executeQuery("USE u748359586_02324;");
            st.executeQuery("SET SQL_SAFE_UPDATES = 0;");
            st.executeQuery("UPDATE query SET string = " + stm +";" );

        }catch(SQLException e){
            e.printStackTrace();
        }

    }


}
