/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;

import java.io.Serializable;

/**
 *
 * @author imanalnahwi
 */
public class OrderItem implements Serializable {
    
    private int itemId; //1,2,3 unique id for this line
    private int orderId;// Order #1
    private int productId;// Stell Bars
    private String productName;//
    private int quantity;//10
    private double  unitPrice;//20$
    private double subtotal;//200$

    public OrderItem(int itemId, int orderId, int productId, int quantity, double unitPrice, double subtotal) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        this.subtotal = subtotal;
    }
    
    public void calculateSubtotal(){
        this.subtotal = this.quantity * this.unitPrice;
        
         }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be grater than 0.");
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        if(unitPrice < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }
    
    public double getSubtotal(){
       return subtotal; 
    }
    
    @Override
    public String toString() {
        return productName + " x" + quantity + " = $" + subtotal;
    }
}
