import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Constructor {
	
	private int[] indexArray;
	
	public void construct() {
		//tell the position of the word
		try {
			File index = File.createTempFile("index", "tmp", new File("/var/tmp"));
			System.out.println(index.getAbsolutePath());
			File tokens = new File("/var/tmp/ut");
			
			BufferedWriter indexWriter = new BufferedWriter(new FileWriter(index));
			OutputStream occurenceWriter = new FileOutputStream("/var/tmp/occurences");
			FileReader fr = new FileReader(tokens);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String word = null;
			int indexLine = 0;
			int occurenceIndex = 0;
			while ((line = br.readLine()) != null) {// while not end of file
				String[] stringArray = line.split(" ");//split line, create array with 2 elements
				if (!stringArray[0].equals(word)) {
					if (!word.substring(0, 2).equals(stringArray[0].substring(0, 2)))
						indexArray[stringArray[0].substring(0, 2).hashCode()] = indexLine;
					word = stringArray[0];
					indexWriter.append(stringArray[0] + " " + occurenceIndex);
					indexLine++;
					indexWriter.newLine();
				}
				occurenceWriter.write(stringArray[1].getBytes("ISO-8859-1"));
				occurenceIndex++;
			}
			indexWriter.close();
			occurenceWriter.close();
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
