import java.io.*;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
			try {
				if (args.length > 0) {
					Finder finder = new Finder ();
					finder.search(args[0]);
				} else {
					FileMaker c = new FileMaker();
					c.setup();
					System.out.println("Total construction time: " + (System.currentTimeMillis() - startTime)  / 1000 + " seconds biatch");
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Search time: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
		
	}
}