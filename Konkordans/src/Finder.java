import java.io.IOException;
import java.io.RandomAccessFile;


public class Finder {

	RandomAccessFile file;
	
	public Finder () {
		try {
		file = new RandomAccessFile("/info/adk15/labb1/korpus", "r");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param 
	 */
	public void search(String args) {
			try {
				file.seek(0);
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
