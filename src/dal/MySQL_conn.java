package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;


public class MySQL_conn {
    private static Connection connection = null;
    public static Connection getConnection() throws SQLException {
        int attempts = 0;
        while(true) {
            try {
                if (connection != null && !connection.isClosed()) {
                    return connection;
                } else {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    connection = DriverManager.getConnection("jdbc:mysql://sql223.main-hosting.eu/u748359586_02324?"
                            + "user=u748359586_02324&password=jR2qSR2gHsCK");
                    return connection;

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            attempts++;
            if (attempts > 20) {
                System.out.println("Database forbindelse fejlede!");
                exit(1);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }
}
