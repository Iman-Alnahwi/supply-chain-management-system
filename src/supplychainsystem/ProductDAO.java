/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;  // ✅ CORRECT


import java.sql.*;
import java.util.ArrayList;
import supplychainsystem.Product;

public class ProductDAO {

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, s.name AS supplier_name FROM products p "
                   + "LEFT JOIN suppliers s ON p.supplier_id = s.supplier_id";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("unit_price"),
                    rs.getInt("stock_quantity"),
                    rs.getInt("supplier_id")
                );
                p.setSupplierName(rs.getString("supplier_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addProduct(Product p) {
        String sql = "INSERT INTO products (name, category, unit_price, stock_quantity, supplier_id) VALUES (?,?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getUnitPrice());
            ps.setInt(4, p.getStockQuantity());
            ps.setInt(5, p.getSupplierId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product p) {
        String sql = "UPDATE products SET name=?, category=?, unit_price=?, stock_quantity=?, supplier_id=? WHERE product_id=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getUnitPrice());
            ps.setInt(4, p.getStockQuantity());
            ps.setInt(5, p.getSupplierId());
            ps.setInt(6, p.getProductId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // The product is used in an order (foreign key), so it cannot be deleted.
            // The screen shows a clear message, so the error is not printed here.
            return false;
        }
    }

    public boolean updateStock(int productId, int newStock) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newStock);
            ps.setInt(2, productId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Math operation — average price of all products
    public double getAverageProductPrice() {
        String sql = "SELECT AVG(unit_price) FROM products";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
