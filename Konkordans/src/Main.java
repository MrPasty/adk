import java.io.*;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		Constructor c = new Constructor();
		c.construct();
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + ((endTime - startTime) / 1000) + " sekunder biatch");

	}

	/**
	 * @param 
	 */
	public void searchFile() {	
		try {
			RandomAccessFile file = new RandomAccessFile("/info/adk15/labb1/korpus", "r");
			file.seek(0);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

