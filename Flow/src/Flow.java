import java.util.ArrayList;


public class Flow {
	private static Kattio io;
	private ArrayList<Edge> edges;
	
	Flow (Kattio io) {
		BipRed br = new BipRed();
		br.readBipartiteGraph(io);
		new EdmondsKarps(edges);
	}

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);
		new Flow (io);
	}

}
