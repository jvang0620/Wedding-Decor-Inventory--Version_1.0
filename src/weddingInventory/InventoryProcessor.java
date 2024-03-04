//package
package src.weddingInventory;

//imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InventoryProcessor {

    private static List<Inventory> inventoryList = new ArrayList<>();

    /**
     * Processes user input to create inventory items.
     */
    public static void processUserInput() {

        // Load inventory data from CSV file
        InventoryFileManager.readInventoryFromCSV(inventoryList);

        // Scanner instance
        Scanner scanner = new Scanner(System.in);

        // Set exit flag to false to enable infinite looping of program
        boolean exitProgram = false;

        // Program will keep looping until user select '4' in the homepage
        while (!exitProgram) {
            try {
                // Displays the home page of the application.
                System.out.println("\n******** Wedding Inventory App ******** \n");
                System.out.println("1. Create Inventory");
                System.out.println("2. Edit Inventory");
                System.out.println("3. View Inventory");
                System.out.println("4. Generate Report");
                System.out.println("5. Exit App \n");
                System.out.println("**Please select a number (1 - 5)**");

                // Read user input
                int number = scanner.nextInt();

                // Consume the newline character left in the input stream
                scanner.nextLine();

                // Initialize variables to track user input
                String nameOfItem = "";
                int quantity = 0;
                boolean isGreenery = false;
                boolean isVases = false;
                boolean isTableRunner = false;

                switch (number) {

                    // Create Inventory
                    case 1:
                        try {
                            System.out.println("\n******** Create Inventory ******** \n");

                            // PROMTE user to input name of item
                            System.out.println("Enter name of item:");
                            nameOfItem = scanner.nextLine();

                            // PROMTE user to input quantity of item. Keep looping if input is non-numeric
                            // character or negative number
                            while (true) {
                                System.out.println("Enter quantity of item:");
                                if (scanner.hasNextInt()) {
                                    quantity = scanner.nextInt();
                                    if (quantity >= 0) {
                                        // Valid quantity entered, break out of the loop
                                        break;
                                    } else {
                                        System.out.println(
                                                "Invalid input. Please enter a positive number (e.g 1, 2, 3, 4).\n");
                                    }
                                } else {
                                    // Invalid input, consume the invalid token and prompt the user again
                                    System.out.println("Invalid input. Please enter a non-negative number.\n");
                                    scanner.next(); // Consume the invalid token
                                }
                            }

                            // PROMTE user to input true/false value for type of item.
                            // Keep prompting until at least one type is selected
                            while (!isGreenery && !isVases && !isTableRunner) {
                                // Prompt user for greenery option
                                isGreenery = HelperFunctions.promptForBoolean(
                                        "Is your item a Greenery? (Enter 'Y' for yes and 'N' for no)",
                                        scanner);
                                if (!isGreenery) {
                                    // If greenery is false, prompt for vases option
                                    isVases = HelperFunctions.promptForBoolean(
                                            "Is your item a Vase? (Enter 'Y' for yes and 'N' for no)",
                                            scanner);
                                    if (!isVases) {
                                        // If both greenery and vases are false, prompt for table runner option
                                        isTableRunner = HelperFunctions.promptForBoolean(
                                                "Is your item a Table Runner? (Enter 'Y' for yes and 'N' for no)",
                                                scanner);
                                        if (!isTableRunner) {
                                            System.out.println("\nYou CANNOT enter 'N' for all three.");
                                            System.out.println("You MUST choose the 'Y' option for your decor .\n");
                                        }
                                    }
                                }
                            }

                            // Create a new Inventory object using the provided parameters
                            Inventory newItem = new Inventory(nameOfItem, quantity, isGreenery, isVases, isTableRunner);

                            // Add the newly created Inventory object to the inventoryList
                            inventoryList.add(newItem);

                            // Print message and object representation
                            System.out.println(newItem.toStringDetails());

                            // Generate inventory report after adding the new item
                            CreateCSVInventoryReport.generateCSVInventoryReport(inventoryList);

                        } catch (InputMismatchException e) {
                            System.out.println("\nCaught InputMismatchException. Please enter a valid entry.");
                        } catch (Exception e) {
                            System.out.println(
                                    "\nCaught Exception. There may be a possible error that occurred during user input.");
                        }
                        break;

                    // TODO: Edit Inventory
                    case 2:
                        break;

                    // View Inventory
                    case 3:
                        System.out.println("\n******** View Inventory ********\n");
                        if (inventoryList.isEmpty()) {
                            System.out.println("Inventory is empty.");
                        } else {
                            // Group inventory items by type
                            List<Inventory> greeneryItems = new ArrayList<>();
                            List<Inventory> vaseItems = new ArrayList<>();
                            List<Inventory> tableRunnerItems = new ArrayList<>();

                            for (Inventory item : inventoryList) {
                                if (item.isTypeGreeneries()) {
                                    greeneryItems.add(item);
                                } else if (item.isTypeVases()) {
                                    vaseItems.add(item);
                                } else if (item.isTypeTableRunners()) {
                                    tableRunnerItems.add(item);
                                }
                            }

                            // Print greenery items
                            HelperFunctions.printInventoryByType("Greeneries", greeneryItems);

                            // Print vase items
                            HelperFunctions.printInventoryByType("Vases", vaseItems);

                            // Print table runner items
                            HelperFunctions.printInventoryByType("Table Runners", tableRunnerItems);
                        }
                        break;

                    // Generate a text file report
                    case 4:
                        CreateTextReport.generateTextReport(inventoryList);
                        break;

                    // Exit Program
                    case 5:
                        System.out.println("\nYou selected to exit the program");
                        System.out.println("Have a great day!\n");

                        // Before exiting the program, iterate through the inventory list and write each
                        // inventory item to the CSV file.
                        InventoryFileManager.writeInventoryToCSV(inventoryList);

                        // Set exit flag to true to terminate the loop
                        exitProgram = true;
                        break;

                    default:
                        System.out.println("\nInvalid input. Please enter a number between 1 and 5.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nCaught InputMismatchException. Please enter a valid entry.\n");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }

        }

        scanner.close(); // Closing scanner to release resources

    }

}
