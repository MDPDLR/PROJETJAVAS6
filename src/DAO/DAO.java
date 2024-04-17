package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {

    private Connection connection;
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URLDataBase, LoginDataBase, PwdDataBase);
    }
}
