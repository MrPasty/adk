import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Constructor {
	
	private String[] indexArray;
	
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
			int wordIndex = 0;
			int occurenceIndex = 0;
			while ((line = br.readLine()) != null) {
				String[] stringArray = line.split(" ");
				if (stringArray[0] != word) {
					indexArray[wordIndex] = stringArray[0].substring(0, 2);
					wordIndex++;
					indexWriter.append(stringArray[0] + " " + occurenceIndex);
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
