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
public class Product implements Serializable {
    
    public static final String[] ALLOWED_CATEGORIES ={
        "Electronics", "Food" , "Clothing", "Machinery", 
        "Raw Materials", "Other"
    };
    
    private int productId;
    private String name;
    private String category;
    private double unitPrice;
    private int stockQuantity;
    private int supplierId;
    private String supplierName;

    public Product(int productId, String name, String category, double unitPrice, int stockQuantity, int supplierId) {
        this.productId = productId;
        this.name = name;
        setCategory(category);
        setUnitPrice(unitPrice);
        setStockQuantity(stockQuantity);
        this.supplierId = supplierId;
       
    }
    
    public boolean isLowStack(){
        return stockQuantity <= 5;
    }
    
    public double getStockValue(){
        return unitPrice * stockQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty.");
            this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        boolean valid = false;
        for (String c : ALLOWED_CATEGORIES) {
            if(c.equals(category)){
                valid = true;
                break;
            }
        }
        if(!valid)
            throw new IllegalArgumentException("Invalid category.");
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        if(unitPrice < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if(stockQuantity < 0)
            throw new IllegalArgumentException("Stock cannot be negative.");
        this.stockQuantity = stockQuantity;
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
    
    @Override
    public String toString() { return name; }
    
}
