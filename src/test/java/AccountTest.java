
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class AccountTest {



    @Test
    public void balance_should_be_float_value () {

        //GIVEN
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        //WHEN

        //THEN
        assertThat(account.getBalance()).isExactlyInstanceOf(Float.class);

    }

    @Test
    public void should_have_account_balance_of_0_for_new_account() {
        // GIVEN
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        // WHEN

        // THEN

        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void balance_should_be_increased_by_deposit_amount() {
        // GIVEN
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        Float startBalance = account.getBalance();
        Float depositAmount = 10f;

        // WHEN
        account.addTransaction(depositAmount);

        // THEN
        assertThat(account.getBalance()).isEqualTo(startBalance + depositAmount);
    }


    @Test
    public void balance_should_be_decreased_by_withdraw_amount() {
        // GIVEN
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        Float startBalance = account.getBalance();
        Float withdrawAmount =  - 10f;
        // WHEN
        account.addTransaction(withdrawAmount);

        // THEN
        assertThat(account.getBalance()).isEqualTo(startBalance + withdrawAmount);
    }

    @Test
    public void count_transaction() {
        // GIVEN
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);

        // WHEN
        account.addTransaction(10f);
        account.addTransaction(20f);
        account.addTransaction(30f);

        // THEN
        assertThat(account.getNbTransactions()).isEqualTo(3);
    }

    @Test
    public void should_display_transaction_list_after_one_transaction_is_done() {
        // GIVEN
        Horloge horloge = Mockito.mock(Horloge.class);
        Account account = new Account(horloge);
        String expectedDisplay = "date || transaction || balance\n"
                + "2015-10-02 || -500,00 || -500,00\n"
                + "2017-12-03 || 200,00 || -300,00\n";

        // WHEN

        LocalDate dateTransaction1 = LocalDate.parse("2015-10-02");
        Mockito.when(horloge.getDate()).thenReturn(dateTransaction1);
        account.addTransaction(-500f);

        LocalDate dateTransaction2 = LocalDate.parse("2017-12-03");
        Mockito.when(horloge.getDate()).thenReturn(dateTransaction2);
        account.addTransaction(200f);

        // THEN
        assertThat(account.displayTransactions()).isEqualTo(expectedDisplay);
    }
}

