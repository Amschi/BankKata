public class TransfertResponse {

    public Transfert.DEPOSIT_RESULT depositResult;
    public Float fee;

    public TransfertResponse(Transfert.DEPOSIT_RESULT depositResult, Float fee) {
        this.depositResult = depositResult;
        this.fee = fee;
    }
}
