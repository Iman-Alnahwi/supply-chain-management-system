/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author imanalnahwi
 */
public class DatabaseConnection {
    private static final String url ="jdbc:mysql://localhost:3306/supply_chain";
    private static final String user="root";
    private static final String password="Iman87@Meko";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
   
}
