package lab8;

public class Shell22 {
	public static void sort(int[] arr) {
		int n = arr.length;
		int h = 0;
		int k = 0;
		while (!(h >= n)) {
			k++;
			h = ((int) Math.pow(2,k)) - 1;
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
			h = ((int) Math.pow(2,k)) - 1;
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
}