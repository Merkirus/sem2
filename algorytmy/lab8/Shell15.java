package lab8;

public class Shell15 {
	public static void sort(int[] arr) {
		int n = arr.length;
		int h = n/2;
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				int value = arr[i];
				int temp, j;
				for (j = i; j >= h && value < (temp=arr[j-h]); j-=h)
					arr[j] = temp;
				arr[j] = value;
			}
			h = (h*3)/4;
		}
	}
}