import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarps {
	private Kattio io;
	private BipRed br;
	private HashMap<Integer, ArrayList<Edge>> edges;
	private HashMap<Integer, ArrayList<Edge>> residual;
    private Edge[] parents;
	private int v, s, t, e, m, totflow;
	
	public EdmondsKarps () {
		io = new Kattio(System.in, System.out);
		br = new BipRed();
		edges = br.readMaxFlowSolution(false); // false because we need to find max flow
		residual = br.getResidual();
		v = br.v; // antal hörn
		s = br.s; // källa
		t = br.t; // sänka
		e = br.e; // antal kanter

        ek();
		writeMaxFlowGraph();
	}

    public void ek() {
		while (true){
			bfs ();
			if (m == 0)
				break;
			totflow += m;
			int i = t;
			while (i != s) {
				Edge edge = parents[i];
				edge.flow += m;
				edge.setResidual(m);
				i = edge.getA();
			}
		}
    }

	public void bfs () {
		parents = new Edge[v + 1];
        int[] cap = new int[v + 1];
        cap[s] = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
		while (!q.isEmpty()) {
			int currentNode = q.poll();
			for (Edge edge : edges.get(currentNode)) {
				int res = edge.getResidual();
				if (res > 0 && parents[edge.getB()] == null && edge.getB() != s) {
					parents[edge.getB()] = edge;
					cap[edge.getB()] = Math.min(cap[edge.getA ()], res);
					if (edge.getB() != t)
						q.add(edge.getB());
					else {
						m = cap[t];
						return;
					}
				}
			}
		}
		m = 0;
	}
	
//	Ford-Fulkersons algoritm i pseudokod
//
//	c[u,v] �r kapaciteten fr�n u till v, f[u,v] �r fl�det, cf[u,v] �r restkapaciteten.
//
//	for varje kant (u,v) i grafen do 
//	    f[u,v]:=0; f[v,u]:=0 
//	    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u] 
//	while det finns en stig p fr�n s till t i restfl�desgrafen do 
//	    r:=min(cf[u,v]: (u,v) ing�r i p) 
//	    for varje kant (u,v) i p do 
//	         f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v] 
//	         cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]

	public void writeMaxFlowGraph() {
		StringBuilder sb = new StringBuilder();
		e = 0;
		io.println(v);
		io.println(s + " " + t + " " + totflow);
		for (int i = s; i <= edges.size() + 1; i++) {
			if (edges.get(i) != null) {
				ArrayList<Edge> l = edges.get(i);
				for (Edge edge : l) {
                    if (edge.flow > 0) {
                        sb.append(edge.toString() + " " + edge.flow + "\n");
                        e++;
                    }
                }
			}
		}
        io.println(e);
		String output = sb.toString();
		
		// Skriv ut antal hörn och kanter samt källa och sänka
		io.println(output);
		
		// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
		io.flush();
	}
}
