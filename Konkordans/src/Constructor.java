import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


public class Constructor {
	
	private int size;
	private int[] indexArray;
	
	public Constructor () throws UnsupportedEncodingException {
		size = "ööö".getBytes("ISO-8859-1").hashCode();
		System.out.println(size);
		indexArray = new int[size];
	}
	
	public void construct() {
		//do everything
		System.out.println("indexArray size: " + size);
		try {
			File index = File.createTempFile("index", "tmp", new File("/var/tmp"));
			File tokens = new File("/var/tmp/mdut");
			
			BufferedWriter indexWriter = new BufferedWriter(new FileWriter(index));
			OutputStream occurenceWriter = new FileOutputStream("/var/tmp/occurences");
			FileReader fr = new FileReader(tokens);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String word = "   ";
			int indexLine = 0;
			int occurenceIndex = 0;
			while ((line = br.readLine()) != null) {// while not end of file
				String[] stringArray = line.split(" ");//split line, create array with 2 elements
				
				if (!stringArray[0].equals(word)) {
					if(stringArray[0].length() < 3)
						stringArray[0] = stringArray[0] + "  "; //add trailing whitespace if 1 or 2 char
					if (!word.substring(0, 2).equals(stringArray[0].substring(0, 2))) {
						try {
							indexArray[stringArray[0].substring(0, 2).hashCode()] = indexLine;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println(e.toString() + " " + stringArray[0].substring(0, 2));
						}
					}
					word = stringArray[0];
					indexWriter.append(stringArray[0] + " " + occurenceIndex);
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
