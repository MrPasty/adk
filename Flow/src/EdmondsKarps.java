import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarps {
	private Kattio io;
	private BipRed br;
	private HashMap<Integer, ArrayList<Edge>> edges;
    private Edge[] pred;
	private int v, s, t, e, totflow;
	
	public EdmondsKarps () {
		io = new Kattio(System.in, System.out);
		br = new BipRed();
		edges = br.readMaxFlowSolution(false); // false because we need to find max flow
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
			if (pred[t] == null)
				break;
			
			int df = Integer.MAX_VALUE;
			for (Edge edge = pred[t]; edge != null; edge = pred[edge.a])
	            df = Math.min(df, edge.cap - edge.flow);
	        
			for (Edge edge = pred[t]; edge != null; edge = pred[edge.a]) {
				edge.flow += df;
                // add flow to reverse edge and put it in the graph
	            edge.rev.flow -= df;
                if (edge.rev.isRev) {
                    if (edges.get(edge.b) == null)
                        edges.put(edge.b, new ArrayList<Edge>());
                    edges.get(edge.b).add(edge.rev);
                }
			}
			totflow += df;
		}
    }

	public void bfs () {
		pred = new Edge[v + 1];
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        
		while (!q.isEmpty()) {
			int currentNode = q.poll();
            if (!edges.containsKey(currentNode)) break;
			for (Edge edge : edges.get(currentNode)) {
				if (pred[edge.b] == null && edge.b != s && edge.cap > edge.flow) {
					pred[edge.b] = edge;
                    q.add(edge.b);
				}
			}
		}
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
		for (int i = 1; i <= edges.size() + 1; i++) {
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
