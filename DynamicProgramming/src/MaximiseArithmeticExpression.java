
public class MaximiseArithmeticExpression {

	private static int[][] minTable;
	private static int[][] maxTable;

	private static int maximiseArithmeticExpression(String exp) {
		int ns = (exp.length() - 1) / 2;
		char[] symbols = new char[ns + 1];
		for (int i = 1, k = 1; i <= ns; k += 2, i++) {
			symbols[i] = exp.charAt(k);
		}
		minTable = new int[ns + 2][ns + 2];
		maxTable = new int[ns + 2][ns + 2];
		for (int i = 1, j = 0; i <= ns + 1; i++, j += 2) {
			minTable[i][i] = Character.getNumericValue(exp.charAt(j));
			maxTable[i][i] = Character.getNumericValue(exp.charAt(j));
		}

		for (int s = 1; s <= ns; s++) {
			for (int i = 1; i <= ns + 1 - s; i++) {
				int j = i + s;
				int[] minMax = getMinMax(i, j, symbols);
				minTable[i][j] = minMax[0];
				maxTable[i][j] = minMax[1];
			}
		}
		return maxTable[1][ns + 1];

	}

	private static int[] getMinMax(int i, int j, char[] symbols) {
		int[] minMax = new int[2];
		minMax[0] = Integer.MAX_VALUE;
		minMax[1] = Integer.MIN_VALUE;
		for (int k = i; k <= j - 1; k++) {
			int a = operation(minTable[i][k], minTable[k + 1][j], symbols[k]);
			int b = operation(minTable[i][k], maxTable[k + 1][j], symbols[k]);
			int c = operation(maxTable[i][k], minTable[k + 1][j], symbols[k]);
			int d = operation(maxTable[i][k], maxTable[k + 1][j], symbols[k]);
			minMax[0] = Math.min(minMax[0], Math.min(Math.min(a, b), Math.min(c, d)));
			minMax[1] = Math.max(minMax[1], Math.max(Math.max(a, b), Math.max(c, d)));
		}

		return minMax;
	}

	private static int operation(int i, int j, char charAt) {
		switch (charAt) {
		case '+':
			return i + j;
		case '-':
			return i - j;
		case '*':
			return i * j;
		case '/':
			return i / j;
		default:
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		String exp = "1+2-5*3-1";
		System.out.println(maximiseArithmeticExpression(exp));
	}

}
