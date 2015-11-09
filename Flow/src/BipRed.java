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
	int x, y, e, v, s, t;

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
		for (int i = 1; i <= v; i++){
			if (i <= x)
				sb.append(s + " " + i + " 1" + "\n");
			else
				sb.append(i + " " + t + " 1" + "\n");
		}
		String output = sb.toString();
		v += 2;
		e += v;
		
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
		int newV = io.getInt();
		int newS = io.getInt();
		int newT = io.getInt();
		boolean vbool = v == newV;
		boolean sbool = s == newS;
		boolean tbool = t == newT;
		if (!(vbool && sbool && tbool) && debug)	// antal hörn, källa och sänka ändrade?
			System.err.println("v/s/t mismatch");
		int totflow = io.getInt();
		e = io.getInt();
		neighbours = new ArrayList<ArrayList<Integer>>(v);

		for (int i = 0; i < e; ++i) {
			int a = io.getInt();
			if (a == newS || a == newT)	// exkludera alla kanter från källan / till inflödet
				continue;
			int b = io.getInt();
			if (b == newS || b == newT)	// exkludera alla kanter från källan / till inflödet
				continue;
			io.getInt();
			if (a > neighbours.size())
				neighbours.add(new ArrayList<Integer>());
			neighbours.get(a - 1).add(b);	//TODO: optimera?
		}
		v = newV - 2;
		e -= v;
		StringBuilder sb = new StringBuilder();
		sb.append(v + "\n");
		sb.append(s + " " + t + "\n");
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

