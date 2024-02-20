//package
package src.weddingInventory;

//Imports
import java.io.*;
import java.util.List;

public class InventoryFileManager {

    // Variable to hold the file path of the CSV file
    private static final String CSV_FILE_PATH = "inventory_report.csv";

    /**
     * Writes the inventory data to a CSV file.
     * 
     * @param inventoryList The list of Inventory objects to write to the CSV file.
     */
    public static void writeInventoryToCSV(List<Inventory> inventoryList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            // Write the header line to the CSV file
            writer.println("Type,Name,Quantity");

            // Iterate over each Inventory object and write its data to the CSV file
            for (Inventory item : inventoryList) {
                writer.println(item.toCSVString());
            }
        } catch (IOException e) {
            // Print an error message if an IOException occurs during file writing
            System.err.println("Error writing inventory to CSV file: " + e.getMessage());
        }
    }

    /**
     * Reads the inventory data from a CSV file on program startup.
     * 
     * @param inventoryList The list to populate with Inventory objects read from
     *                      the CSV file.
     */
    public static void readInventoryFromCSV(List<Inventory> inventoryList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;

            // Skip the header line
            reader.readLine();

            // Read each line of the CSV file
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using comma as delimiter
                String[] parts = line.split(",");

                // Extract data from parts
                String nameOfItem = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                boolean typeGreeneries = parts[0].equals("Greenery");
                boolean typeVases = parts[0].equals("Vase");
                boolean typeTableRunners = parts[0].equals("Table Runner");

                // Create an Inventory object and add it to the inventoryList
                inventoryList.add(new Inventory(nameOfItem, quantity, typeGreeneries, typeVases, typeTableRunners));
            }
        } catch (IOException e) {
            // Print an error message if an IOException occurs during file reading
            System.err.println("Error reading inventory from CSV file: " + e.getMessage());
        }
    }

}
