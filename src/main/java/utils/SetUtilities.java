package utils;

import java.util.Iterator;
import java.util.Set;

import mainpackage.Activator;

public class SetUtilities {
	public static void removeIfExistFromSet(Set<String> input, String value) {
		Iterator<String> it = input.iterator();
		while(it.hasNext()) {
			String val = it.next();
			if(val.equals(value)) {
				input.remove(value);
			}
		}
	}

}
