/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;

/**
 *
 * @author imanalnahwi
 */
public class Staff extends Person{
    private String username;
    private String password;
    private String department;
    
    public static final String[] DEPARTMENTS ={ "Procurement","Warehouse","Logistics"
    
    };

    public Staff(String username, String password, String department, int id, String name, String phone) {
        super(id, name, phone);
        this.username = username;
        this.password = password;
        setDepartment(department);
    }
    
    @Override
    public String getDisplayDetails() {
        return getBasicInfo() + " | Username: " + username + " | Department: " + department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        boolean valid= false;
        for (String d : DEPARTMENTS) {
            if(d.equals(department)){
                valid = true;
                break;
            }
            
        }
        if(! valid)
            throw new IllegalArgumentException("Invalid department: "+ department);
        this.department = department;
    }
    
    public boolean canAccessSuppliers() {
        return department.equals("Procurement");
    }

    public boolean canAccessProducts() {
        return department.equals("Procurement") || department.equals("Warehouse");
    }

    public boolean canAccessOrders() {
        return department.equals("Logistics") || department.equals("Procurement");
    }
    
    
    @Override
    public String toString() { return username + " (" + department + ")"; }
}
