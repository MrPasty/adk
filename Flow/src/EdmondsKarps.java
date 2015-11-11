import java.util.ArrayList;
import java.lang.Math;

public class EdmondsKarps {
	Kattio io;
	ArrayList<Edge> edges;
	ArrayList<Edge> restFlowEdges;
	
	public EdmondsKarps (ArrayList<Edge> edges) {
		
		int u, v, c, f, revf, cf; // c[u,v] �r kapaciteten fr�n u till v, f[u,v] �r fl�det, cf[u,v] �r restkapaciteten.
		this.edges = edges;
		Edge edge, p;
		
		for (int i = 0; i < edges.size(); i++) {//	for varje kant i i grafen do
			edge = edges.get(i);
			
//		    f[u,v]:=0; f[v,u]:=0 
			edge.setFlow(0);
			edge.getRev().setFlow(0);
			
//		    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u]
			edge.getRev().setCap(edge.getCap()); //	cf[u,v]:=c[u,v]
//			edge.setCap(edge.getRev().getCap()); // cf[v,u]:=c[v,u] redundant
		}
//		while det finns en stig p fr�n s till t i restfl�desgrafen do 
		while (p) { //TODO: get p through BFS, makes this Edmonds-Karps algorithm
//		    r:=min(cf[u,v]: (u,v) ing�r i p) 
			int r = min(edge.getRev().getCap(), anotherint);
//		    for varje kant (u,v) i p do 
//		         f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v] 
//		         cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]
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
	
	public void bfs () {
		//TODO: implement bfs (Dijsktra's on restflowgraph?)
	}
}
