package lab8;

public class Shell33 {
	public static void sort(int[] arr) {
				int n = arr.length;
		int h = 0;
		int k = 0;
		while (!(h >= n)) {
			k++;
			h = ((int) Math.pow(2,k)) + 1;
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
			h = ((int) Math.pow(2,k)) + 1;
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
}