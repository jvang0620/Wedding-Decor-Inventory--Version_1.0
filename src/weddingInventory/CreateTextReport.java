package src.weddingInventory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CreateTextReport {
    /**
     * Generates a report of the overall inventory in a text file.
     */
    static void generateTextReport(List<Inventory> inventoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Generate_Report.txt"))) {
            // Write headers
            writer.write("Inventory Report\n");
            writer.write("----------------\n");

            // Write inventory items
            writeInventoryByTypeToText("Greenery", inventoryList.stream().filter(Inventory::isTypeGreeneries).toList(),
                    writer);
            writeInventoryByTypeToText("Vase", inventoryList.stream().filter(Inventory::isTypeVases).toList(), writer);
            writeInventoryByTypeToText("Table Runner",
                    inventoryList.stream().filter(Inventory::isTypeTableRunners).toList(), writer);

            System.out.println("Text report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating text report: " + e.getMessage());
        }
    }

    /**
     * Writes inventory items of a specific type to the text file.
     * 
     * @param type   The type of inventory items.
     * @param items  The list of inventory items.
     * @param writer The BufferedWriter object for writing to the file.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeInventoryByTypeToText(String type, List<Inventory> items, BufferedWriter writer)
            throws IOException {
        if (!items.isEmpty()) {
            writer.write("\nType: " + type + "\n");
            writer.write(String.format("%-5s %-65s %-10s%n", "#", "Item Name", "Quantity"));

            // Convert the immutable list to a mutable list
            List<Inventory> mutableItems = new ArrayList<>(items);

            // Sort the items alphabetically by name
            Collections.sort(mutableItems, Comparator.comparing(Inventory::getNameOfItem));

            // Write each item with a numbered list
            for (int i = 0; i < mutableItems.size(); i++) {
                Inventory item = mutableItems.get(i);
                writer.write(String.format("%-5d %-65s %-10d%n", i + 1, item.getNameOfItem(), item.getQuantity()));
            }
        }
    }
}
