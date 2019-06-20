package dal;

import dto.CreateCSV;
import dto.Item;
import dto.SearchItem;

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
        PreparedStatement prest = c.prepareStatement(hej.toString());


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
        ResultSet result = prest.executeQuery();

        System.out.println(prest.toString());

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
                item.setPrice(result.getDouble("price"));
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


        return items;
    }

    @Override
    public List<Item> getItems(SearchItem item) throws SQLException {
        return getItems(item.isCategoryc(), item.isPurchaser(), item.isProductName(), item.isPrice(), item.isAmount(), item.isDateOfPurchase(), item.isComment(), item.getBuyersName(), item.getCategory().replaceAll(".* - ", ""), item.getDateOfPurchaseStart(), item.getDateOfPurchaseEnd());
    }



}
