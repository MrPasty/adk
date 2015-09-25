public class Hasher {

	private static String alph = " abcdefghijklmnopqrstuvwxyzäåö";
	private static int[] m = {1, 30, 900}; // number of characters power of 0,1,2
	
	public static int hash (String s) {
		char[] ca = s.toCharArray();
		int res = 0;
		for (int i = 0; i < ca.length; i++) {
			res += alph.indexOf(ca[i]) * m[i];
		}
		return res;
	}
}
