package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    /**
     * @param string
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getDbConnection(String string) 
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql//" + dbHost +":"
                + dbPort + "/" + dbName;
        
        Class.forName("com.mysql.jdbs.Driver");

        dbConnection = DriverManager.getConnection(connectionString, 
            dbUser, dbPass);
        
        return dbConnection;
    }
}
