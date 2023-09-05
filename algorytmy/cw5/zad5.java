package cw5;

import java.util.Comparator;

public class zad5 {
    public zad5() {
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

        IntegerToStringExecutor executor = new IntegerToStringExecutor();
//        IntegerToStringExecutor executor1 = new IntegerToStringExecutor();
//        IntegerToStringExecutor executor2 = new IntegerToStringExecutor();

        bst.inOrderWalk(executor);
//        bst.postOrderWalk(executor1);
//        bst.preOrderWalk(executor2);
//
        System.out.println(executor.getResult());
//        System.out.println(executor1.getResult());
//        System.out.println(executor2.getResult());

        System.out.println(bst.liczbaWezlow());
        System.out.println(bst.wysokosc());
        System.out.println(bst.liczbaParzystychKluczy());
        System.out.println(bst.liczbaWezlowZJednymDzieckiem());
        System.out.println(bst.liczbaWezlowZJednymBratem());
        System.out.println(bst.findNajdluzszaSekwencja());
    }
}
