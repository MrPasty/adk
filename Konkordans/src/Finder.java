import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;


public class Finder {

	RandomAccessFile corpus;
	File index;
	private ObjectInputStream inputStream;
	private int[] arrayIndex;
	private String term;
	
	public Finder () {
		try {
			inputStream = new ObjectInputStream(new FileInputStream("arrayIndexFile"));
			arrayIndex = (int[])inputStream.readObject();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		corpus = new RandomAccessFile("/info/adk15/labb1/korpus", "r");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param 
	 */
	public void search(String args) {
		term = args;
		int begin = Hasher.hash (args.substring(0, 2));
		int end = 0;
		for (int i = begin; i < arrayIndex.length; i++) {
			if (arrayIndex[i] != 0) {
				end = i;
				break;
			}
		}
		binarySearch(begin, end);
			try {
				corpus.seek(0);
				corpus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void binarySearch (int begin, int end) {
		int pos;
		while (end - begin > 1000) {
			pos = (begin + end) / 2;
//			if (index.seek(pos) <= )
		}
	}
}
