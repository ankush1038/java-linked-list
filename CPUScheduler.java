import java.util.Scanner;

public class CPUScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProcessQueue processQueue = new ProcessQueue();

        System.out.print("Enter Time Quantum: ");
        int timeQuantum = sc.nextInt();
        sc.nextLine(); // Consume newline

        while (true) {
            System.out.println("\nRound-Robin CPU Scheduler");
            System.out.println("1. Add Process");
            System.out.println("2. Execute Scheduling");
            System.out.println("3. Display Processes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Process ID: ");
                int id = sc.nextInt();
                System.out.print("Enter Burst Time: ");
                int burstTime = sc.nextInt();
                System.out.print("Enter Priority: ");
                int priority = sc.nextInt();
                sc.nextLine(); // Consume newline

                processQueue.addProcess(new ProcessNode(id, burstTime, priority));
            } else if (choice == 2) {
                processQueue.executeScheduling(timeQuantum);
            } else if (choice == 3) {
                processQueue.displayProcesses();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                sc.close();
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Node class for Circular Linked List
class ProcessNode {
    int id, burstTime, priority, remainingTime;
    ProcessNode next;

    // Constructor
    public ProcessNode(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.next = null;
    }
}

// Circular Linked List Class for Round-Robin Scheduling
class ProcessQueue {
    private ProcessNode head, tail;

    // Constructor initializes an empty list
    public ProcessQueue() {
        this.head = this.tail = null;
    }

    // Add a new process at the end (without using built-in methods)
    public void addProcess(ProcessNode newProcess) {
        if (head == null) {
            head = tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
        System.out.println("Process " + newProcess.id + " added.");
    }

    // Execute Round-Robin Scheduling (without built-in lists)
    public void executeScheduling(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        int totalTime = 0;
        int completedProcesses = 0;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int totalProcesses = countProcesses();

        ProcessNode current = head;

        System.out.println("\nExecuting Round-Robin Scheduling...");
        while (completedProcesses < totalProcesses) {
            if (current.remainingTime > 0) {
                int executeTime = (current.remainingTime < timeQuantum) ? current.remainingTime : timeQuantum;
                totalTime += executeTime;
                current.remainingTime -= executeTime;

                if (current.remainingTime == 0) {
                    completedProcesses++;
                    int turnAroundTime = totalTime;
                    int waitingTime = turnAroundTime - current.burstTime;

                    totalTurnAroundTime += turnAroundTime;
                    totalWaitingTime += waitingTime;

                    System.out.println("Process " + current.id + " completed. Turnaround Time: " + turnAroundTime + ", Waiting Time: " + waitingTime);
                    removeProcess(current.id);
                }
            }
            current = current.next;
        }

        // Calculate and display averages
        double avgWaitingTime = totalProcesses > 0 ? (double) totalWaitingTime / totalProcesses : 0;
        double avgTurnAroundTime = totalProcesses > 0 ? (double) totalTurnAroundTime / totalProcesses : 0;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnAroundTime);
    }

    // Remove a process by ID (without using built-in methods)
    public void removeProcess(int processId) {
        if (head == null) {
            return;
        }

        ProcessNode temp = head, prev = null;

        // Traverse the circular linked list
        do {
            if (temp.id == processId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = temp.next;
                }

                if (head == tail && head.id == processId) {
                    head = tail = null;
                }

                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);
    }

    // Display all processes (without built-in list functions)
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }

        ProcessNode temp = head;
        System.out.println("\nProcess List:");

        do {
            System.out.println("Process ID: " + temp.id + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority + ", Remaining Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    // Count the number of processes in the list (without built-in size methods)
    private int countProcesses() {
        if (head == null) {
            return 0;
        }

        int count = 0;
        ProcessNode temp = head;

        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        return count;
    }
}
