package lab8;

import java.util.HashMap;

public class Shell34 {
	public static void sort(int[] arr) {
		int n = arr.length;
		int h = 0;
		int k = 0;
		while (!(h >= n)) {
			k++;
			h = fib(k, new HashMap<>());
		}
		while (h > 1) {
			for (int i = h; i < n; i++) {
				int left;
				for (int right = i; (left=right-h) >= h; right=left) {
					if (arr[left] > arr[right]) {
						int temp = arr[left];
						arr[left] = arr[right];
						arr[right] = temp;
					}
				}
			}
			k--;
			h = fib(k, new HashMap<>());
		}
		if (h == 1) {
			for (int i = 1; i < n; i++) {
				int value = arr[i];
				int temp, j;
				for (j = i; j > 0 && value < (temp=arr[j-1]); j--)
					arr[j] = temp;
				arr[j] = value;
			}
		}
	}
	private static int fib(int n, HashMap<Integer, Integer> memo) {
		if (memo.containsKey(n)) return memo.get(n);
		if (n <= 1)
			return n;
		memo.put(n, fib(n-1, memo) + fib(n-2, memo));
		return memo.get(n);
	}
}