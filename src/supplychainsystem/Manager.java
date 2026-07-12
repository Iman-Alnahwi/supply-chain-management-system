/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supplychainsystem;

/**
 *
 * @author imanalnahwi
 */
public class Manager extends Person {
    
   private String username;
   private String password;

    public Manager(String username, String password, int id, String name, String phone) {
        super(id, name, phone);
        this.username = username;
        this.password = password;
    }
   
   @Override
   public String getDisplayDetails(){
      return getBasicInfo() + " | Username: " + username + " | Role: Manager"; 
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
   
    @Override
    public String toString() { return username + " (Manager)"; }

}
