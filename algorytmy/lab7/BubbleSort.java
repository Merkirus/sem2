package lab7;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i=0; i < arr.length; i++) {
            for (int right=1; right < arr.length-i; right++) {
                int left = right-1;
                if (arr[left] > arr[right]) {
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }
        }
    }
}
