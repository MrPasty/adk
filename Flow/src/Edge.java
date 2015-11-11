
public class Edge {
	private int a, b, cap, flow;
	private Edge rev;
	
	public Edge (int a, int b) {
		this(a, b, 1);
	}
	
	public Edge (int a, int b, int cap) {
		this(a, b, cap, 0);
	}
	
	public Edge (int a, int b, int cap, int flow) {
		this(a, b, cap, flow, new Edge(b, a));
	}
	
	public Edge (int a, int b, int cap, int flow, Edge rev) {
		this.a = a;
		this.b = b;
		this.cap = cap;
		this.flow = flow;
		cap = cap < flow ? flow : cap;
		this.rev = rev == null ? this : rev;
	}
	
	public String toString () {
		return (a + " " + b + " " + cap);
	}
	
	public Edge getRev () {
		return rev;
	}
	
	public int getCap () {
		return cap;
	}
	
	public int getFlow () {
		return flow;
	}
}