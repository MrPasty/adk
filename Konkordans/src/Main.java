import java.io.*;
import java.lang.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
	public void indexFile() {
		//tell the position of the word
	}
}

