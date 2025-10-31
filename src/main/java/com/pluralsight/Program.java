package com.pluralsight;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        boolean isAdmin = askUserRole();

        if (isAdmin) {
            AdminUserInterface adminUserInterface = new AdminUserInterface();
            adminUserInterface.display();
        } else {
            UserInterface userInterface = new UserInterface();
            userInterface.display();
        }

    }

    public static boolean askUserRole() {
        final String ADMIN_PASSWORD = "admin@123";
        final int MAX_ATTEMPTS = 3;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Sign in as Admin (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes") || response.equals("y")) {
            for (int attemptsLeft = MAX_ATTEMPTS; attemptsLeft > 0; attemptsLeft--) {
                System.out.print("Enter admin password: ");
                String input = scanner.nextLine().trim();

                if (input.equals(ADMIN_PASSWORD)) {
                    System.out.println("Welcome, Admin!");
                    return true;
                } else if (attemptsLeft > 1) {
                    System.out.println("Incorrect password. Attempts left: " + (attemptsLeft - 1));
                }
            }

            System.out.println("Too many failed attempts. Switching to user mode.");
            return false;
        } else {
            System.out.println("Welcome, user!");
            return false;
        }
    }
}
