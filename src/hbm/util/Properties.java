package hbm.util;

public class Properties {

	private final boolean isDebugMode = true;
//	private final boolean isHardDebugMode = true;
	private static String osName;
	
	public enum OS_NAME {
		LINUX, WINDOWS7, UNKNOWN
	}
	
	
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
	public OS_NAME getOsName() {
		return OS_NAME.valueOf(osName);
	}
	
	// Setter
	public void setOsName() {
		String _OS_NAME = System.getProperty("os.name");
		if(_OS_NAME.equalsIgnoreCase("linux"))
			osName = OS_NAME.LINUX.toString();
		else if(_OS_NAME.equalsIgnoreCase("windows 7"))
			osName = OS_NAME.WINDOWS7.toString();
		else
			osName = OS_NAME.UNKNOWN.toString();
	}
	
	
	// Public Methods
	
	
}
