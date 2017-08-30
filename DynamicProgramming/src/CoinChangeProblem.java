
public class CoinChangeProblem {

	private static int minCoinChange(int[] coins, int money) {
		int[] changeArray = new int[money + 1];
		changeArray[0] = 0;
		for (int i = 1; i <= money; i++) {
			int minChange = Integer.MAX_VALUE;

			for (int j = 0; j < coins.length; j++) {
				if (i >= coins[j]) {
					if (changeArray[i - coins[j]] + 1 < minChange) {
						minChange = changeArray[i - coins[j]] + 1;
					}
				}
			}
			changeArray[i] = minChange;
		}
		return changeArray[money];
	}

	public static void main(String[] args) {
		int[] coins = { 4, 2, 8 };
		System.out.println(minCoinChange(coins, 998));
	}

}
