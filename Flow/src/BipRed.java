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
	int x, y, e, v, s, t, totflow;

	void readBipartiteGraph() {
		// Läs antal hörn och kanter
		x = io.getInt();
		y = io.getInt();
		e = io.getInt();
		v = x + y;
		neighbours = new ArrayList<ArrayList<Integer>>(v);
		
		if (debug)
			System.out.println("\n x: " + x + ", y: " + y + ", e: " + e + ", v: " + v + "\n");

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
		s = v + 1;
		t = v + 2;
		StringBuilder sb = new StringBuilder();
		sb.append((v + 2) + "\n");
		sb.append(s + " " + t + "\n");
		sb.append((e + v) + "\n");
		for (int a = 0; a < neighbours.size(); a++) {
			for (int b : neighbours.get(a)) {
				sb.append((a + 1) + " " + b + " 1" + "\n");
			}
		}
		for (int i = 1; i <= v; i++) {
			if (i <= x)
				sb.append(s + " " + i + " 1" + "\n");
			else
				sb.append(i + " " + t + " 1" + "\n");
		}
		String output = sb.toString();
		
		// Skriv ut antal hörn och kanter samt källa och sänka
		if (debug)
			System.out.println(output);
		io.println(output);
		
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
		v = io.getInt();
		s = io.getInt();
		t = io.getInt();
		totflow = io.getInt();
		e = io.getInt();
		neighbours = new ArrayList<ArrayList<Integer>>(v);

		for (int i = 0; i < e; ++i) {
			int a = io.getInt();
			if (a == s || a == t) {	// exkludera alla kanter från källan / till sänkan
				io.getInt();
				io.getInt();
				continue;
			}
			int b = io.getInt();
			if (b == s || b == t) {	// exkludera alla kanter från källan / till sänkan
				io.getInt();
				continue;
				}
			io.getInt();
			if (a >= neighbours.size())
				neighbours.add(new ArrayList<Integer>());
			neighbours.get(a - 1).add(b);	//TODO: optimera?
		}
		System.out.println("neighbours size: " + neighbours.size());
	}


	void writeBipMatchSolution() {
		// Skriv ut antal hörn och storleken på matchningen
		io.println(x + " " + y);
//		io.println(totflow);
		
		v -= 2; // ta bort källa och sänka
		e -= v; // ta bort kanter till/från källa och sänka
		StringBuilder sb = new StringBuilder();
		// following is correct format?
		sb.append(e + "\n");
		for (int a = 0; a < neighbours.size(); a++) {
			for (int b : neighbours.get(a)) {
				sb.append((a + 1) + " " + b + "\n");
			}
		}
		String output = sb.toString();
		// Skriv ut antal hörn och kanter samt källa och sänka
		if (debug)
			System.out.println(output);
		io.println(output);

//		for (int i = 0; i < totflow; ++i) {
//			int a = 5, b = 2323;
//			// Kant mellan a och b ingår i vår matchningslösning
//			io.println(a + " " + b);
//		}

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

