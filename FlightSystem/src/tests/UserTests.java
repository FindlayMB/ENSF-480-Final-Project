package tests;

import java.time.LocalDate;

import FlightSystem.objects.user.RegisteredUser;
import FlightSystem.objects.user.User;
import FlightSystem.objects.user.UsersSingleton;

public class UserTests {
    public static void displayUsers() {
        UsersSingleton.getInstance().getUsersMap().forEach((ui, u) -> {
            System.out.println(u);
        });
    }

    public static void displayRegUsers() {
        UsersSingleton.getInstance().getRegisteredUsersMap().forEach((ui, u) -> {
            System.out.println(ui + ": " + u);
        });
    }

    public static void main(String[] args) {
        // addUserTest();

        // removeUserTest();

        addRegUserTest();

        // removeRegUserTest();

        removeUserRegUserTest();

        System.out.println("\n\n");
        displayRegUsers();
    }

    // Works
    public static void addUserTest() {
        UsersSingleton users = UsersSingleton.getInstance();

        System.out.println("Before");
        displayUsers();

        users.addUser(new User(0, "John", "Doe", "JohnDoe@gmail.com", "guest"));

        System.out.println("\nAfter");
        displayUsers();
    }

    // Works
    public static void removeUserTest() {
        UsersSingleton users = UsersSingleton.getInstance();

        System.out.println("Before");
        displayUsers();

        users.removeUser(users.getUserByID(24));

        System.out.println("\nAfter");
        displayUsers();
    }

    // Works
    public static void addRegUserTest() {
        UsersSingleton users = UsersSingleton.getInstance();

        System.out.println("Before");
        // displayUsers();
        // displayRegUsers();

        UsersSingleton.getInstance().addRegisteredUser(
                new RegisteredUser(users.addUser(new User(0, "John", "Doe", "JohnDoe@gmail.com", "member")),
                        "john", "doe", LocalDate.now(), "member", null, null));

        System.out.println("\nAfter");
        displayUsers();
        displayRegUsers();
    }

    // works
    public static void removeRegUserTest() {
        UsersSingleton users = UsersSingleton.getInstance();

        System.out.println("Before");
        displayUsers();
        displayRegUsers();

        users.removeRegisteredUser(users.getRegisteredUser(24));

        System.out.println("\nAfter");
        displayUsers();
        displayRegUsers();
    }

    // Remove a registered user from user table
    // works
    public static void removeUserRegUserTest() {
        UsersSingleton users = UsersSingleton.getInstance();

        System.out.println("Before");
        displayUsers();
        displayRegUsers();

        UsersSingleton.getInstance().removeUser(users.getRegisteredUser(24));

        System.out.println("\nAfter");
        System.out.println("User table");
        displayUsers();
        System.out.println("Registered table");
        displayRegUsers();
    }
}
