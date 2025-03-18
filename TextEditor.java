import java.util.Scanner;

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Editor editor = new Editor(10); // Limit history to 10 states

        while (true) {
            System.out.println("\nTextEditor - Undo/Redo");
            System.out.println("1. Type New Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String newText = sc.nextLine();
                    editor.typeText(newText);
                    break;

                case 2:
                    editor.undo();
                    break;

                case 3:
                    editor.redo();
                    break;

                case 4:
                    editor.displayCurrentText();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Node class for Doubly Linked List
class TextNode {
    String text;
    TextNode prev, next;

    // Constructor
    public TextNode(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

// Doubly Linked List Class for TextEditor
class Editor {
    private TextNode head, tail, current;
    private final int historyLimit;
    private int historySize;

    // Constructor to initialize the editor with a history limit
    public Editor(int historyLimit) {
        this.head = this.tail = this.current = new TextNode(""); // Empty initial state
        this.historyLimit = historyLimit;
        this.historySize = 1;
    }

    // Typing new text (adds new state)
    public void typeText(String newText) {
        TextNode newNode = new TextNode(newText);

        if (current.next != null) {
            current.next = null; // Clear redo history
        }

        newNode.prev = current;
        current.next = newNode;
        current = newNode;

        if (historySize >= historyLimit) {
            head = head.next;
            head.prev = null;
        } else {
            historySize++;
        }

        System.out.println("Text added: " + newText);
    }

    // Undo functionality
    public void undo() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.text);
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    // Redo functionality
    public void redo() {
        if (current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.text);
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    // Display the current text
    public void displayCurrentText() {
        System.out.println("Current Text: " + current.text);
    }
}
