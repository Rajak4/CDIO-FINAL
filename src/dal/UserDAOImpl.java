package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO{

    @Override
    public String getUser(int ID) {
        try {
            Connection c = MySQL_conn.getConnection();
            PreparedStatement prest = c.prepareStatement("SELECT * FROM users WHERE ID = ?");
            prest.setInt(1, ID);
            ResultSet result = prest.executeQuery();

            if (result.next()) return result.getString("userName");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String[]> getUsers() {
        try {
            Connection c = MySQL_conn.getConnection();
            PreparedStatement prest = c.prepareStatement("SELECT * FROM users");
            ResultSet result = prest.executeQuery();

            List<String[]> users = new ArrayList<>();
            while (result.next()){
                users.add(new String[]{result.getString("ID"), result.getString("userName")});
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(String name) {
        try {
            Connection c = MySQL_conn.getConnection();
            PreparedStatement prest = c.prepareStatement("insert into users (userName) values(?)");
            prest.setString(1, name);
            prest.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
