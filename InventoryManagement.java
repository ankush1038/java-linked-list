import java.util.Scanner;

public class InventoryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Item Quantity by ID");
            System.out.println("6. Search Item by ID");
            System.out.println("7. Search Item by Name");
            System.out.println("8. Display Inventory");
            System.out.println("9. Calculate Total Inventory Value");
            System.out.println("10. Sort by Item Name");
            System.out.println("11. Sort by Price");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    if (choice == 1) inventory.addAtBeginning(id, name, qty, price);
                    else if (choice == 2) inventory.addAtEnd(id, name, qty, price);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        inventory.addAtPosition(id, name, qty, price, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Item ID to Remove: ");
                    int removeId = sc.nextInt();
                    inventory.removeItem(removeId);
                    break;

                case 5:
                    System.out.print("Enter Item ID to Update Quantity: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter New Quantity: ");
                    int newQty = sc.nextInt();
                    inventory.updateQuantity(updateId, newQty);
                    break;

                case 6:
                    System.out.print("Enter Item ID to Search: ");
                    int searchId = sc.nextInt();
                    inventory.searchById(searchId);
                    break;

                case 7:
                    System.out.print("Enter Item Name to Search: ");
                    String searchName = sc.nextLine();
                    inventory.searchByName(searchName);
                    break;

                case 8:
                    inventory.displayInventory();
                    break;

                case 9:
                    inventory.calculateTotalValue();
                    break;

                case 10:
                    inventory.sortByName();
                    System.out.println("Inventory sorted by Item Name.");
                    break;

                case 11:
                    inventory.sortByPrice();
                    System.out.println("Inventory sorted by Price.");
                    break;

                case 12:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Class representing an item in the inventory
class Item {
    int itemId;
    String itemName;
    int quantity;
    double price;
    Item next;

    // Constructor to initialize an item node
    public Item(int itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

// Class managing the inventory using a singly linked list
class Inventory {
    private Item head;

    // Method to add an item at the beginning
    public void addAtBeginning(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Method to add an item at the end
    public void addAtEnd(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);

        if (head == null) {
            head = newItem;
            return;
        }

        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Method to add an item at a specific position
    public void addAtPosition(int itemId, String itemName, int quantity, double price, int position) {
        Item newItem = new Item(itemId, itemName, quantity, price);

        if (position == 1) {
            newItem.next = head;
            head = newItem;
            return;
        }

        Item temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Method to remove an item based on item ID
    public void removeItem(int itemId) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " removed.");
            return;
        }

        Item temp = head, prev = null;

        while (temp != null && temp.itemId != itemId) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Item not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Item with ID " + itemId + " removed.");
    }

    // Method to update an item's quantity by ID
    public void updateQuantity(int itemId, int newQuantity) {
        Item temp = head;

        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for Item ID " + itemId);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Method to search an item by ID
    public void searchById(int itemId) {
        Item temp = head;

        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("Item Found: " + temp.itemName + " | ID: " + temp.itemId + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Method to search an item by name
    public void searchByName(String itemName) {
        Item temp = head;

        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item Found: " + temp.itemName + " | ID: " + temp.itemId + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Method to display all inventory items
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        Item temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.itemId + " | Name: " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
            temp = temp.next;
        }
    }

    // Method to calculate total inventory value
    public void calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;

        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }

        System.out.println("Total Inventory Value: ₹" + totalValue);
    }

    // Sorting methods (to be implemented)
    public void sortByName() { /* Sorting logic */ }
    public void sortByPrice() { /* Sorting logic */ }
}