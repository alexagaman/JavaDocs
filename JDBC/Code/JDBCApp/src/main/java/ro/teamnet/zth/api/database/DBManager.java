package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alexandra.Gaman on 7/13/2017.
 */
public class DBManager {
    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";
    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver(){
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(Connection connection){
        registerDriver();
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING,DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static boolean checkConnection(Connection connection){
        try (Statement statement = connection.createStatement( )){
            return statement.execute("SELECT 1 FROM DUAL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
