public class ApiResponse {

    private int httpCode;
    private Float fee;

    public ApiResponse(int httpCode, Float fee) {
        this.httpCode = httpCode;
        this.fee = fee;
    }


    public int getHttpCode() {
        return httpCode;
    }

    public float getFee() {
        return fee;
    }
}
