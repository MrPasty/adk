import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

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
		
//		int u, v, c, f, revf, cf; c[u,v] �r kapaciteten fr�n u till v, f[u,v] �r fl�det, cf[u,v] �r restkapaciteten.
		ArrayList<Edge> edgeList, p;
		Edge rev;
		
		for (int i = 1; i <= v; i++) {//	for varje kant i i grafen do
			edgeList = edges.get(i);
			
			for(Edge e : edgeList) {
//			    f[u,v]:=0; f[v,u]:=0 
				e.setFlow(0);
				rev = e.getRev();
				rev.setFlow(0);
				
//			    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u]]
				rev.setCap(e.getCap());
//				edge.setCap(edge.getRev().getCap()); // redundant
			} 
		}
		bfs();
//		while det finns en stig p fr�n s till t i restfl�desgrafen do 
//		while (p) { //TODO: get p through BFS, makes this Edmonds-Karps algorithm
//		    r:=min(cf[u,v]: (u,v) ing�r i p) 
//			int r = min(edge.getRev().getCap(), anotherint);
//		    for varje kant (u,v) i p do 
//		         f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v] 
//		         cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]
//		}
		
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
	
	/**
	 * ShortestPath from one specified node to another.
	 * traverses the graph with a bfs and builds a previous nodes array.
	 * which is then used to create a arrayList for the shortest path
	 * returns an empty array if there's no path.
	 * 
	 * @param g graph to traverse
	 * @param from starting node
	 * @param to end node
	 * @return ArrayList containing the shortest path
	 */
	private void bfs() {
		int b = -1;
		HashMap<Integer, ArrayList<Edge>> residual = new HashMap<> (edges.size());
		
		ArrayList<Edge> current = null;
	
		for (int i = s; i <= t; i++) {
			if (edges.get(i) != null) {
				current = edges.get(i);
				for(Edge e : current) {
					b = e.getB ();
					if (residual.get(b) == null)
						residual.put(b, new ArrayList<Edge> ());
					residual.get(b).add(e.getRev());
				}
			}
		}
		//needed?
//		ArrayList<Integer> prevs = new ArrayList<Integer>();
//		if(visited[t]) {
//			while(previous[t] != null) {
//				prevs.add(0, t);
//				t = previous[t];
//			}
//			prevs.add(0, start);
//		}
//		return prevs;
	}
}
