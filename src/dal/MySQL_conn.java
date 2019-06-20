package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_conn {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {

            if (connection != null && !connection.isClosed() && connection.isValid(1)) {
                    return connection;
            } else {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection("jdbc:mysql://sql223.main-hosting.eu:3306/u748359586_02324?"
                        + "user=u748359586_02324&password=jR2qSR2gHsCK&autoReconnect=true");
                return connection;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg, e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }
}
