package FinanceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SavingsManager {

    private final SessionManager session;

    public SavingsManager(SessionManager session) {
        this.session = session;
    }
    private void setSavingsRate(Scanner scanner) {
        User user = session.getLoggedInUser();
        System.out.print("Enter your desired savings rate (5-20%): ");
        try {
            double rate = Double.parseDouble(scanner.nextLine()) / 100.0;
            if (rate < 0.05 || rate > 0.20) {
                System.out.println("Rate must be between 5% and 20%.");
                return;
            }
            user.savingsRate = rate;
            System.out.println("Savings rate set to " + (rate * 100) + "%");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid percentage.");
        }
        session.saveUserData();
    }

    private void setSavingsGoal(Scanner scanner) {
        User user = session.getLoggedInUser();
        System.out.print("Enter your savings goal (e.g., 1000): ");
        try {
            double goal = Double.parseDouble(scanner.nextLine());
            if (goal < 0) {
                System.out.println("Goal cannot be negative.");
                return;
            }
            user.savingsGoal = goal;
            System.out.println("Savings goal set to $" + goal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        session.saveUserData();
    }

    private void setSavingsTime(Scanner scanner) {
        User user = session.getLoggedInUser();
        System.out.print("Enter the time period (in months) you plan to save: ");
        try {
            int months = Integer.parseInt(scanner.nextLine());
            if (months < 1) {
                System.out.println("Time period must be at least 1 month.");
                return;
            }
            user.savingTimeMonths = months;
            System.out.println("Saving time set to " + months + " month(s).");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
        session.saveUserData();
    }

    private void calculateMonthlySavings() {
        User user = session.getLoggedInUser();
        if (user.totalIncome < 250) {
            System.out.println("Total income is less than $250. No savings will be made this month.");
            return;
        }
        double monthlySavings = user.totalIncome * user.savingsRate;
        user.savings += monthlySavings;

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.savingsRecords.add(new User.SavingsRecord(date, monthlySavings));

        if (user.savings >= user.savingsGoal && user.savingsGoal > 0) {
            System.out.printf("Congratulations! You've reached your savings goal of $%.2f!\n", user.savingsGoal);
        }

        session.saveUserData();

        System.out.printf("Monthly savings of $%.2f has been added. Current total savings: $%.2f\n",
            monthlySavings, user.savings);
    }

    private void displaySavingsTable() {
        User user = session.getLoggedInUser();
        if (user.savingsRecords.isEmpty()) {
            System.out.println("No savings records to display.");
            return;
        }
        System.out.println("============================================================");
        System.out.println("|                Savings History Table                     |");
        System.out.println("============================================================");
        System.out.println("|   Date         |    Amount Saved                         |");
        System.out.println("============================================================");

        for (User.SavingsRecord sr : user.savingsRecords) {
            System.out.printf("| %-13s | $%-36.2f |\n", sr.date, sr.amountSaved);
        }
        System.out.println("============================================================");
        System.out.printf("Total Savings: $%.2f\n", user.savings);
    }

    // The main sub-menu for all savings features
    public void savingsMenu(Scanner scanner) {
        if (session.getLoggedInUser() == null) {
            System.out.println("No user is logged in.");
            return;
        }
        while (true) {
            System.out.println("============================================================");
            System.out.println("|                     SAVINGS MENU                         |");
            System.out.println("============================================================");
            System.out.println("| 1) Set/Modify Savings Rate (5-20%)                       |");
            System.out.println("| 2) Set/Modify Savings Goal                               |");
            System.out.println("| 3) Set/Modify Time Period (months)                       |");
            System.out.println("| 4) Calculate Monthly Savings                             |");
            System.out.println("| 5) View Savings Table                                    |");
            System.out.println("| 6) Return to Main Menu                                   |");
            System.out.println("============================================================");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    setSavingsRate(scanner);
                    break;
                case "2":
                    setSavingsGoal(scanner);
                    break;
                case "3":
                    setSavingsTime(scanner);
                    break;
                case "4":
                    calculateMonthlySavings();
                    break;
                case "5":
                    displaySavingsTable();
                    break;
                case "6":
                    // Return to main menu
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
}
