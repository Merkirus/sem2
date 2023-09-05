package lab7;

public class SelectSort {
    public static void sort(int[] arr) {
        int size = arr.length;
        for (int left=0; left < size; left++) {
            int smallest = left;
            for (int right = left+1; right < size; right++) {
                if (arr[right] < arr[smallest])
                    smallest = right;
            }
            if (smallest != left) {
                int temp = arr[smallest];
                arr[smallest] = arr[left];
                arr[left] = temp;
            }
        }
    }
}
