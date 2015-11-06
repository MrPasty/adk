import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class FileMaker {	
	private int size = Hasher.hash("ööö");
	private int[] indexArray = new int[size+1];
	private int[] endArray = new int[size+1];
	private int byteSize = 8;
	private int padding = 45;
	private byte[] longBytes = new byte[byteSize];
	private byte[] padded = new byte[padding];
	private FileInputStream tokens;
	private OutputStream indexWriter, occurenceWriter;
	private InputStreamReader ir;
	private BufferedReader br;
	
	public void setup() throws IOException{
		tokens = new FileInputStream("/var/tmp/ut");
		indexWriter = new FileOutputStream(new File("/var/tmp/index"));
		occurenceWriter = new FileOutputStream("/var/tmp/occurences");
		ir = new InputStreamReader(tokens, "ISO-8859-1");
		br = new BufferedReader(ir);
		
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
				long2Bytes(Integer.toUnsignedLong(occurenceOffset));
				writePadded(word.getBytes("ISO-8859-1"), indexWriter);
				indexWriter.write(longBytes);
				indexOffset++;
			}
			long2Bytes(Long.parseLong(stringArray[1]));
			writePadded(longBytes, occurenceWriter);
			occurenceOffset += byteSize;
		}
		System.out.println("occurenceOffset: " + occurenceOffset + ", indexOffset: " + indexOffset);
		finished();
	}

	private void writePadded (byte[] ba, OutputStream os) throws IOException {
		for(int i = 0; i < ba.length; i++)
			padded[i] = ba[i];
		os.write(padded);
		padded = new byte[padding];
	}
	
	private final void long2Bytes(long v) {
	    longBytes[0] = (byte)(v >>> 56);
	    longBytes[1] = (byte)(v >>> 48);
	    longBytes[2] = (byte)(v >>> 40);
	    longBytes[3] = (byte)(v >>> 32);
	    longBytes[4] = (byte)(v >>> 24);
	    longBytes[5] = (byte)(v >>> 16);
	    longBytes[6] = (byte)(v >>>  8);
	    longBytes[7] = (byte)(v >>>  0);
	}
	
	private void finished() throws IOException {
		indexWriter.close();
		occurenceWriter.close();
		br.close();
		ObjectOutputStream indexArrayFileStream = new ObjectOutputStream(new FileOutputStream("indexArrayFile"));
		indexArrayFileStream.writeObject(indexArray);
		indexArrayFileStream.close();
		ObjectOutputStream endArrayFileStream = new ObjectOutputStream(new FileOutputStream("endArrayFile"));
		endArrayFileStream.writeObject(endArray);
		endArrayFileStream.close();
	}
}
