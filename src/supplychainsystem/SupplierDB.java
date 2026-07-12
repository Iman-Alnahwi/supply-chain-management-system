/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;
import supplychainsystem.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import supplychainsystem.Supplier;

/**
 *
 * @author imanalnahwi
 */
public class SupplierDB {
    public ArrayList<Supplier> getAllSuppliers(){
            ArrayList<Supplier> list = new ArrayList<>();
    String sql="SELECT * FROM suppliers";
    
    try { 
    Connection conn= DatabaseConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs= st.executeQuery(sql);
    while(rs.next()){
    Supplier s = new Supplier(
            rs.getInt("supplier_id"),
            rs.getString("name"),
            rs.getString("phone"),
            rs.getString("email"),
            rs.getString("address")
    );
    list.add(s);
   }
  } catch(SQLException e){
    e.printStackTrace();
   }
     return list;
}
  
    public boolean addSupplier(Supplier s){
        String sqlInsert="INSERT INTO suppliers(name, phone , email, address) "
                + "VALUES(?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sqlInsert);
            ps.setString(1, s.getName());
            ps.setString(2, s.getPhone());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getAddress());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateSupplier(Supplier s){
        String sqlUpdate="UPDATE suppliers SET name=?, phone=? , email=? , address=?"
                + "WHERE supplier_id=?";
        
        try {
            Connection conn =DatabaseConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sqlUpdate);
            ps.setString(1, s.getName());
            ps.setString(2, s.getPhone());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getAddress());
            ps.setInt(5, s.getId());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteSupplier(int supplierId){
        //Business rule: cannot delete supplier that has products
        String checksql="SELECT COUNT(*) FROM products WHERE supplier_id =?";
        try {
            Connection conn= DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(checksql);
            ps.setInt(1, supplierId);
            ResultSet rs= ps.executeQuery();
            if(rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        String sqlDelete="DELETE FROM suppliers WHERE supplier_id=?";
        try {
            Connection conn =DatabaseConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sqlDelete);
            ps.setInt(1, supplierId);
            ps.executeUpdate();
            return true;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
