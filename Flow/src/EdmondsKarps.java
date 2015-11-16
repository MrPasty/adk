import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

public class EdmondsKarps {
	Kattio io;
	ArrayList<Edge> edges;
	int s;
	int t;
	
	
	public EdmondsKarps (ArrayList<Edge> edges, int s,  int t) {
		this.s = s;
		this.t = t;
		
		int u, v, c, f, revf, cf; // c[u,v] är kapaciteten från u till v, f[u,v] är flödet, cf[u,v] är restkapaciteten.
		Edge edge, p;
		
		for (int i = 0; i < edges.size(); i++) {//	for varje kant i i grafen do
			edge = edges.get(i);
			
//		    f[u,v]:=0; f[v,u]:=0 
			edge.setFlow(0);
			edge.getRev().setFlow(0);
			
//		    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u]
			edge.getRev().setCap(edge.getCap());
//			edge.setCap(edge.getRev().getCap()); // redundant
		}
//		while det finns en stig p från s till t i restflödesgrafen do 
		while (p) { //TODO: get p through BFS, makes this Edmonds-Karps algorithm
//		    r:=min(cf[u,v]: (u,v) ingår i p) 
			int r = min(edge.getRev().getCap(), anotherint);
//		    for varje kant (u,v) i p do 
//		         f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v] 
//		         cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]
		}
		
	}
	
//	Ford-Fulkersons algoritm i pseudokod
//
//	c[u,v] är kapaciteten från u till v, f[u,v] är flödet, cf[u,v] är restkapaciteten.
//
//	for varje kant (u,v) i grafen do 
//	    f[u,v]:=0; f[v,u]:=0 
//	    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u] 
//	while det finns en stig p från s till t i restflödesgrafen do 
//	    r:=min(cf[u,v]: (u,v) ingår i p) 
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
	private ArrayList<Edge> shortestPath() {
		Queue<Integer> q = new LinkedList<Integer>();
		int n = edges.size();
		HashMap<Integer, Integer> visited = new HashMap<>();
		Edge[] previous = new Edge[n];
		int start = s;
		previous[s] = null;
		q.add(s);
		visited[s] = true;
		while(q.size() != 0) {
			s = q.poll();
			for (int i = 0; i < edges.size(); i++) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(i);
					previous[i] = s;
				}
			}
		}
		ArrayList<Integer> prevs = new ArrayList<Integer>();
		if(visited[t]) {
			while(previous[t] != null) {
				prevs.add(0, t);
				t = previous[t];
			}
			prevs.add(0, start);
		}
		return prevs;
	}
}
