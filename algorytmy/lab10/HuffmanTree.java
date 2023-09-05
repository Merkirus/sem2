package lab10;

import cw3.*;
import cw5.BST;
import cw5.IExecutor;
import cw5.KPList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HuffmanTree {
    private class Node implements Comparable<Node> {
        Node left, right;

        Character character;
        Integer freq;

        Node(Character character, Integer freq) {
            this.character = character;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq.compareTo(o.freq);
        }
    }

    private HashMap<Character, String> code_map;
    private Node root;
    private char[] input_data;

    public HuffmanTree() {
        code_map = new HashMap<>();
        root = null;
    }

    public void generateTree(HashMap<Character, Integer> map) throws EmptyStackException, FullQueueException, FullStackException, EmptyQueueException {
        KPList<Node> kp = new KPList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            kp.enqueue(new Node(entry.getKey(), entry.getValue()));
        }
        while(kp.size() != 1) {
            Node node1 = kp.dequeue();
            Node node2 = kp.dequeue();
            Node result = new Node(null, node1.freq+node2.freq);
            result.left = node1;
            result.right = node2;
            kp.enqueue(result);
        }
        root =  kp.dequeue();
    }

    public void convertToCode() {
        convertToCode(root, "");
    }

    private void convertToCode(Node node, String value) {
        if (node.character != null) {
            code_map.put(node.character, value);
            return;
        }
        if (node.left != null) {
            String newValue = value + "0";
            convertToCode(node.left, newValue);
        }
        if (node.right != null) {
            String newValue = value + "1";
            convertToCode(node.right, newValue);
        }
    }

    public void displayCode() {
        for (Map.Entry<Character, String> entry : code_map.entrySet()) {
            System.out.println("Znak: " + entry.getKey() + " Kod: " + entry.getValue());
        }
    }

    public <R> void inOrderWalk(IExecutor<Integer,R> executor) {
        inOrderWalk(root,executor);
    }

    private <R> void inOrderWalk(Node node, IExecutor<Integer,R> executor) {
        if (node!=null) {
            inOrderWalk(node.left, executor);
            executor.execute(node.freq);
            inOrderWalk(node.right, executor);
        }
    }

    public HashMap<Character, Integer> frequency() {
        HashMap<Character, Integer> mappies = new HashMap<>();
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
            output = new StringBuilder();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                output.append(temp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[] char_output = new char[output.length()];
        output.getChars(0, output.length(), char_output, 0);
        this.input_data = char_output;
        /*Inwersja by wynik sie zgadzal z podanym na liscie*/
        char[] temp = new char[char_output.length];
        for (int i=0; i < char_output.length; i++)
            temp[char_output.length-1-i] = char_output[i];
        char_output = temp;
        for (char elem : char_output) {
            if(!mappies.containsKey(elem))
                mappies.put(elem, 1);
            else
                mappies.put(elem, mappies.get(elem)+1);
        }
        return mappies;
    }

    public void displayMap(HashMap<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            System.out.println("Klucz: " + entry.getKey() + " Częstotliwość: " + entry.getValue());
    }

    public void saveCodeToFile() {
        try {
            FileWriter fw = new FileWriter("temp.txt");
            StringBuilder sb = new StringBuilder();
            for (char elem : input_data) {
                sb.append(code_map.get(elem));
            }
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
