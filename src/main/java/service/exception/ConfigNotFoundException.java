package service.exception;

public class ConfigNotFoundException extends VoteServiceException{
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
