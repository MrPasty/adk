import java.util.ArrayList;

/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
	boolean debug = false;
	Kattio io;
	ArrayList<ArrayList<Integer>> neighbours;
	int x, y, e, v;

	void readBipartiteGraph() {
		// Läs antal hörn och kanter
		x = io.getInt();
		y = io.getInt();
		e = io.getInt();
		v = x + y;
		neighbours = new ArrayList<ArrayList<Integer>>(v);

		// Läs in kanterna
		for (int i = 0; i < e; i++) {
			int a = io.getInt();
			int b = io.getInt();
			if (a > neighbours.size())
				neighbours.add(new ArrayList<Integer>());
			neighbours.get(a - 1).add(b);	//TODO: optimera?
		}
	}


	void writeFlowGraph() {
		int s = 0, t = v + 1;
		// Skriv ut antal hörn och kanter samt källa och sänka
		if (debug) {
			System.out.println(v);
			System.out.println(s + " " + t);
			System.out.println(e);
			for (int a = 0; a < neighbours.size(); a++) {
				for (int b : neighbours.get(a)) {
					System.out.println((a + 1) + " " + b + " 1");
				}
			}
			for (int i = 1; i <= v; i++){
				if (i <= x)
					System.out.println(s + " " + i + " 1");
				else
					System.out.println(i + " " + t + " 1");
			}
		} else {
			io.println(v);
			io.println(s + " " + t);
			io.println(e);
			for (int a = 0; a < neighbours.size(); a++) {
				for (int b : neighbours.get(a)) {
					io.println((a + 1) + " " + b + " 1");
				}
			}
			for (int i = 1; i <= v; i++){
				if (i <= x)
					io.println(s + " " + i + " 1");
				else
					io.println(i + " " + t + " 1");
			}
		}
		// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
		io.flush();

		// Debugutskrift
		if (debug)
			System.out.println("Skickade iväg flödesgrafen");
	}


	void readMaxFlowSolution() {
		// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
		// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
		// skickade iväg)
		int v = io.getInt();
		int s = io.getInt();
		int t = io.getInt();
		int totflow = io.getInt();
		int e = io.getInt();

		for (int i = 0; i < e; ++i) {
			// Flöde f från a till b
			int a = io.getInt();
			int b = io.getInt();
			int f = io.getInt();
		}
	}


	void writeBipMatchSolution() {
		int x = 17, y = 4711, maxMatch = 0;

		// Skriv ut antal hörn och storleken på matchningen
		io.println(x + " " + y);
		io.println(maxMatch);

		for (int i = 0; i < maxMatch; ++i) {
			int a = 5, b = 2323;
			// Kant mellan a och b ingår i vår matchningslösning
			io.println(a + " " + b);
		}

	}

	BipRed() {
		io = new Kattio(System.in, System.out);

		readBipartiteGraph();

		writeFlowGraph();

		readMaxFlowSolution();

		writeBipMatchSolution();

		// debugutskrift
		System.err.println("Bipred avslutar\n");

		// Kom ihåg att stänga ner Kattio-klassen
		io.close();
	}

	public static void main(String args[]) {
		new BipRed();
	}
}

