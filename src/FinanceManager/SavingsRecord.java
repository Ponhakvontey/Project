package FinanceManager;
import java.io.Serializable;
public class SavingsRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    public String date;
    public double amountSaved;

    public SavingsRecord(String date, double amountSaved) {
        this.date = date;
        this.amountSaved = amountSaved;
    }
}
    

