import java.util.List;
import java.util.Scanner;

public class Main {
    private static final InventoryStack inventory = new InventoryStack();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("Simple Inventory Management (LIFO) - Vanilla Java CLI");

        while (running) {
            printMenu();
            System.out.print("Choose an action (1-5): ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": // Add Item
                    addItem(sc);
                    break;
                case "2": // Peek
                    peekItem();
                    break;
                case "3": // Pop
                    popItem();
                    break;
                case "4": // View All
                    viewAll();
                    break;
                case "5":
                    System.out.println("Exiting. Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Enter a number between 1 and 5.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\nActions:");
        System.out.println(" 1) Add Item (Push)");
        System.out.println(" 2) View Last Item (Peek)");
        System.out.println(" 3) Remove Last Item (Pop)");
        System.out.println(" 4) View All Inventory");
        System.out.println(" 5) Exit");
    }

    private static void addItem(Scanner sc) {
        try {
            System.out.print("Enter Item ID (integer): ");
            String idLine = sc.nextLine().trim();
            int id = Integer.parseInt(idLine);

            System.out.print("Enter Item Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Quantity (integer): ");
            String qLine = sc.nextLine().trim();
            int qty = Integer.parseInt(qLine);

            InventoryItem item = new InventoryItem(id, name, qty);
            inventory.push(item);
            System.out.println("Success: added " + item);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Item not added.");
        }
    }

    private static void peekItem() {
        InventoryItem top = inventory.peek();
        if (top == null) System.out.println("Inventory is empty.");
        else System.out.println("Top item: " + top);
    }

    private static void popItem() {
        InventoryItem removed = inventory.pop();
        if (removed == null) System.out.println("Inventory is empty. Nothing to remove.");
        else System.out.println("Removed: " + removed);
    }

    private static void viewAll() {
        List<InventoryItem> all = inventory.getAll();
        if (all.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory (Bottom -> Top):");
        for (InventoryItem it : all) {
            System.out.println(" - " + it);
        }
    }
}
