package FinanceManager;

public class DraftFinanceManager {

    // We keep references to our managers
    private final SessionManager sessionManager;
    private final TransactionManager transactionManager;
    private final SavingsManager savingsManager;

    // Constructor
    public DraftFinanceManager() {
        this.sessionManager = new SessionManager();
        this.transactionManager = new TransactionManager(sessionManager);
        this.savingsManager = new SavingsManager(sessionManager);
    }

    // Provide access to them
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public SavingsManager getSavingsManager() {
        return savingsManager;
    }

    // Load data at startup
    public void loadUserData() {
        sessionManager.loadUserData();
    }

    // Display user account status
    public void viewStatus() {
        User user = sessionManager.getLoggedInUser();
        if (user == null) {
            System.out.println("No user is logged in.");
            return;
        }
        System.out.println("============================================================================================================================");
        System.out.println("|                                                  Username: " + user.username + "                                                          |");
        System.out.println("============================================================================================================================");
        System.out.println("|                                                  Total Income: $" + user.totalIncome + "                                                   |");
        System.out.println("|                                                  Total Expense: $" + user.totalExpense + "                                                  |");
        System.out.println("|                                                  Savings: $" + user.savings + "                                                           |");
        System.out.println("|                                                  Net Balance: $" + (user.totalIncome - user.totalExpense-user.savings) + "                                                 |");
        System.out.println("============================================================================================================================");
    }
}
