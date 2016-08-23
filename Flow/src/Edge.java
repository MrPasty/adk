
public class Edge {
	public int a, b, cap, flow;
	public Edge rev;
	
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
		this.cap = cap;
		flow = flow > cap ? cap : flow;
		this.flow = flow;
		this.rev = rev == null ? new Edge(b, a, 0, flow, this) : rev;
	}
	
	public String toString () {
		return (a + " " + b);
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