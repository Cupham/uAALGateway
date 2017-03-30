package echowand.net;
/*
 * @author HiepNguyen
 */
public class NetworkException extends Exception {
	
    public NetworkException(String message) {
        super(message);
    }
	
    public NetworkException(String message, Exception cause) {
        super(message, cause);
    }
}
