package lab8;

import java.util.HashMap;

public class Shell24 {
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
				int value = arr[i];
				int temp, j;
				for (j = i; j >= h && value < (temp = arr[j-h]); j-=h)
					arr[j] = temp;
				arr[j] = value;
			}
			k--;
			h = fib(k, new HashMap<>());
		}
		if (h == 1) {
			for (int i = 0; i < n; i++) {
				for (int right = 1; right < n-i; right++) {
					int left = right - 1;
					if (arr[left] > arr[right]) {
						int temp = arr[left];
						arr[left] = arr[right];
						arr[right] = temp;
					}
				}
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