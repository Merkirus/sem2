package lab7;

public class InsertSort {
    public static void sort(int[] arr) {
        for (int i=1; i < arr.length; i++) {
            int value = arr[i];
            int temp;
            int j;
            for (j=i; j > 0 && value < (temp=arr[j-1]); j--)
                arr[j] = temp;
            arr[j]=value;
        }
    }
}
