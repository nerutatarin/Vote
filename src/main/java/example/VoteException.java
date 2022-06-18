package example;

public class VoteException extends RuntimeException {

    public VoteException() {
    }

    public VoteException(String message) {
        super(message);
    }

    public VoteException(Throwable cause) {
        super(cause);
    }

    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }
}