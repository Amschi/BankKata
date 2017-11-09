import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private LocalDate transactionDate;
    private Float amount;

    public Transaction(LocalDate date, Float amount) {
        this.transactionDate = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return transactionDate;
    }

    public Float getAmount() {
        return amount;
    }
}
