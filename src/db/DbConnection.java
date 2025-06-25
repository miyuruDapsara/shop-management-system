package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection  dbconnection;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/itp_pos","root","1234");
    }

    public  static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbconnection == null) {
            dbconnection=new DbConnection();
        }
        return dbconnection;
    }
    public Connection getConnection() throws  ClassNotFoundException, SQLException {
        return connection;
    }
}
