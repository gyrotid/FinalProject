import java.util.*;
import java.io.*;

public class FinalProject {
    static ArrayList<String> activeOrders = new ArrayList<>();
    static String[] completedOrders = new String[50];
    static int completedCount = 0;
    static final String ACTIVE_FILE = "activeOrders.txt";
    static final String COMPLETED_FILE = "completedOrders.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load saved data
        activeOrders = FileHandler.loadData(ACTIVE_FILE);
        ArrayList<String> completedList = FileHandler.loadData(COMPLETED_FILE);
        completedCount = completedList.size();
        for (int i = 0; i < completedCount && i < completedOrders.length; i++) {
            completedOrders[i] = completedList.get(i);
        }

        int choice;
        do {
            System.out.println("\n--- Restaurant Order System ---");
            System.out.println("1. Add new order");
            System.out.println("2. View active orders");
            System.out.println("3. Complete an order");
            System.out.println("4. View completed orders");
            System.out.println("5. Save and Exit");
            System.out.print("Enter your choice: ");

            choice = InputValidator.getUserInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter new order description: ");
                    String order = scanner.nextLine();
                    activeOrders.add(order);
                    System.out.println("Order added!");
                    break;

                case 2:
                    System.out.println("Active Orders:");
                    if (activeOrders.isEmpty()) {
                        System.out.println("No active orders.");
                    } else {
                        for (int i = 0; i < activeOrders.size(); i++) {
                            System.out.println((i + 1) + ". " + activeOrders.get(i));
                        }
                    }
                    break;

                case 3:
                    if (activeOrders.isEmpty()) {
                        System.out.println("No active orders to complete.");
                        break;
                    }
                    System.out.println("Enter the index of the order to complete:");
                    int index = InputValidator.getUserInput() - 1;
                    try {
                        String completed = activeOrders.remove(index);
                        completedOrders[completedCount++] = completed;
                        System.out.println("Order completed: " + completed);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid order index. Try again.");
                    }
                    break;

                case 4:
                    System.out.println("Completed Orders:");
                    if (completedCount == 0) {
                        System.out.println("No completed orders yet.");
                    } else {
                        for (int i = 0; i < completedCount; i++) {
                            System.out.println((i + 1) + ". " + completedOrders[i]);
                        }
                    }
                    break;

                case 5:
                    saveAllData();
                    System.out.println("Data saved. Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    private static void saveAllData() {
        FileHandler.saveData(activeOrders, ACTIVE_FILE);
        ArrayList<String> completedList = new ArrayList<>();
        for (int i = 0; i < completedCount; i++) {
            completedList.add(completedOrders[i]);
        }
        FileHandler.saveData(completedList, COMPLETED_FILE);
    }
}
