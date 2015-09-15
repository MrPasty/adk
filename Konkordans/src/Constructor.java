import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Constructor {
	
	public void construct() {
		//tell the position of the word
		try {
			File index = File.createTempFile("index", "tmp", new File("/var/tmp"));
			System.out.println(index.getAbsolutePath());
			File tokens = new File("/var/tmp/ut");
			
			BufferedWriter indexWriter = new BufferedWriter(new FileWriter(index));
			OutputStream posWriter = new FileOutputStream("/var/tmp/pos");
			FileReader fr = new FileReader(tokens);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String word = null;
			int startIndex = 0;
			while ((line = br.readLine()) != null) {
				String[] stringArray = line.split(" ");
				if (!stringArray[0].equals(word)) {
					word = stringArray[0];
					indexWriter.append(word + " " + startIndex);
					indexWriter.newLine();
				}
				posWriter.write(stringArray[1].getBytes("ISO-8859-1"));
				startIndex++;
			}
			indexWriter.close();
			posWriter.close();
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
