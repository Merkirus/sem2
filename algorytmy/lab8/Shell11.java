package lab8;

public class Shell11 {
    public static void sort(int[] arr) {
        int n = arr.length;
        int h = 1;
        while (!(h >= n)) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int value = arr[i];
                int temp;
                int j;
                for (j = i; j >= h && value < (temp=arr[j - h]); j -= h)
                    arr[j] = temp;
                arr[j] = value;
            }
            h = (h-1)/3;
        }
    }
}
