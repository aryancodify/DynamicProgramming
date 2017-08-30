import java.util.Stack;

public class EditDistance {
	private static void longestCommonSubsequence(String s1, String s2) {

		int[][] subSeqs = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			subSeqs[i][0] = 0;
		}

		for (int i = 0; i <= s2.length(); i++) {
			subSeqs[0][i] = 0;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				subSeqs[i][j] = Math.max(subSeqs[i - 1][j], subSeqs[i][j - 1]);
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					subSeqs[i][j] = subSeqs[i - 1][j - 1] + 1;
				}
			}
		}
		Stack<Character> chars = new Stack<>();
		int i = s1.length();
		int j = s2.length();
		int value = subSeqs[i][j];
		while (value != 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				chars.push(s1.charAt(i - 1));
				i -= 1;
				j -= 1;
			} else {
				if (subSeqs[i - 1][j] >= subSeqs[i][j - 1]) {
					i -= 1;
				} else {
					j -= 1;
				}
			}
			value = subSeqs[i][j];

		}
		while (chars.size() > 0) {
			System.out.println(chars.pop());
		}
		System.out.println(subSeqs[s1.length()][s2.length()]);

	}

	public static void main(String[] args) {
		longestCommonSubsequence("abcef", "abdddddf");
	}
}
