
public class Edge {
	int a, b, cap, flow;
	Edge rev;
	
	public Edge (int a, int b) {
		new Edge(a, b, 1);
	}
	
	public Edge (int a, int b, int cap) {
		new Edge(a, b, cap, 0);
	}
	
	public Edge (int a, int b, int cap, int flow) {
		new Edge(a, b, cap, flow, this);
	}
	
	public Edge (int a, int b, int cap, int flow, Edge rev) {
		this.a = a;
		this.b = b;
		this.cap = cap;
		this.flow = flow;
		this.rev = rev;		
	}
}