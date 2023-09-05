package cw4;

import cw3.EmptyQueueException;
import cw3.FullQueueException;

import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws EmptyQueueException, FullQueueException {
        zad3<Integer> zad3 = new zad3<>();
        //LinkedList<Integer> arr = new LinkedList<>(List.of(76,71, 5, 57,12,50,20,93,20));
        //Integer[] arr = {0,2,1,0,4,4,2,1,1,1};
        Integer[] arr = {76,71, 5, 57,12,50,20,93,20,55,62,3};
        zad3.sort(arr);
    }
}
