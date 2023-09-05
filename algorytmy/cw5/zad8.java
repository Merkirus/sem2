package cw5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class zad8 {
    public zad8() {
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
        bst.preOrderWalk(executor);
        try {
            FileWriter fw = new FileWriter("temp.txt");
            fw.write(executor.getResult());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> arr = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
            String temp;
            while ((temp = br.readLine()) != null) {
                arr.add(temp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String[]> finalOutput = new ArrayList<>();
        for (String string : arr) {
            finalOutput.add(string.split("; "));
        }
        ArrayList<Integer> integerOutput = new ArrayList<>();
        for (String[] line : finalOutput) {
            for (String word : line) {
                integerOutput.add(Integer.parseInt(word));
            }
        }
        BST<Integer> bst1 = new BST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (Integer i : integerOutput)
            bst1.insert(i);
        IntegerToStringExecutor executor1 = new IntegerToStringExecutor();
        bst1.preOrderWalk(executor1);
        System.out.println(executor1.getResult());
    }
}
