import org.junit.Test;
import org.mockito.Mockito;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransfertTest {

    @Test
    public void deposit_with_negative_amount_rejected () {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "AZEAZE", -25)).thenReturn(new ApiResponse(400, null));


        // WHEN


        // THEN
        assertThat(transfert.deposit("AZEAZE", -25).depositResult).isEqualTo(Transfert.DEPOSIT_RESULT.NEGATIVE_VALUE);
    }

    @Test
    public void deposit_with_positive_amount_accepted() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "AZEAZE", 25)).thenReturn(new ApiResponse(200, null));

        // WHEN

        // THEN
        assertThat(transfert.deposit("AZEAZE", 25).depositResult).isEqualTo(Transfert.DEPOSIT_RESULT.OK);
    }

    @Test
    public void deposit_with_negative_amount_and_wrong_IBAN_declined() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "", -25)).thenReturn(new ApiResponse(400, null));

        // WHEN

        // THEN
        assertThat(transfert.deposit("", -25).depositResult).isEqualTo(Transfert.DEPOSIT_RESULT.WRONG_FORMAT);
    }

    @Test
    public void deposit_send_post_request_with_parameters() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);

        // WHEN
        transfert.deposit("AZE", 25);

        // THEN
        Mockito.verify(webService).post(Transfert.URL_BANK_TRANSFERT, "AZE", 25);
    }

    @Test
    public void accepted_deposit_has_fee() {
        // GIVEN
        WebService webService = Mockito.mock(WebService.class);
        Transfert transfert = new Transfert(webService);
        Mockito.when(webService.post(Transfert.URL_BANK_TRANSFERT, "AZE", 25)).thenReturn(new ApiResponse(200, 10f));


        // WHEN
        TransfertResponse transfertResponse = transfert.deposit("AZE", 25);


        // THEN
        assertThat(transfertResponse.fee).isEqualTo(10f);
    }
}
