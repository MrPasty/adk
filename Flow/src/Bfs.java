import java.io.*;
import java.util.*;

public class Bfs {
	private final static String NAME = Bfs.class.getName();

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.printf("Usage: java %s FILE FROM TO%n", Bfs.class.getSimpleName());
			System.exit(1); // Unix error handling

		}
		final String from = args[1];
		final String to = args[2];
		final String fileName = args[0];
		//int errCode = 0; // Unix error handling
		// FileReader uses "the default character encoding".
		// To specify an encoding, use this code instead:
		//new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

		// This "try-with-resource" statement automatically calls file.close()
		// just before leaving the try block.
		long a = 0, b = 0;
		Graph g = null;
		try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
			a = System.nanoTime();
			g = parse(file);
			b = System.nanoTime() - a;
			System.out.println("Elapsed: " + b/1000000F);
		} catch (IOException e) {
			System.err.printf("%s: %s%n", NAME, e);
			//	errCode = 1;
		}  
//		catch (ParsingErrorException e) {
//
//			e.printStackTrace();
		} finally {
		}
		ArrayList<Integer> ansur = shortestPath(g, Integer.parseInt(from), Integer.parseInt(to));
		System.out.println(ansur);
	}

	/** 
	 * Splits the file around the specified regex to attain only integers.
	 * Returns the Graph specified by the file
	 * distances.txt
	 */
	private static Graph parse(BufferedReader in) throws IOException {
		ArrayList<Integer> graphInputs = new ArrayList<Integer>();
		String line;
		boolean first = true;
		int rowcounter = 0;
		int parseCount= 0;
		while ((line = in.readLine()) != null) {
			String[] temp = line.replaceAll("\\/\\/.*", "").split("\\s+");
			int intCount= 0;
			rowcounter++;
			for(int j = 0; j < temp.length; j++){
				if(temp[j].isEmpty()) continue;
				int parsed = 0;
				try {
					parsed = Integer.parseInt(temp[j]);
				} catch(NumberFormatException e) {
//					throw new ParsingErrorException("Value exceeds maximum possible integer \nRow: " + rowcounter);
				}
				if(parsed < 0){
//					throw new ParsingErrorException("Input values must be positive \nRow: " + rowcounter);
				}
				graphInputs.add(parsed);	
				parseCount++; // keep track of first
				intCount++; //keep track of number of ints at each string coversion (set to zero new line)
			}
			if(parseCount > 1 && first)
//				throw new ParsingErrorException("First row can only have one number\nRow: " + rowcounter);
			if(parseCount == 1)
				first = false;
			if(parseCount > 1 && intCount != 3 && temp.length > 1){
//				throw new ParsingErrorException("Each row must have three integers \nRow: " + rowcounter);
			}
		}

		Graph graph = new HashGraph(graphInputs.get(0));
		for (int i = 1; i < graphInputs.size(); i+=3){
			if(graphInputs.get(i) >= graphInputs.get(0) || graphInputs.get(i+1) >= graphInputs.get(0)){
//				throw new ParsingErrorException("Node is out of range");
			}
			graph.addBi(graphInputs.get(i), graphInputs.get(i+1), graphInputs.get(i+2));
		}
		return graph;
	}

	/**
	 * ShortestPath from one specified node to another.
	 * traverses the graph with a bfs and builds a previous nodes array.
	 * which is then used to create a arrayList for the shortest path
	 * returns an empty array if there's no path 
	 * 
	 * @param g graph to traverse
	 * @param from starting node
	 * @param to end node
	 * @return ArrayList containing the shortest path
	 */
	private static ArrayList<Integer> shortestPath(Graph g, int from,  int to){
		Queue<Integer> q = new LinkedList<Integer>();
		int n = g.numVertices();
		boolean[] visited = new boolean[n];
		int[] previous = new int[visited.length];
		int start = from;
		previous[from] = -1;
		q.add(from);
		visited[from]=true;
		while(q.size()!=0){
			from = q.poll();
			for(VertexIterator it = g.neighbors(from); it.hasNext();){
				int index = it.next();
				if(!visited[index]){
					visited[index]=true;
					q.add(index);
					previous[index] = from; 
				}
			}
		}
		ArrayList<Integer> prevs = new ArrayList<Integer>();
		if(visited[to]){
			while(previous[to] != -1){
				prevs.add(0, to);
				to = previous[to];
			}
			prevs.add(0, start);
		}
		return prevs;
	}
}
