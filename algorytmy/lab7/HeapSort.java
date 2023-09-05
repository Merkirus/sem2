package lab7;

public class HeapSort {
    public static void sort(int[] arr) {
        heapsort(arr, arr.length);
    }

    private static void heapsort(int[] arr, int n) {
        heapAdjustment(arr, n);
        for (int i=n-1; i>0; i--) {
            swap(arr, i, 0);
            sink(arr, 0, i);
        }
    }

    private static void heapAdjustment(int[] arr, int n) {
        for (int i=(n-1)/2; i>=0; i--)
            sink(arr, i, n);
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void sink(int[] arr, int index, int n) {
        int indexOfBigger = 2*index+1;
        if (indexOfBigger<n) {
            if (indexOfBigger+1<n && arr[indexOfBigger] < arr[indexOfBigger+1])
                indexOfBigger++;
            if (arr[index] < arr[indexOfBigger]) {
                swap(arr, index, indexOfBigger);
                sink(arr, indexOfBigger, n);
            }
        }
    }
}
