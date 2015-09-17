import java.io.*;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		Constructor c = new Constructor();
		c.construct();
		final long endTime = System.currentTimeMillis();
		Long constructionTime = endTime - startTime;
		System.out.println("Total construction time: " + constructionTime  / 1000 + " seconds biatch");
		if (args.length > 0) {
			Finder finder = new Finder ();
			finder.search(args[0]);
		}
		System.out.println("Search time: " + (System.currentTimeMillis() - constructionTime) / 1000 + " seconds");
	}
}

