import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList studentList = new StudentList();

        while (true) {
            System.out.println("\nStudent Records Management");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student by Roll Number");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. Display All Students");
            System.out.println("7. Update Student Grade");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age1 = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade1 = sc.next().charAt(0);
                    studentList.addAtBeginning(roll1, name1, age1, grade1);
                    break;

                case 2:
                    System.out.print("Enter Roll Number: ");
                    int roll2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age2 = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade2 = sc.next().charAt(0);
                    studentList.addAtEnd(roll2, name2, age2, grade2);
                    break;

                case 3:
                    System.out.print("Enter Roll Number: ");
                    int roll3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name3 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age3 = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade3 = sc.next().charAt(0);
                    System.out.print("Enter Position: ");
                    int position = sc.nextInt();
                    studentList.addAtPosition(roll3, name3, age3, grade3, position);
                    break;

                case 4:
                    System.out.print("Enter Roll Number to Delete: ");
                    int rollDel = sc.nextInt();
                    studentList.deleteByRollNumber(rollDel);
                    break;

                case 5:
                    System.out.print("Enter Roll Number to Search: ");
                    int rollSearch = sc.nextInt();
                    studentList.searchByRollNumber(rollSearch);
                    break;

                case 6:
                    studentList.displayAll();
                    break;

                case 7:
                    System.out.print("Enter Roll Number to Update Grade: ");
                    int rollUpdate = sc.nextInt();
                    System.out.print("Enter New Grade: ");
                    char newGrade = sc.next().charAt(0);
                    studentList.updateGrade(rollUpdate, newGrade);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}

// Class representing a student node in the singly linked list
class Student {
    int rollNumber;
    String name;
    int age;
    char grade;
    Student next;

    // Constructor to initialize a student node
    public Student(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

// Class managing the singly linked list of students
class StudentList {
    private Student head;

    // Method to add a student at the beginning of the list
    public void addAtBeginning(int rollNumber, String name, int age, char grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Method to add a student at the end of the list
    public void addAtEnd(int rollNumber, String name, int age, char grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);

        if (head == null) {
            head = newStudent;
            return;
        }

        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    // Method to add a student at a specific position
    public void addAtPosition(int rollNumber, String name, int age, char grade, int position) {
        Student newStudent = new Student(rollNumber, name, age, grade);

        if (position == 1) {
            newStudent.next = head;
            head = newStudent;
            return;
        }

        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    // Method to delete a student record by roll number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
            return;
        }

        Student temp = head;
        Student prev = null;

        while (temp != null && temp.rollNumber != rollNumber) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Student with Roll Number " + rollNumber + " deleted.");
    }

    // Method to search for a student by roll number
    public void searchByRollNumber(int rollNumber) {
        Student temp = head;

        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found: Roll No: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to display all student records
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    // Method to update a student's grade by roll number
    public void updateGrade(int rollNumber, char newGrade) {
        Student temp = head;

        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                System.out.println("Grade updated for Roll No: " + rollNumber);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }
}
