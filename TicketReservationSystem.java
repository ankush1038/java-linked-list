import java.util.Scanner;

public class TicketReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketQueue ticketQueue = new TicketQueue();

        while (true) {
            System.out.println("\nOnline Ticket Reservation System");
            System.out.println("1. Add Ticket");
            System.out.println("2. Remove Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Count Total Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Ticket ID: ");
                int id = sc.nextInt();
                sc.nextLine(); // Consume newline
                System.out.print("Enter Customer Name: ");
                String customerName = sc.nextLine();
                System.out.print("Enter Movie Name: ");
                String movieName = sc.nextLine();
                System.out.print("Enter Seat Number: ");
                String seatNumber = sc.nextLine();
                System.out.print("Enter Booking Time: ");
                String bookingTime = sc.nextLine();

                ticketQueue.addTicket(new TicketNode(id, customerName, movieName, seatNumber, bookingTime));
            } else if (choice == 2) {
                System.out.print("Enter Ticket ID to remove: ");
                int id = sc.nextInt();
                ticketQueue.removeTicket(id);
            } else if (choice == 3) {
                ticketQueue.displayTickets();
            } else if (choice == 4) {
                System.out.print("Search by (1) Customer Name or (2) Movie Name: ");
                int searchType = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (searchType == 1) {
                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.nextLine();
                    ticketQueue.searchByCustomer(customerName);
                } else if (searchType == 2) {
                    System.out.print("Enter Movie Name: ");
                    String movieName = sc.nextLine();
                    ticketQueue.searchByMovie(movieName);
                } else {
                    System.out.println("Invalid choice!");
                }
            } else if (choice == 5) {
                System.out.println("Total Booked Tickets: " + ticketQueue.countTickets());
            } else if (choice == 6) {
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
class TicketNode {
    int ticketId;
    String customerName, movieName, seatNumber, bookingTime;
    TicketNode next;

    // Constructor
    public TicketNode(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

// Circular Linked List Class for Ticket Reservation System
class TicketQueue {
    private TicketNode head, tail;

    // Constructor initializes an empty list
    public TicketQueue() {
        this.head = this.tail = null;
    }

    // Add a new ticket at the end (without using built-in methods)
    public void addTicket(TicketNode newTicket) {
        if (head == null) {
            head = tail = newTicket;
            tail.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        System.out.println("Ticket ID " + newTicket.ticketId + " booked successfully.");
    }

    // Remove a ticket by Ticket ID (without using built-in methods)
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        TicketNode temp = head, prev = null;

        // Traverse the circular linked list
        do {
            if (temp.ticketId == ticketId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = temp.next;
                }

                if (head == tail && head.ticketId == ticketId) {
                    head = tail = null;
                }

                System.out.println("Ticket ID " + ticketId + " removed successfully.");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Ticket ID not found.");
    }

    // Display all tickets (without built-in list functions)
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        TicketNode temp = head;
        System.out.println("\nBooked Tickets:");

        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    // Search ticket by Customer Name (without using built-in search methods)
    public void searchByCustomer(String customerName) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        TicketNode temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket ID: " + temp.ticketId + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No ticket found for customer: " + customerName);
        }
    }

    // Search ticket by Movie Name (without using built-in search methods)
    public void searchByMovie(String movieName) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        TicketNode temp = head;
        boolean found = false;

        do {
            if (temp.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tickets found for movie: " + movieName);
        }
    }

    // Count the total number of booked tickets (without built-in size methods)
    public int countTickets() {
        if (head == null) {
            return 0;
        }

        int count = 0;
        TicketNode temp = head;

        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        return count;
    }
}
