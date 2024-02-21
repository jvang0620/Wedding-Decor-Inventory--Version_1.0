//package
package src.weddingInventory;

//imports
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class HelperFunctions {

    /**
     * This method takes the type of inventory items and a list of inventory items
     * as parameters.
     * It prints the type first and then loops through the items to print their
     * names and quantities.
     * If the list of items is empty, it does not print anything for that type.
     * 
     * @param type
     * @param items
     */
    public static void printInventoryByType(String type, List<Inventory> items) {
        if (!items.isEmpty()) {
            System.out.println("Type: " + type);
            System.out.printf("%-5s %-65s %-10s%n", "#", "Item Name", "Quantity");

            // Sort the items alphabetically by name
            Collections.sort(items, Comparator.comparing(Inventory::getNameOfItem));

            // Iterate over the sorted items and print them with numbered list
            for (int i = 0; i < items.size(); i++) {
                Inventory item = items.get(i);
                System.out.printf("%-5d %-65s %-10d%n", i + 1, item.getNameOfItem(), item.getQuantity());
            }

            System.out.println(); // Add a newline after printing items of this type
        }
    }

    /**
     * Prompts the user for a boolean value (Y/N) and keeps prompting until valid
     * input is provided.
     * 
     * @param prompt  The prompt to display to the user.
     * @param scanner The scanner object to read user input.
     * @return The boolean value entered by the user.
     */
    public static boolean promptForBoolean(String prompt, Scanner scanner) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.next().toUpperCase(); // Convert input to uppercase for case-insensitive comparison
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("\nInvalid input. Please enter 'Y' for yes or 'N' for no. \n");
            }
        }
    }
}
