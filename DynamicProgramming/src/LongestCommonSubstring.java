import java.util.Stack;

public class LongestCommonSubstring {
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
				subSeqs[i][j] = 0;
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					subSeqs[i][j] = subSeqs[i - 1][j - 1] + 1;
				}
			}
		}
		int max = 0;
		int maxI = 0;
		int maxJ = 0;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (subSeqs[i][j] > max) {
					max = subSeqs[i][j];
					maxI = i;
					maxJ = j;
				}
			}
		}

		Stack<Character> chars = new Stack<>();
		int i = s1.length();
		int j = s2.length();
		int value = subSeqs[i][j];
		while (subSeqs[maxI][maxJ] != 0) {
			chars.push(s1.charAt(maxI - 1));
			maxI -= 1;
			maxJ -= 1;
		}
		while (chars.size() > 0) {
			System.out.println(chars.pop());
		}
		System.out.println(max);

	}

	public static void main(String[] args) {
		longestCommonSubsequence("abcef", "acef");
	}

}
