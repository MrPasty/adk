
public class Hasher {

	public static int hash (String s) {
		char[] ca = s.toCharArray();
		int res = 0;
		for (char c : ca) {
			res = res + c;
		}
		return res;
	}
}
