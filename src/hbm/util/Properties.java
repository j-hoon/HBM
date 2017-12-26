package hbm.util;


public class Properties {

	private final boolean isDebugMode = true;
//	private final boolean isHardDebugMode = true;
	
	private Properties() {
		
	}

	// Singleton (Demand Holder Idiom)
	private static class Singleton {
		private static final Properties instance = new Properties();
	}
	public static Properties getInstance() {
		return Singleton.instance;
	}
	
	// Getter 
	public boolean isDebugMode() {
		return isDebugMode;
	}
	
	// Setter
	
	
	// Public Methods
	
}
