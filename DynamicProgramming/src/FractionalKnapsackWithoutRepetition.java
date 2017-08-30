import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FractionalKnapsackWithoutRepetition {
	private static void getMaxValueForKnapsack(int weight, Map<Integer, Integer> weights) {
		int[][] knapArray = new int[weights.size() + 1][weight + 1];
		int i = 1;
		for (Integer w : weights.keySet()) {
			for (int j = 1; j <= weight; j++) {
				knapArray[i][j] = knapArray[i - 1][j];
				if (w <= j) {
					knapArray[i][j] = Math.max(knapArray[i][j], knapArray[i - 1][j - w] + weights.get(w));
				}
			}
			i++;
		}
		System.out.println(knapArray[weights.size()][weight]);
		List<Integer> weightList = new ArrayList<>(weights.keySet());
		Collections.reverse(weightList);
		int j = weight;
		i = weights.size();
		for (int w : weightList) {
			if (knapArray[i][j] == knapArray[i - 1][j]) {
				i = i - 1;
				continue;
			}
			if (w <= knapArray[i][j]) {
				if (knapArray[i - 1][j - w] + weights.get(w) == knapArray[i][j]) {
					System.out.println(w);
					i = i - 1;
					j = j - w;
				}
			}
		}
	}

	public static void main(String[] args) {
		Map<Integer, Integer> weights = new LinkedHashMap<>();
		weights.put(1, 1);
		weights.put(4, 4);
		weights.put(8, 8);
		getMaxValueForKnapsack(10, weights);
	}
}
