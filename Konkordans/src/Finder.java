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
	private String w;
	
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
		w = args;
		int begin = Hasher.hash (args.substring(0, 3));
		int end = 0;
		for (int i = begin + 1; i < indexArray.length; i++) {
			if (indexArray[i] != 0) {
				end = i;
				break;
			}
		}
		begin = indexArray[begin];
		System.out.println("begin: " + begin);
		end = indexArray[end];
		System.out.println("end: " + end);
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
	
	private int binarySearch (int i, int j) throws IOException {
		int m = i;
		while (j - i > 1000) {
			m = (i + j) / 2;
			index.seek(m);
			int comp = index.readLine().compareTo(w);
			if (comp < 0)
				return binarySearch(i, m);
			else if (comp > 0)
				return binarySearch(m, j);
		}
		System.out.println(index.readLine());
		return m;
	}
}
