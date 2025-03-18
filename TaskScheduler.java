import java.util.Scanner;

public class TaskScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();

        while (true) {
            System.out.println("\nTask Scheduler - Circular Linked List");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Specific Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task & Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    scheduler.addTaskAtBeginning(inputTask(sc));
                    break;

                case 2:
                    scheduler.addTaskAtEnd(inputTask(sc));
                    break;

                case 3:
                    System.out.print("Enter position: ");
                    int position = sc.nextInt();
                    sc.nextLine();
                    scheduler.addTaskAtPosition(inputTask(sc), position);
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int taskId = sc.nextInt();
                    scheduler.removeTask(taskId);
                    break;

                case 5:
                    scheduler.viewAndMoveToNextTask();
                    break;

                case 6:
                    scheduler.displayTasks();
                    break;

                case 7:
                    System.out.print("Enter Priority to search: ");
                    int priority = sc.nextInt();
                    scheduler.searchTaskByPriority(priority);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to take user input for a task
    private static Task inputTask(Scanner sc) {
        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Priority: ");
        int priority = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Due Date: ");
        String dueDate = sc.nextLine();

        return new Task(id, name, priority, dueDate);
    }
}

// Node class for Circular Linked List
class Task {
    int id, priority;
    String name, dueDate;
    Task next;

    // Constructor
    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

// Circular Linked List Class for Task Scheduler
class Scheduler {
    private Task head, tail, current;

    // Constructor initializes an empty list
    public Scheduler() {
        this.head = this.tail = this.current = null;
    }

    // Add task at the beginning
    public void addTaskAtBeginning(Task newTask) {
        if (head == null) {
            head = tail = current = newTask;
            tail.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
        System.out.println("Task added at the beginning.");
    }

    // Add task at the end
    public void addTaskAtEnd(Task newTask) {
        if (head == null) {
            head = tail = current = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
        System.out.println("Task added at the end.");
    }

    // Add task at a specific position
    public void addTaskAtPosition(Task newTask, int position) {
        if (position <= 1) {
            addTaskAtBeginning(newTask);
            return;
        }

        Task temp = head;
        int count = 1;

        while (temp.next != head && count < position - 1) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (newTask.next == head) {
            tail = newTask;
        }

        System.out.println("Task added at position " + position);
    }

    // Remove task by ID
    public void removeTask(int taskId) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }

        Task temp = head, prev = null;

        do {
            if (temp.id == taskId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = temp.next;
                }

                if (head == tail && head.id == taskId) {
                    head = tail = current = null;
                }

                System.out.println("Task removed.");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Task not found.");
    }

    // View current task and move to next
    public void viewAndMoveToNextTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("Current Task: ");
        displayTask(current);
        current = current.next;
    }

    // Display all tasks
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        System.out.println("Task List:");

        do {
            displayTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search task by priority
    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                displayTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task found with priority " + priority);
        }
    }

    // Helper method to display task details
    private void displayTask(Task task) {
        System.out.println("Task ID: " + task.id);
        System.out.println("Task Name: " + task.name);
        System.out.println("Priority: " + task.priority);
        System.out.println("Due Date: " + task.dueDate);
        System.out.println("-----------------------------");
    }
}
