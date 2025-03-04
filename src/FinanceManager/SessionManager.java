package FinanceManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SessionManager {
    private static final String DATA_FILE = "users.txt";

    // Keep track of all users
    private final Map<String, User> users = new HashMap<>();

    // The currently logged-in user
    private User loggedInUser = null;

    public Map<String, User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Load previously saved user data from file
    public void loadUserData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No previous data found, starting fresh.");
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj = in.readObject();
            if (obj instanceof HashMap) {
                users.putAll((HashMap<String, User>) obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh.");
            users.clear();
        }
    }

    // Save user data to file
    public void saveUserData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Register a new user
    public void registerUser(Scanner scanner) {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        users.put(username, new User(username, password));
        saveUserData();
        System.out.println("Registration successful! Please log in.");
    }

    // Log in
    public void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            loggedInUser = users.get(username);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    // Log out
    public void logout() {
        saveUserData();
        System.out.println("Logging out...");
        loggedInUser = null;
    }
}
