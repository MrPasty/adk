import java.util.ArrayList;
import java.util.HashMap;

public class EdmondsKarps {
	BipRed br;
	private HashMap<Integer, ArrayList<Edge>> edges;
	private int v, s, t, totflow;
	
	public EdmondsKarps () {
		br = new BipRed();
		edges = br.readMaxFlowSolution(false);
		v = br.v;
		s = br.s;
		t = br.t;
		
		totflow = bfs();
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
	
	private int bfs() {
		int maxFlow = 0;
		int b = -1;
		int minCap = Integer.MAX_VALUE;
		HashMap<Integer, ArrayList<Edge>> residual = new HashMap<> (edges.size());
		
		ArrayList<Edge> current = null;
	
		for (int i = s; i <= t; i++) {
			if (edges.get(i) != null) {
				current = edges.get(i);
				for (Edge e : current) {
					b = e.b;
					if (residual.get(b) == null)
						residual.put(b, new ArrayList<Edge> ());
					minCap = minCap < e.rev.cap ? minCap : e.rev.cap;
					e.rev.setCap(minCap);
					residual.get(b).add(e.rev);
				}
			}
		}
		for (int i = t; i >= s; i--) {
			if (residual.get(i) != null) {
				current = residual.get(i);
				for(Edge r : current) {
					r.flow += minCap;
					r.rev.flow -= minCap;
				}
				maxFlow =+ minCap;
			}
		}
		return maxFlow;
	}
}
