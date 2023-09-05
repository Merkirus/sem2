package lab7;

public class MergeSort {
    public static void sort(int[] arr) {
        System.arraycopy(mergesort(arr, 0, arr.length-1), 0, arr, 0, arr.length);
    }

    private static int[] mergesort(int[] arr, int begin, int end) {
        if (begin==end) {
            int[] result = {arr[begin]};
            return result;
        }
        int split = begin + (end - begin) / 2;
        return merge(mergesort(arr, begin, split), mergesort(arr, split+1, end));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int curr = 0;
        int currLeft = -1;
        int currRight = -1;
        int elemLeft = 0;
        int elemRight = 0;
        if (++currLeft < left.length) {
            elemLeft = left[currLeft];
        }
        if (++currRight < right.length) {
            elemRight = right[currRight];
        }
        while (currLeft < left.length && currRight < right.length) {
            if (elemLeft <= elemRight) {
                result[curr] = elemLeft;
                curr++;
                if (++currLeft < left.length) {
                    elemLeft = left[currLeft];
                } else {
                    result[curr] = elemRight;
                    curr++;
                }
            }
            else {
                result[curr] = elemRight;
                curr++;
                if (++currRight < right.length) {
                    elemRight = right[currRight];
                } else {
                    result[curr] = elemLeft;
                    curr++;
                }
            }
        }
        while (++currLeft < left.length) {
            elemLeft = left[currLeft];
            result[curr] = elemLeft;
            curr++;
        }
        while (++currRight < right.length) {
            elemRight = right[currRight];
            result[curr] = elemRight;
            curr++;
        }
        return result;
    }
}
