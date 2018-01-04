package hbm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean checkId(String id) {
		Pattern pattern = Pattern.compile("^[a-z0-9]{4,40}$");
		Matcher matcher = pattern.matcher(id);
		if(matcher.find())
			return true;
		else
			return false;
	}
}
