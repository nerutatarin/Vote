package service.retrofit;

public class RetrofitException extends RuntimeException {

    public RetrofitException(String errorMessage) {
        super(errorMessage);
    }

    public RetrofitException(String string, Throwable e) {
        super(string, e);
    }

}
