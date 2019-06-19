package dal;

import dto.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements ICategoryDAO{
    @Override
    public void createCategory(String name) {
        try {
            Connection c = MySQL_conn.getInstance().getConnection();
            PreparedStatement prest = c.prepareStatement("insert into category (categoryName) values(?)");
            prest.setString(1, name);
            prest.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCategory(int ID) {
        try {
            Connection c = MySQL_conn.getInstance().getConnection();
            PreparedStatement prest = c.prepareStatement("SELECT * FROM category WHERE ID = ?");
            prest.setInt(1, ID);
            ResultSet result = prest.executeQuery();

            if (result.next()) return result.getString("categoryName");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String[]> getCategories() {
        try {
            Connection c = MySQL_conn.getInstance().getConnection();
            PreparedStatement prest = c.prepareStatement("SELECT * FROM category");
            ResultSet result = prest.executeQuery();

            List<String[]> categories = new ArrayList<>();

            while (result.next()){

                categories.add(new String[]{Integer.toString(result.getInt("categoryNumber")),result.getString("categoryName")});
            }

            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
