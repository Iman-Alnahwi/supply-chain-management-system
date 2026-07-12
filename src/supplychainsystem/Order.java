/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author imanalnahwi
 */
public class Order  implements Serializable{
    
    private int orderId;
    private int supplierId;
    private String supplierName;
    private Date orderDate;
    private String status;
    private double totalAmount;

    public Order(int orderId, int supplierId, Date orderDate, String status, double totalAmount) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }
    
    public boolean isEmpty(){
        return totalAmount == 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        if(totalAmount < 0)
            throw new IllegalArgumentException("Total cannot be negative.");
        this.totalAmount = totalAmount;
    }
    
   @Override
    public String toString() {
        return "Order #" + orderId + " | " + supplierName + " | " + status;
    } 
}
