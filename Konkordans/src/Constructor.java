import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Constructor {
	
	private int size;
	private int[] indexArray;
	private int[] endArray;

	public Constructor () {
		size = Hasher.hash("ööö");
		indexArray = new int[size+1];
		endArray = new int[size+1];
	}
	
	public void construct() {
		try {
			File index = new File("/var/tmp/index");
			FileInputStream tokens = new FileInputStream("/var/tmp/mdut");
			
			OutputStream indexWriter = new FileOutputStream(index);
			OutputStream occurenceWriter = new FileOutputStream("/var/tmp/occurences");
			InputStreamReader ir = new InputStreamReader(tokens, "ISO-8859-1");
			BufferedReader br = new BufferedReader(ir);
			
			String line;
			String word = "   ";
			String stump = word;
			String nextStump;
			int lastHash = 0;
			int indexOffset = 0;
			int occurenceOffset = 0;
			while ((line = br.readLine()) != null) {// while not end of file
				String[] stringArray = line.split(" ");//split line, create array with 2 elements
				if(!stringArray[0].equals(word)) {
					nextStump = stringArray[0];
					if(nextStump.length() > 2)
						nextStump = nextStump.substring(0, 3);
					if (!stump.equals(nextStump)) {
						try {
							int hash = Hasher.hash(nextStump);
							indexArray[hash] = indexOffset;
							endArray[lastHash] = indexOffset;
							lastHash = hash;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println(e.toString() + " " + nextStump);
						}
					}
					word = stringArray[0];
					stump = nextStump;
					//TODO: Create consistent byte array size
					indexWriter.write((word + " " + occurenceOffset).getBytes("ISO-8859-1"));
					indexOffset++;
				}
				occurenceWriter.write(stringArray[1].getBytes("ISO-8859-1"));
				//System.out.println(stringArray[1].getBytes("ISO-8859-1"));
				occurenceOffset++;
			}
			indexWriter.close();
			occurenceWriter.close();
			br.close();
			ObjectOutputStream indexArrayFileStream = new ObjectOutputStream(new FileOutputStream("indexArrayFile"));
			indexArrayFileStream.writeObject(indexArray);
			indexArrayFileStream.close();
			ObjectOutputStream endArrayFileStream = new ObjectOutputStream(new FileOutputStream("endArrayFile"));
			endArrayFileStream.writeObject(endArray);
			endArrayFileStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
