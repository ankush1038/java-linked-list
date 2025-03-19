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
            sc.nextLine();

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

class FriendNode {
    int friendId;
    FriendNode next;

    public FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class User {
    int userId;
    String name;
    int age;
    FriendNode friends;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = null;
        this.next = null;
    }
}

class FriendNetwork {
    private User head;

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

    public void addFriend(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!isFriend(user1.friends, userId2)) {
            user1.friends = new FriendNode(userId2, user1.friends);
            user2.friends = new FriendNode(userId1, user2.friends);
            System.out.println("Friend connection added.");
        } else {
            System.out.println("Users are already friends.");
        }
    }

    private boolean isFriend(FriendNode head, int friendId) {
        FriendNode temp = head;
        while (temp != null) {
            if (temp.friendId == friendId) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void displayFriends(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        FriendNode temp = user.friends;
        if (temp == null) {
            System.out.println(user.name + " has no friends.");
            return;
        }

        System.out.print(user.name + "'s Friends: ");
        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
