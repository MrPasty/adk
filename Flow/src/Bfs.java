import java.io.*;
import java.util.*;

public class Bfs {
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
	private static ArrayList<Edge> shortestPath(ArrayList<Edge> edges, int s,  int t) {
		Queue<Integer> q = new LinkedList<Integer>();
		int n = edges.size();
		boolean[] visited = new boolean[n];
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
