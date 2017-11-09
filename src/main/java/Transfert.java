public class Transfert {


    public static final String URL_BANK_TRANSFERT = "http://api.bank.octo/transfert";

    public enum DEPOSIT_RESULT {
        OK, WRONG_FORMAT, NEGATIVE_VALUE
    }

    private WebService webService;

    public Transfert(WebService webService) {
        this.webService = webService;
    }

    public TransfertResponse deposit(String iban, float amount ) {
        ApiResponse apiResponse = webService.post(URL_BANK_TRANSFERT, iban, amount);

        if (apiResponse.getHttpCode() == 403)
        {
            return new TransfertResponse(DEPOSIT_RESULT.NEGATIVE_VALUE, null);
        }


        else if (apiResponse.getHttpCode() == 200)
        {
            return new TransfertResponse(DEPOSIT_RESULT.OK, apiResponse.getFee());
        }

        else if (apiResponse.getHttpCode() == 400) {
            return new TransfertResponse(DEPOSIT_RESULT.WRONG_FORMAT, null);
        }

        return null;
    }
}
