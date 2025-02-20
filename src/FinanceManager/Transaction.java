package FinanceManager;

import java.io.Serializable;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    double amount;
    String type;
    String description;
    String date;

    public Transaction(double amount, String type, String description, String date) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
    }
}
