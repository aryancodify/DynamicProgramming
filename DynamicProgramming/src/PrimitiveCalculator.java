import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PrimitiveCalculator {

	static int[] out;
	static int[] res;

	private static List<Integer> optimal_sequence(int n) {
		List<Integer> sequence = new ArrayList<Integer>();
		while (n >= 1) {
			sequence.add(n);
			if (n % 3 == 0) {
				n /= 3;
			} else if (n % 2 == 0) {
				n /= 2;
			} else {
				n -= 1;
			}
		}
		Collections.reverse(sequence);
		return sequence;
	}

	private static int dynamicOptimalSequenceRecursive(int n) {

		if (n == 1) {
			return 0;
		}
		if (out[n] != 0) {
			return out[n];
		}
		if (n % 3 == 0 && n % 2 != 0) {
			int div3 = dynamicOptimalSequenceRecursive(n / 3);
			int sub1 = dynamicOptimalSequenceRecursive(n - 1);
			res[n] = (div3 <= sub1 ? 3 : 1);
			return out[n] = 1 + Math.min(div3, sub1);
		}
		if (n % 3 != 0 && n % 2 == 0) {
			int div2 = dynamicOptimalSequenceRecursive(n / 2);
			int sub1 = dynamicOptimalSequenceRecursive(n - 1);
			res[n] = (div2 <= sub1 ? 2 : 1);
			return out[n] = 1 + Math.min(div2, sub1);
		}
		if (n % 3 == 0 && n % 2 == 0) {
			int div3 = dynamicOptimalSequenceRecursive(n / 3);
			int div2 = dynamicOptimalSequenceRecursive(n / 2);
			int sub1 = dynamicOptimalSequenceRecursive(n - 1);
			res[n] = div3 <= div2 ? (div3 <= sub1 ? 3 : 1) : div2 <= sub1 ? 2 : 1;
			return out[n] = 1 + Math.min(div3, Math.min(div2, sub1));
		}
		res[n] = 1;
		return out[n] = 1 + dynamicOptimalSequenceRecursive(n - 1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		out = new int[n + 1];
		res = new int[n + 1];
		dynamicOptimalSequence(n);
	}

	private static void dynamicOptimalSequence(int n) {
		int i = 1;
		out[i] = 0;
		res[i] = 0;
		for (i = 2; i <= n; i++) {
			out[i] = out[i - 1] + 1;
			res[i] = 1;
			if (i % 2 == 0) {
				if (out[i / 2] + 1 < out[i]) {
					res[i] = 2;
				}
				out[i] = Math.min(out[i / 2] + 1, out[i]);
			}
			if (i % 3 == 0) {
				if (out[i / 3] + 1 < out[i]) {
					res[i] = 3;
				}
				out[i] = Math.min(out[i / 3] + 1, out[i]);
			}
		}
		System.out.println(out[n]);
		Stack<Integer> nums = new Stack<Integer>();
		nums.push(n);
		while (n != 1) {
			n = res[n] == 1 ? n - 1 : res[n] == 2 ? n / 2 : n / 3;
			nums.push(n);
		}
		while (nums.size() > 0) {
			System.out.print(nums.pop() + " ");
		}
	}
}
