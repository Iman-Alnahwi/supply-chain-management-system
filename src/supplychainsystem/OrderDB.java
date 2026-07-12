/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author imanalnahwi
 */
public class OrderDB {
    
    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> list= new ArrayList<>();
        String sqlString="SELECT orders.order_id, orders.supplier_id, orders.order_date,"
                + "orders.status, orders.total_amount, suppliers.name AS supplier_name "
                + "FROM orders "
                +"LEFT JOIN suppliers ON orders.supplier_id= suppliers.supplier_id";
        try {
           Connection conn=DatabaseConnection.getConnection();
           Statement st= conn.createStatement();
           ResultSet rs= st.executeQuery(sqlString);
           while(rs.next()){
               Order order = new Order(
                       rs.getInt("order_id"),
                       rs.getInt("supplier_id"),
                       rs.getDate("order_date"),
                       rs.getString("status"),
                       rs.getDouble("total_amount")
                       
               );
               order.setSupplierName(rs.getString("supplier_name"));
               list.add(order);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int addOrder(Order order){
        String sqlInsert="INSERT INTO orders (supplier_id, order_date, status, total_amount) "
                + "VALUES(?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getSupplierId());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(3, order.getStatus());
            ps.setDouble(4, order.getTotalAmount());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()) return keys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean updateOrderTotal(int orderId, double total){
        String sqlUpdate=" UPDATE orders SET total_amount =? WHERE order_id =?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            ps.setDouble(1, total);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //business rule: when delivered update stock
    public boolean updateOrderStatus(int orderId, String newStatus) {
    String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
    try {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newStatus);
        ps.setInt(2, orderId);
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean deleteOrder(int orderId){
        String deleteItems="DELETE FROM order_items WHERE order_id=?";
        String deleteOrder="DELETE FROM orders WHERE order_id =?";
        try {
           Connection conn = DatabaseConnection.getConnection();
           conn.setAutoCommit(false);
           // delete the items first (child rows), then the order (parent row)
           PreparedStatement ps1 = conn.prepareStatement(deleteItems);
           ps1.setInt(1, orderId);
           ps1.executeUpdate();
           PreparedStatement ps2 = conn.prepareStatement(deleteOrder);
           ps2.setInt(1, orderId);
           ps2.executeUpdate();
           conn.commit();
           return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Order> searchOrders(String supplierName, String status,
            String dateFrom, String dateTo){
        ArrayList<Order> list= new ArrayList<>();
        String sqlString="SELECT o.*, s.name AS supplier_name FROM orders o "
                + "LEFT JOIN suppliers s ON o.supplier_id = s.supplier_id WHERE 1=1";

        if(supplierName != null && !supplierName.isEmpty())
            sqlString+=" AND s.name LIKE '%" + supplierName +"%'";
        if(status != null && !status.isEmpty())
            sqlString +=" AND o.status = '" + status + "'";
       if (dateFrom != null && !dateFrom.isEmpty())
            sqlString += " AND o.order_date >= '" + dateFrom + "'";
        if (dateTo != null && !dateTo.isEmpty())
            sqlString += " AND o.order_date <= '" + dateTo + "'";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlString);
            while (rs.next()) {
                Order o = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("supplier_id"),
                    rs.getDate("order_date"),
                    rs.getString("status"),
                    rs.getDouble("total_amount")
                );
                o.setSupplierName(rs.getString("supplier_name"));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
    
    // Math operation — total value of all orders
    public double getTotalOrdersValue() {
        String sql = "SELECT SUM(total_amount) FROM orders";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
