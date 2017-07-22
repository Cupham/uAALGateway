package echowand.net;
/*
 * @author CuPham
 */
public class NetworkException extends Exception {
	
    public NetworkException(String message) {
        super(message);
    }
	
    public NetworkException(String message, Exception cause) {
        super(message, cause);
    }
}
