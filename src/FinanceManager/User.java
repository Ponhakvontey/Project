package FinanceManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // Basic fields
    String username;
    String password;
    double totalIncome = 0;
    double totalExpense = 0;
    List<Transaction> transactions = new ArrayList<>();

    // Savings-related fields
    public double savings = 0;
    public double savingsRate = 0;
    public double savingsGoal = 0;
    public int savingTimeMonths = 0;

    // A list of monthly savings deposits
    public List<SavingsRecord> savingsRecords = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Nested class to record each monthly savings deposit
    public static class SavingsRecord implements Serializable {
        private static final long serialVersionUID = 1L;
        public String date;
        public double amountSaved;

        public SavingsRecord(String date, double amountSaved) {
            this.date = date;
            this.amountSaved = amountSaved;
        }
    }
}
