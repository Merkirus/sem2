package lab7;

import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        final int size = 10000;
        int[] randomArr = new int[size];
        int[] min_max_sorted = new int[size];
        int[] max_min_sorted = new int[size];
        for (int i=0; i < size; i++) randomArr[i] = random.nextInt(size);
        for (int i=0; i < size; i++) min_max_sorted[i] = i;
        for (int i=0; i < size; i++) max_min_sorted[i] = size-i;

        long startTime = System.nanoTime();
        QuickSort.sort(randomArr);
        long endTime = System.nanoTime();
        System.out.println("Quick random");
        System.out.println((endTime-startTime)/1000000);
        startTime = System.nanoTime();
        QuickSort.sort(min_max_sorted);
        endTime = System.nanoTime();
        System.out.println("Quick min-max");
        System.out.println((endTime-startTime)/1000000);
        startTime = System.nanoTime();
        QuickSort.sort(max_min_sorted);
        endTime = System.nanoTime();
        System.out.println("Quick max-min");
        System.out.println((endTime-startTime)/1000000);
    }
}
