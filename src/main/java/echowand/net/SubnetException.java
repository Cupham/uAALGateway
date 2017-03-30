package echowand.net;

public class SubnetException extends Exception {

    public SubnetException(String message) {
        super(message);
    }

    public SubnetException(String message, Throwable cause) {
        super(message, cause);
    }
}
