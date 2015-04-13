/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private static final String URL = "jdbc:mysql://localhost:1500/";
    private static final String DATABASE_NAME = "bookstore";
    
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        return DriverManager.getConnection(URL, "", "");
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    
}
