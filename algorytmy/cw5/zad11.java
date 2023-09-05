package cw5;

import java.util.ArrayList;
import java.util.Comparator;

public class zad11 {
    public zad11() {
        BST<Integer> bst = new BST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        bst.insert(20);
        bst.insert(7);
        bst.insert(10);
        bst.insert(25);
        bst.insert(4);
        bst.insert(1);
        bst.insert(2);
        bst.insert(12);
        bst.insert(30);
        bst.delete(12);
        bst.delete(1);
        bst.delete(20);
        /*Nie umiem xD*/
//        bst.printPoziomy();
    }
}
