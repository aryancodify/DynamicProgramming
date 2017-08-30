public class EditDistance {
	private static void calcEditDistance(String s1, String s2) {

		int[][] subSeqs = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			subSeqs[i][0] = i;
		}

		for (int i = 0; i <= s2.length(); i++) {
			subSeqs[0][i] = i;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				subSeqs[i][j] = Math.min(Math.min(subSeqs[i - 1][j] + 1, subSeqs[i][j - 1] + 1),
						subSeqs[i - 1][j - 1] + 1);
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					subSeqs[i][j] = subSeqs[i - 1][j - 1];
				}
			}
		}
		System.out.println(subSeqs[s1.length()][s2.length()]);

	}

	public static void main(String[] args) {
		calcEditDistance("editing", "distance");
	}
}
