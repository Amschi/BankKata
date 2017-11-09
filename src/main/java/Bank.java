public class Bank {

    private Account account;
    private Transfert transfert;

    public Bank(Account account, Transfert transfert) {
        this.account = account;
        this.transfert = transfert;
    }

    public Account getAccount() {
        return account;
    }

    public void startTransfert(Float transfertAmount, String iban) {

        if(transfert.deposit(iban, transfertAmount).depositResult == Transfert.DEPOSIT_RESULT.OK)
        {
            account.addTransaction( - transfertAmount);
        }



    }
}
