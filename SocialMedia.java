import java.util.ArrayList;
import java.util.Scanner;

public class SocialMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FriendNetwork network = new FriendNetwork();

        while (true) {
            System.out.println("\nSocial Media Friend Management");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display All Friends of a User");
            System.out.println("6. Search User by ID");
            System.out.println("7. Search User by Name");
            System.out.println("8. Count Friends of Each User");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    network.addUser(userId, name, age);
                    break;

                case 2:
                    System.out.print("Enter User ID 1: ");
                    int id1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int id2 = sc.nextInt();
                    network.addFriend(id1, id2);
                    break;

                case 3:
                    System.out.print("Enter User ID 1: ");
                    int removeId1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int removeId2 = sc.nextInt();
                    network.removeFriend(removeId1, removeId2);
                    break;

                case 4:
                    System.out.print("Enter User ID 1: ");
                    int mutualId1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int mutualId2 = sc.nextInt();
                    network.findMutualFriends(mutualId1, mutualId2);
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    int displayId = sc.nextInt();
                    network.displayFriends(displayId);
                    break;

                case 6:
                    System.out.print("Enter User ID to Search: ");
                    int searchId = sc.nextInt();
                    network.searchUserById(searchId);
                    break;

                case 7:
                    System.out.print("Enter Name to Search: ");
                    String searchName = sc.nextLine();
                    network.searchUserByName(searchName);
                    break;

                case 8:
                    network.countFriends();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Class representing a User in the social network
class User {
    int userId;
    String name;
    int age;
    ArrayList<Integer> friends;
    User next;

    // Constructor to initialize a user
    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
        this.next = null;
    }
}

// Class managing the friend network using a singly linked list
class FriendNetwork {
    private User head;

    // Method to add a new user
    public void addUser(int userId, String name, int age) {
        if (getUserById(userId) != null) {
            System.out.println("User ID already exists!");
            return;
        }

        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        System.out.println("User added successfully.");
    }

    // Method to get a user by their ID
    private User getUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Method to add a friend connection
    public void addFriend(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friends.contains(userId2)) {
            user1.friends.add(userId2);
            user2.friends.add(userId1);
            System.out.println("Friend connection added.");
        } else {
            System.out.println("Users are already friends.");
        }
    }

    // Method to remove a friend connection
    public void removeFriend(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (user1.friends.contains(userId2)) {
            user1.friends.remove(Integer.valueOf(userId2));
            user2.friends.remove(Integer.valueOf(userId1));
            System.out.println("Friend connection removed.");
        } else {
            System.out.println("Users are not friends.");
        }
    }

    // Method to find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        System.out.print("Mutual Friends: ");
        boolean found = false;
        for (int friendId : user1.friends) {
            if (user2.friends.contains(friendId)) {
                System.out.print(friendId + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No mutual friends found.");
        } else {
            System.out.println();
        }
    }

    // Method to display all friends of a user
    public void displayFriends(int userId) {
        User user = getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.friends.isEmpty()) {
            System.out.println(user.name + " has no friends.");
            return;
        }

        System.out.print(user.name + "'s Friends: ");
        for (int friendId : user.friends) {
            System.out.print(friendId + " ");
        }
        System.out.println();
    }

    // Method to search a user by their ID
    public void searchUserById(int userId) {
        User user = getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("User Found: ID: " + user.userId + ", Name: " + user.name + ", Age: " + user.age);
    }

    // Method to search a user by their name
    public void searchUserByName(String name) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found.");
    }

    // Method to count the number of friends for each user
    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friends.size() + " friends.");
            temp = temp.next;
        }
    }
}
