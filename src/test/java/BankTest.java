import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BankTest {

    @Test
    public void when_i_make_transfert_linked_account_is_debited() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "AZE", 25)).thenReturn(new ApiResponse(200, null));
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        Bank bank = new Bank(account, transfert);
        Float initialAccountBalance = account.getBalance();
        Float transfertAmount = 25f;


        // WHEN
        bank.startTransfert(transfertAmount, "AZE");

        // THEN
        assertThat(bank.getAccount().getBalance()).isEqualTo(initialAccountBalance - transfertAmount);
    }

    @Test
    public void bank_must_execute_deposit_on_transfert() {
        // GIVEN
        Transfert transfert = Mockito.mock(Transfert.class);
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        Bank bank = new Bank(account, transfert);


        // WHEN
        bank.startTransfert(250f, "EZPZLEMONSQUIZZY");


        // THEN
        Mockito.verify(transfert).deposit("EZPZLEMONSQUIZZY", 250f);
    }

    @Test
    public void if_amount_negative_transfer_not_executed() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "", -25)).thenReturn(new ApiResponse(403, null));
        Horloge horloge = new Horloge();
        Account account = new Account(horloge);
        Bank bank = new Bank(account, transfert);
        Float initialAccountBalance = account.getBalance();


        // WHEN
        bank.startTransfert(-250f, "EZPZLEMONSQUIZZY");


        // THEN
        assertThat(bank.getAccount().getBalance()).isEqualTo(initialAccountBalance);
    }
}
