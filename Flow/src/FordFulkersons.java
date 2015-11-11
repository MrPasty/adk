import java.util.ArrayList;

public class FordFulkersons {
	
	ArrayList<Edge> edges;
	
	public FordFulkersons (ArrayList<Edge> edges) {
		this.edges = edges;
		
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
}
