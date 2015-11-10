
public class Edge {
	int a, b, cap, flow;
	
	public Edge (int a, int b) {
		new Edge(a, b, 1);
	}
	
	public Edge (int a, int b, int cap) {
		new Edge(a, b, cap, 0);
	}
	
	public Edge (int a, int b, int cap, int flow) {
		this.a = a;
		this.b = b;
		this.cap = cap;
		this.flow = flow;
	}
}