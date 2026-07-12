/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;

import supplychainsystem.DatabaseConnection;
import java.util.ArrayList;
import supplychainsystem.OrderItem;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author imanalnahwi
 */
public class OrderItemDB {

    public ArrayList<OrderItem> getItemsByOrderId(int orderId) {
        ArrayList<OrderItem> list = new ArrayList<>();
        String sql = "SELECT order_items.item_id, order_items.order_id, "
                   + "order_items.product_id, order_items.quantity, "
                   + "order_items.unit_price, order_items.subtotal, "
                   + "products.name "
                   + "FROM order_items "
                   + "LEFT JOIN products ON order_items.product_id = products.product_id "
                   + "WHERE order_items.order_id = " + orderId;
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OrderItem item = new OrderItem(
                    rs.getInt("item_id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price"),
                    rs.getDouble("subtotal")
                );
                item.setProductName(rs.getString("name"));
                list.add(item);
            }
        } catch (SQLException e) {
                e.printStackTrace();
                
                }
        return list;
        }
    
    public boolean addOrderItem(OrderItem item) {
        String sqlInsert = "INSERT INTO order_items (order_id, product_id, quantity, unit_price, subtotal) "
                   + "VALUES (?,?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getUnitPrice());
            ps.setDouble(5, item.getSubtotal());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteOrderItem(int itemId) {
        String sqlDelete = "DELETE FROM order_items WHERE item_id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlDelete);
            ps.setInt(1, itemId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Calculates total of all items in one order
    public double calculateOrderTotal(int orderId) {
        ArrayList<OrderItem> items = getItemsByOrderId(orderId);
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    ArrayList<OrderItem> getItemByOrderId(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
