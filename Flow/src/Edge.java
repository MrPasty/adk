
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
		this(a, b, cap, flow, null);
	}
	
	public Edge (int a, int b, int cap, int flow, Edge rev) {
		this.a = a;
		this.b = b;
		cap = cap < flow ? flow : cap;
		this.cap = cap;
		this.flow = flow;
		this.rev = rev == null ? new Edge(b, a, this.cap - flow, flow, this) : rev;
	}
	
	public String toString () {
		return (a + " " + b + " " + cap);
	}
	
	public int getA () {
		return a;
	}
	
	public int getB () {
		return b;
	}
	
	public Edge getRev () {
		return rev;
	}
	
	public int getCap () {
		return cap;
	}
	
	public void setCap (int cap) {
		this.cap = cap;
	}
	
	public int getFlow () {
		return flow;
	}
	
	public void setFlow (int flow) {
		this.flow = flow;
	}
}