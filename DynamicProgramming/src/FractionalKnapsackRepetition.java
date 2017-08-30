import java.util.HashMap;
import java.util.Map;

public class FractionalKnapsackRepetition {

	private static void getMaxValueForKnapsack(int weight, Map<Integer, Integer> weights) {
		int[] knapArray = new int[weight + 1];
		knapArray[0] = 0;
		for (int i = 1; i <= weight; i++) {
			for (Integer w : weights.keySet()) {
				if (w <= i) {
					knapArray[i] = Math.max(knapArray[i], knapArray[i - w] + weights.get(w));
				}
			}
		}
		System.out.println(knapArray[weight]);
		int i = weight;
		while (i != 0) {
			for (int w : weights.keySet()) {
				if (knapArray[i - w] + weights.get(w) == knapArray[i]) {
					System.out.println(w);
					i = i - w;
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		Map<Integer, Integer> weights = new HashMap<>();
		weights.put(2, 9);
		weights.put(3, 14);
		weights.put(4, 16);
		weights.put(6, 30);
		getMaxValueForKnapsack(10, weights);
	}

}
