
public class WebService {

    public int post (String url){
        return 500;
    }

    public ApiResponse post (String url, String iban, float amount) {
        return new ApiResponse(500, 0f);
    }

}
