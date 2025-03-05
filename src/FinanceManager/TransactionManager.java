package FinanceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TransactionManager {

    private final SessionManager session;

    public TransactionManager(SessionManager session) {
        this.session = session;

    }

    public static void clearConsole() {
        try {

            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println(" Unable to clear screen.");
        }
    }

    // Add a new transaction (Income, Expense, or Savings)
    public void addTransaction(Scanner scanner) {
        if (session.getLoggedInUser() == null) {
            System.out.println("No user is logged in.");
            return;
        }
        try {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter type (Income/Expense/Savings): ");
            String type = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD) or press Enter for today: ");
            String date = scanner.nextLine();

            // If date is empty, use today's date
            if (date.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(new Date());
            }

            User user = session.getLoggedInUser();

            if (type.equalsIgnoreCase("Income")) {
                // Add to total income
                user.totalIncome += amount;
            } else if (type.equalsIgnoreCase("Expense")) {
                // Add to total expense
                user.totalExpense += amount;
            } else if (type.equalsIgnoreCase("Savings")) {
                // If user wants to deposit money into savings
                if (amount <= 0) {
                    System.out.println("Savings amount must be positive.");
                    return;
                }
                // Increase user's total savings
                user.savings += amount;

                // Record the deposit in the user's savings history
                String depositDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                user.savingsRecords.add(new User.SavingsRecord(depositDate, amount));

                // Check if we've reached or exceeded the savings goal
                if (user.savings >= user.savingsGoal && user.savingsGoal > 0) {
                    System.out.printf("Congratulations! You've reached your savings goal of $%.2f!\n",
                            user.savingsGoal);
                }
            } else {
                System.out.println("Invalid type. Please enter Income, Expense, or Savings.");
                return;
            }

            // Add this transaction to the user's transaction list
            user.transactions.add(new Transaction(amount, type, description, date));

            // Save the data
            session.saveUserData();
            System.out.println("Transaction added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    // View transactions with a sub-menu
    public void viewTransactions(Scanner scanner) {
        if (session.getLoggedInUser() == null) {
            System.out.println("No user is logged in.");
            return;
        }
        if (session.getLoggedInUser().transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "|                                                  -Selection Menu-                                                        |");
        System.out.println(
                "|                                   Please select one of the view format options:                                          |");
        System.out.println(
                "|                                         1) Table Row View                                                                |");
        System.out.println(
                "|                                         2) Summary View                                                                  |");
        System.out.println(
                "|                                         3) Details Display View                                                          |");
        System.out.println(
                "============================================================================================================================");
        System.out.print("Enter Your Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                clearConsole();
                displayTableRowView();
                break;
            case "2":
                clearConsole();
                displaySummaryView();
                break;
            case "3":
                clearConsole();
                displayDetailsView(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void displayTableRowView() {
        User user = session.getLoggedInUser();
        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "|                                                                                                                          |");
        System.out.println(
                "|                                                    Table Row View                                                        |");
        System.out.println(
                "|                                                                                                                          |");
        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "|       No.        |         Amount          |          Type          |         Date         |         Description         |");
        System.out.println(
                "============================================================================================================================");

        int index = 1;
        for (Transaction t : user.transactions) {
            System.out.printf("| %-16d | $%-22.2f | %-22s | %-20s | %-27s |\n",
                    index, t.amount, t.type, t.date, t.description);
            index++;
        }
        System.out.println(
                "============================================================================================================================");
    }

    private void displaySummaryView() {
        User user = session.getLoggedInUser();
        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "|                                                                                                                          |");
        System.out.println(
                "|                                                    Summary View                                                          |");
        System.out.println(
                "|                                                                                                                          |");
        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "|               Date               |                      Type                      |              Description             |");
        System.out.println(
                "============================================================================================================================");
        for (Transaction t : user.transactions) {
            System.out.printf("| %-32s | %-46s | %-36s |\n", t.date, t.type, t.description);

        }
        System.out.println(
                "============================================================================================================================");

    }

    private void displayDetailsView(Scanner scanner) {
        User user = session.getLoggedInUser();
        System.out.print("Enter transaction number to view details: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > user.transactions.size()) {
                System.out.println("Invalid transaction number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        Transaction t = user.transactions.get(choice - 1);

        System.out.println("============================================================");
        System.out.println("|                                                          |");
        System.out.println("|                    [Description: " + t.description + "]                 |");
        System.out.println("|                                                          |");
        System.out.println("============================================================");
        System.out.printf("| Date: %-50s |\n", t.date);
        System.out.printf("| Amount: $%-47.2f |\n", t.amount);
        System.out.printf("| Type: %-50s |\n", t.type);
        System.out.println("============================================================");
    }
}
