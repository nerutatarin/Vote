package service.exception;

public class BrowserNotFoundException extends VoteServiceException{
    public BrowserNotFoundException(String message) {
        super(message);
    }

    public BrowserNotFoundException() {
        super("Не удалось инициировать браузер");
    }
}
