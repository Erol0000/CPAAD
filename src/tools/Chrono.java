package tools;

public class Chrono {
	private static long start = 0;
	private static long end = 0;
	private static long time = 0;
	private static boolean isTiming = false;
	
	public static void start() {
		start = System.currentTimeMillis();
		isTiming=true;
	}
	
	
	public static void stop() {
		end = System.currentTimeMillis();
		isTiming = false;
		time = end - start;
		start = 0;
		end = 0;
	}
	public static Long currentTime() {
		return  System.currentTimeMillis()-start;
	}
	public static long getTime() {
		return time;
	}
	
	public static boolean isTiming() {
		return isTiming;
	}
}