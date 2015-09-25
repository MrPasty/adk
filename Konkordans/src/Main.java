import java.io.*;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		if (args.length > 0) {
			Finder finder = new Finder ();
			try {
				finder.search(args[0]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Search time: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
		} else {
			Constructor c = new Constructor();
			c.construct();
			System.out.println("Total construction time: " + (System.currentTimeMillis() - startTime)  / 1000 + " seconds biatch");
		}
	}
}