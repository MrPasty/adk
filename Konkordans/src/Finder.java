import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;


public class Finder {

	RandomAccessFile corpus;
	RandomAccessFile index;
	private ObjectInputStream inputStream;
	private int[] indexArray;
	private String term;
	
	public Finder () {
		try {
			inputStream = new ObjectInputStream(new FileInputStream("indexArrayFile"));
			indexArray = (int[])inputStream.readObject();
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
		for (int i = begin; i < indexArray.length; i++) {
			if (indexArray[i] != 0) {
				end = i;
				break;
			}
		}
		index = new RandomAccessFile("/var/tmp/index", "r");
		try {
			binarySearch(begin, end);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			try {
				corpus.seek(0);
				corpus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private String binarySearch (int begin, int end) throws IOException {
		int pos;
		while (end - begin > 1000) {
			pos = (begin + end) / 2;
			index.seek(pos);
			int comp = index.readUTF().compareTo(term);
			if (comp < 0)
				binarySearch(begin, pos);
			else if (comp > 0)
				binarySearch(pos, end);
		}
		return index.readUTF();
	}
}
