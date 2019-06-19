package dal;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executor;

@Singleton
public class MySQL_conn {

    private static MySQL_conn instance = null;
    private static Connection connection = null;

    public static MySQL_conn getInstance() {
        if (instance == null) {
            instance = new MySQL_conn();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {

            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Executor executor = new Executor() {
                    @Override
                    public void execute(Runnable command) {

                    }
                };

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
