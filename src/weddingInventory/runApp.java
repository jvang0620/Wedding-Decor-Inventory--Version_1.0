//package
package src.weddingInventory;

//imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class runApp {

    private static List<Inventory> inventoryList = new ArrayList<>();

    /**
     * Processes user input to create inventory items.
     */
    public static void processUserInput() {

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
                System.out.println("2. View Inventory");
                System.out.println("3. Generate Report");
                System.out.println("4. Exit App \n");
                System.out.println("**Please select a number (1 - 4)**");

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
                                isGreenery = promptForBoolean(
                                        "Is your item a Greenery? (Enter 'Y' for yes and 'N' for no)",
                                        scanner);
                                if (!isGreenery) {
                                    // If greenery is false, prompt for vases option
                                    isVases = promptForBoolean(
                                            "Is your item a Vase? (Enter 'Y' for yes and 'N' for no)",
                                            scanner);
                                    if (!isVases) {
                                        // If both greenery and vases are false, prompt for table runner option
                                        isTableRunner = promptForBoolean(
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

                        } catch (InputMismatchException e) {
                            System.out.println("\nCaught InputMismatchException. Please enter a valid entry.");
                        } catch (Exception e) {
                            System.out.println(
                                    "\nCaught Exception. There may be a possible error that occurred during user input.");
                        }
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    // Exit Program
                    case 4:
                        System.out.println("\nYou selected to exit the program");
                        System.out.println("Have a great day!\n");

                        exitProgram = true; // Set exit flag to true to terminate the loop
                        break;

                    default:
                        System.out.println("\nInvalid input. Please enter a number between 1 and 4.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nCaught InputMismatchException. Please enter a valid entry.\n");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }

        }

        scanner.close(); // Closing scanner to release resources

    }

    /**
     * Prompts the user for a boolean value (Y/N) and keeps prompting until valid
     * input is provided.
     * 
     * @param prompt  The prompt to display to the user.
     * @param scanner The scanner object to read user input.
     * @return The boolean value entered by the user.
     */
    private static boolean promptForBoolean(String prompt, Scanner scanner) {
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
