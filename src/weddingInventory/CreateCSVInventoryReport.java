package src.weddingInventory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateCSVInventoryReport {

    /**
     * Generates a report of the overall inventory in a CSV file.
     */
    static void generateCSVInventoryReport(List<Inventory> inventoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory_Report.csv"))) {
            // Write headers
            writer.write("TYPE,NAME,QUANTITY\n");

            // Write inventory items
            writeInventoryByTypeToCSV("Greenery", inventoryList.stream().filter(Inventory::isTypeGreeneries).toList(),
                    writer);
            writeInventoryByTypeToCSV("Vase", inventoryList.stream().filter(Inventory::isTypeVases).toList(), writer);
            writeInventoryByTypeToCSV("Table Runner",
                    inventoryList.stream().filter(Inventory::isTypeTableRunners).toList(), writer);

            System.out.println("Inventory report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating inventory report: " + e.getMessage());
        }
    }

    /**
     * Writes inventory items of a specific type to the CSV file.
     * 
     * @param type   The type of inventory items.
     * @param items  The list of inventory items.
     * @param writer The BufferedWriter object for writing to the file.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeInventoryByTypeToCSV(String type, List<Inventory> items, BufferedWriter writer)
            throws IOException {
        for (Inventory item : items) {
            writer.write(String.format("%s,%s,%d\n", type, item.getNameOfItem(), item.getQuantity()));
        }
    }

}
