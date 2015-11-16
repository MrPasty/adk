import java.util.ArrayList;


public class Flow {
	private static Kattio io;
	private ArrayList<Edge> edges;
	
	Flow (Kattio io) {
		BipRed br = new BipRed();
		edges = br.readMaxFlowSolution(io, false);
		new EdmondsKarps(edges, br.s, br.t);
	}

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);
		new Flow (io);
	}

}
