import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Constructor {
	
	private int size;
	private int[] indexArray;

	public Constructor () {
		size = Hasher.hash("ööö");
		System.out.println(size);
		indexArray = new int[size];
	}
	
	public void construct() {
		//do everything
		System.out.println("indexArray size: " + size);
		try {
			File index = new File("/var/tmp/index");
			FileInputStream tokens = new FileInputStream("/var/tmp/mdut");
			
			BufferedWriter indexWriter = new BufferedWriter(new FileWriter(index));
			OutputStream occurenceWriter = new FileOutputStream("/var/tmp/occurences");
			InputStreamReader ir = new InputStreamReader(tokens, "ISO-8859-1");
			BufferedReader br = new BufferedReader(ir);
			
			String line;
			String word = "   ";
			String stump = word;
			String nextStump;
			int indexLine = 0;
			int occurenceIndex = 0;
			while ((line = br.readLine()) != null) {// while not end of file
				String[] stringArray = line.split(" ");//split line, create array with 2 elements
				if(!stringArray[0].equals(word)) {
					nextStump = stringArray[0];
					if(nextStump.length() > 2)
						nextStump = nextStump.substring(0, 2);
					if (!stump.equals(nextStump)) {
						try {
							indexArray[Hasher.hash(nextStump)] = indexLine;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println(e.toString() + " " + nextStump);
						}
					}
					word = stringArray[0];
					stump = nextStump;
					indexWriter.append(word + " " + occurenceIndex);
					indexLine++;
					indexWriter.newLine();
				}
				occurenceWriter.write(stringArray[1].getBytes("ISO-8859-1"));
				//System.out.println(stringArray[1].getBytes("ISO-8859-1"));
				occurenceIndex++;
			}
			indexWriter.close();
			occurenceWriter.close();
			br.close();
			ObjectOutputStream arrayFileStream = new ObjectOutputStream(new FileOutputStream("indexArrayFile"));
			arrayFileStream.writeObject(indexArray);
			arrayFileStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
