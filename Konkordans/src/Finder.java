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
	private int BYTE_SIZE;

	public Finder () throws FileNotFoundException, IOException, ClassNotFoundException {
        BYTE_SIZE = FileMaker.byteSize;
		inputStream = new ObjectInputStream(new FileInputStream("indexArrayFile"));
		indexArray = (int[])inputStream.readObject();
		endStream = new ObjectInputStream(new FileInputStream("endArrayFile"));
		endArray = (int[])endStream.readObject();
		corpus = new RandomAccessFile("korpus", "r");
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
		int m = i;
		byte[] b = new byte[45];
		index.seek(i);
		index.readFully(b);
		System.out.println(new String(b, "ISO-8859-1"));
		while (j - i > 10) {
			m = ((i + j) / 2) * BYTE_SIZE; //TODO get byte size from filemaker
			index.seek(m);
			index.readFully(b, 0, 45);
			int comp = (new String(b, "ISO-8859-1")).compareTo(w);
			System.out.println("comp: " + comp);
			if (comp < 0)
				return binarySearch(i, m);
			else if (comp > 0)
				return binarySearch(m, j);
		}
		System.out.println("word: " + new String(b, "ISO-8859-1"));
		return m;
	}
}
