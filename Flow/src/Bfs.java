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
	private static ArrayList<Integer> shortestPath(Graph g, int from,  int to) {
		Queue<Integer> q = new LinkedList<Integer>();
		int n = g.numVertices();
		boolean[] visited = new boolean[n];
		int[] previous = new int[visited.length];
		int start = from;
		previous[from] = -1;
		q.add(from);
		visited[from] = true;
		while(q.size()!=0) {
			from = q.poll();
			for(VertexIterator it = g.neighbors(from); it.hasNext();) {
				int index = it.next();
				if(!visited[index]) {
					visited[index] = true;
					q.add(index);
					previous[index] = from;
				}
			}
		}
		ArrayList<Integer> prevs = new ArrayList<Integer>();
		if(visited[to]){
			while(previous[to] != -1) {
				prevs.add(0, to);
				to = previous[to];
			}
			prevs.add(0, start);
		}
		return prevs;
	}
}
