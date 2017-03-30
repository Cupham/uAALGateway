package echowand.logic;

public class TooManyObjectsException extends Exception {

    public TooManyObjectsException(String message) {
        super(message);
    }

    public TooManyObjectsException(String message, Throwable cause) {
        super(message, cause);
    }
}
