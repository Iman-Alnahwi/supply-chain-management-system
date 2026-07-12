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
public class Supplier extends Person{
    
    private String email;
    private String address;

    public Supplier(int id, String name, String phone, String email, String address) {
        super(id, name, phone);
        this.email = email;
        this.address = address;
    }
    
   @Override
    public String getDisplayDetails() {
        return getBasicInfo() + " | Email: " + email + " | Address: " + address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
    

    @Override
    public String toString() {
       return getName();
    }
   
    
    
}
