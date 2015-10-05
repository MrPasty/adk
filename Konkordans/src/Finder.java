import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;


public class Finder {

	RandomAccessFile corpus;
	RandomAccessFile index;
	private ObjectInputStream inputStream;
	private ObjectInputStream endStream;
	private int[] indexArray;
	private int[] endArray;
	private String w;
	
	public Finder () throws FileNotFoundException, IOException, ClassNotFoundException {
			inputStream = new ObjectInputStream(new FileInputStream("indexArrayFile"));
			indexArray = (int[])inputStream.readObject();
			endStream = new ObjectInputStream(new FileInputStream("endArrayFile"));
			endArray = (int[])endStream.readObject();
			corpus = new RandomAccessFile("/info/adk15/labb1/korpus", "r");
	}

	/**
	 * @param 
	 * @throws IOException 
	 */
	public void search(String args) throws IOException {
		w = args;
		int hash = Hasher.hash (args.substring(0, 3));
		int begin = indexArray[hash];
		int end = endArray[hash];
		System.out.println("begin: " + begin);
		System.out.println("end: " + end);
		index = new RandomAccessFile("/var/tmp/index", "r");
		binarySearch(begin, end);
		corpus.seek(binarySearch(begin, end));
		corpus.readLine();
		corpus.close();
	}
	
	private int binarySearch (int i, int j) throws IOException {
		index.seek(i);
		System.out.println("i: " + index.readByte());
		index.seek(j);
		System.out.println("j: " + index.readByte());
		int m = i;
		while (j - i > 10) {
			m = (i + j) / 2;
			index.seek(m);
			int comp = index.readLine().compareTo(w);
			System.out.println("comp: " + comp);
			if (comp < 0)
				return binarySearch(i, m);
			else if (comp > 0)
				return binarySearch(m, j);
		}
		System.out.println("line: " + index.readByte());
		return m;
	}
}
