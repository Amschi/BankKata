import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final String HEADER_TRANSACTION_LIST = "date || transaction || balance\n";
    private Float balance = 0f;
    private Horloge horloge;


    private List<Transaction> transactionsList = new ArrayList<Transaction>();

    public Account(Horloge horloge) {
        this.horloge = horloge;
    }

    public Float getBalance() {
        return balance;
    }

    public void addTransaction(Float amount) {
        balance = balance + amount;
        transactionsList.add(new Transaction(horloge.getDate(), amount));
    }


    public int getNbTransactions() {
        return transactionsList.size();
    }


    public String displayTransactions() {
        String transactionsString = HEADER_TRANSACTION_LIST;
        Float balance = 0f;

        for(int i = 0; i < transactionsList.size(); i++) {
            Transaction transaction = transactionsList.get(i);
            Float transactionAmount = transaction.getAmount();
            balance += transactionAmount;

            transactionsString += transaction.getDate() +" || " + String.format("%.2f", transactionAmount) + " || " + String.format("%.2f", balance) + "\n";
        }

        return transactionsString;
    }
}

