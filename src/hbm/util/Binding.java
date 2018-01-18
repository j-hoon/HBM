package hbm.util;

import javafx.beans.binding.StringBinding;

public class Binding {
	
	public static StringBinding pwBinding(String pw) {
		return new StringBinding() {
			@Override
			protected String computeValue() {
				if(pw.length() >= 8 && pw.length() <= 60) {
					return "********";
				} else
					return "INVALID";
			}
		};
	}
	
	public static StringBinding phoneBinding(String phone) {
		return new StringBinding() {
			@Override
			protected String computeValue() {
				if(phone.length() >= 8 && phone.length() <= 9) {
					return "01" + phone.charAt(0) + "-" + 
							((phone.length() == 8) ? (phone.substring(1, 4) + "-" + phone.substring(4)) : (phone.substring(1, 5) + "-" + phone.substring(5)));
				} else
					return "INVALID";
			}
		};
	}
	
}
