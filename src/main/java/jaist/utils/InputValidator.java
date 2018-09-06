package jaist.utils;

public class InputValidator {
	public static boolean isNotNull (String input) {
		if(input.equals("") || input.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

}
