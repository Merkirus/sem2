package lab8;

public class Shell13 {
	public static void sort(int[] arr) {
		int n = arr.length;
		int h = 0;
		int k = 0;
		while (!(h >= n)) {
			k++;
			h = ((int) Math.pow(2,k)) - 1;
		}
		while (h>=1) {
			for (int i = h; i < n; i++) {
  	 			int value = arr[i];
  	 			int temp, j;
  	 			for (j = i; j >= h && value < (temp=arr[j-h]); j-=h)
  	 				arr[j] = temp;
  	 			arr[j] = value;
			}
			k--;
			h = ((int) Math.pow(2,k)) - 1;
		}
	}
}