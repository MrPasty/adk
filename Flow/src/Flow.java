import java.util.ArrayList;
import java.util.HashMap;


public class Flow {
	private HashMap<Integer, ArrayList<Edge>> edges;
	
	Flow () {
		new EdmondsKarps();
	}

	public static void main(String[] args) {
		new Flow ();
	}

}
