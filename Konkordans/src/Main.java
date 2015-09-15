import java.io.*;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Constructor c = new Constructor();
		c.construct();

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

