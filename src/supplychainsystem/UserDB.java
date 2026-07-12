/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;
import supplychainsystem.DatabaseConnection;
import java.sql.*;
import supplychainsystem.Manager;


import supplychainsystem.Person;
import supplychainsystem.Staff;

/**
 *
 * @author imanalnahwi
 */
public class UserDB {
    
    public Person login(String username, String password, String role){
        String sqlString="SELECT * FROM users WHERE username = ? "
                + "AND password = ? AND role = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs =ps.executeQuery();
            
            if(rs.next()){
                int id =rs.getInt("user_id");
                String name =rs.getString("name") != null ? 
                        rs.getString("name") : username;
                String phone =rs.getString("phone") != null ?
                        rs.getString("phone") : "";
                String dept = rs.getString("department");
                
                if(role.equals("Manager")){
                    return new Manager(username, password, id, name, phone);
                }else{
                    return new Staff(username, password, dept, id, name, phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
           return null;     
              
    }
}
