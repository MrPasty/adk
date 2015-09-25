import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;


public class Finder {

	RandomAccessFile corpus;
	RandomAccessFile index;
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
	 * @throws FileNotFoundException 
	 */
	public void search(String args) throws FileNotFoundException {
		term = args;
		int begin = Hasher.hash (args.substring(0, 2));
		int end = 0;
		for (int i = begin; i < arrayIndex.length; i++) {
			if (arrayIndex[i] != 0) {
				end = i;
				break;
			}
		}
		BufferedReader br = new BufferedReader(new FileReader(index));
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
			index.seek(pos);
			String pick = index.readLine();
			int comp = 
			if (comp < 0)
				binarySearch(begin, pos);
			else if (comp > 0)
				binarySearch(pos, end);
			return index.seek(pos);
		}
	}
}
