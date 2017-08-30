import java.util.Arrays;

public class Pass {
	public static int solution(int[] A) {
		// write your code in Java SE 8
		int[] costArray = new int[31];
		costArray[0] = 0;
		int min = -1;
		for (int i = 1; i < costArray.length; i++) {
			if (Arrays.binarySearch(A, i) < 0) {
				costArray[i] = costArray[i - 1];
				continue;
			}
			min = costArray[i - 1] + 2;
			for (int j = Math.max(0, i - 7); j <= i - 4; j++) {
				min = Math.min(min, costArray[j] + 7);
			}
			costArray[i] = min;
		}
		return Math.min(25, costArray[costArray.length - 1]);

	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 5, 7, 23, 25, 27, 29, 30 };
		System.out.println(solution(A));
	}
}
