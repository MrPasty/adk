import java.util.ArrayList;
import java.util.HashMap;

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
	private Kattio io;
	private final boolean debug = false;
	private HashMap<Integer, ArrayList<Edge>> edges;

	public int x, y, e, v, s, t, totflow;

	public BipRed() {
		io = new Kattio(System.in, System.out);
	}
	
	public HashMap<Integer, ArrayList<Edge>> readBipartiteGraph() {
		// Läs antal hörn och kanter
		x = io.getInt();
		y = io.getInt();
		e = io.getInt();
		v = x + y;
		edges = new HashMap<Integer, ArrayList<Edge>>();
		
		if (debug)
			System.out.println("\n x: " + x + ", y: " + y + ", e: " + e + ", v: " + v + "\n");

		// Läs in kanterna
		for (int i = 0; i < e; i++) {
			int a = io.getInt();
			int b = io.getInt();
			if (edges.get(a) == null)
				edges.put(a, new ArrayList<Edge>());
			edges.get(a).add(new Edge(a, b));
		}
		return edges;
	}


	void writeFlowGraph() {
		s = v + 1;
		t = v + 2;
		StringBuilder sb = new StringBuilder();
		sb.append((v + 2) + "\n");
		sb.append(s + " " + t + "\n");
		sb.append((e + v) + "\n");
		for (int i = 1; i <= v; i++) {
			if (edges.get(i) != null) {
				ArrayList<Edge> l = edges.get(i);
				for (Edge edge : l)
					sb.append(edge.toString() + " " + edge.cap + "\n");
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


	public HashMap<Integer, ArrayList<Edge>> readMaxFlowSolution(boolean readMaxFlow) {
		// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
		// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
		// skickade iväg)
		v = io.getInt();
		s = io.getInt();
		t = io.getInt();
		if (readMaxFlow)
			totflow = io.getInt();
		e = io.getInt();
		edges = new HashMap<Integer, ArrayList<Edge>>();

		for (int i = 0; i < e; ++i) {
			int a = io.getInt();
			if (readMaxFlow && (a == s || a == t)) {	// exkludera alla kanter från källan / till sänkan
				io.getInt();
				io.getInt();
				continue;
			}
			int b = io.getInt();
			if (readMaxFlow && (b == s || b == t)) {	// exkludera alla kanter från källan / till sänkan
				io.getInt();
				continue;
				}
			int f = io.getInt();
			if (edges.get(a) == null)
				edges.put(a, new ArrayList<Edge>());
			edges.get(a).add(new Edge(a, b, 0, f));
		}
		return edges;
	}


	void writeBipMatchSolution() {
		// Skriv ut antal hörn och storleken på matchningen
		StringBuilder sb = new StringBuilder();
		sb.append(x + " " + y + "\n");
		
		e = edges.size(); // ta bort kanter till/från källa och sänka
		sb.append(e + "\n");
		for (int i = 1; i <= v; i++) {
			if (edges.get(i) != null) {
				ArrayList<Edge> l = edges.get(i);
				for (Edge edge : l)
					sb.append(edge.toString() + " " + edge.cap + "\n");
			}
		}
		String output = sb.toString();
		// Skriv ut antal hörn och kanter samt källa och sänka
		if (debug)
			System.out.println(output);
		io.println(output);
	}

	public void Reduce() {
		readBipartiteGraph();

		writeFlowGraph();

		readMaxFlowSolution(true);

		writeBipMatchSolution();

		// debugutskrift
		System.err.println("Bipred avslutar\n");

		// Kom ihåg att stänga ner Kattio-klassen
		io.close();
	}

	public static void main(String args[]) {
		BipRed br = new BipRed();
		br.Reduce();
	}
}

