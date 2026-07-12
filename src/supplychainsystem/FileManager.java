package supplychainsystem;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // Write orders to a text file
    public static void writeTextReport(ArrayList<Order> orders, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("=== Supply Chain Order Report ===");
            bw.newLine();
            bw.write("Generated: " + new java.util.Date());
            bw.newLine();
            bw.write("----------------------------------");
            bw.newLine();

            for (Order order : orders) {
                bw.write("Order #" + order.getOrderId()
                        + " | Supplier: " + order.getSupplierName()
                        + " | Status: " + order.getStatus()
                        + " | Total: $" + order.getTotalAmount());
                bw.newLine();
            }

            bw.write("----------------------------------");
            bw.newLine();

            double grandTotal = 0;
            for (Order order : orders) {
                grandTotal += order.getTotalAmount();
            }
            bw.write("Grand Total: $" + grandTotal);
            bw.newLine();

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read text file and return content
    public static String readTextFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
        return content.toString();
    }

    // Write orders to binary file
    public static void writeBinaryFile(ArrayList<Order> orders, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read orders from binary file
    public static ArrayList<Order> readBinaryFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Order> orders = (ArrayList<Order>) ois.readObject();
            ois.close();
            fis.close();
            return orders;
        } catch (IOException | ClassNotFoundException e) {
            return null;   // not a valid binary order file (e.g. user picked a text file)
        }
    }
}