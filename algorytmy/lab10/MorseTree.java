package lab10;

import cw3.*;
import cw5.BST;
import cw5.IExecutor;
import cw5.KPList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class MorseTree {

    private class Node implements Comparable<Node> {

        Node left, right;

        String character;
        Node(String character) {
            this.character = character;
        }

        @Override
        public int compareTo(Node o) {
            return this.character.compareTo(o.character);
        }

    }

    private HashMap<Character, String> code_map;
    HashMap<String, Character> translate;
    HashMap<Character, String> translateInverse;
    ArrayQueue<String> output_array;
    private Node root;
    private char[] input_data;
    StringBuilder output;

    public MorseTree() {
        translate = new HashMap<>();
        translateInverse = new HashMap<>();
        output_array = new ArrayQueue<>();
        translate.put(".", 'e');
        translate.put("-", 't');
        translate.put("..", 'i');
        translate.put(".-", 'a');
        translate.put("-.", 'n');
        translate.put("--", 'm');
        translate.put("...", 's');
        translate.put("..-", 'u');
        translate.put(".-.", 'r');
        translate.put(".--", 'w');
        translate.put("-..", 'd');
        translate.put("-.-", 'k');
        translate.put("--.", 'g');
        translate.put("---", 'o');
        translate.put("....", 'h');
        translate.put("...-", 'v');
        translate.put("..-.", 'f');
        translate.put(".-..", 'l');
        translate.put(".--.", 'p');
        translate.put(".---", 'j');
        translate.put("-...", 'b');
        translate.put("-..-", 'x');
        translate.put("-.-.", 'c');
        translate.put("-.--", 'y');
        translate.put("--..", 'z');
        translate.put("--.-", 'q');
        translateInverse.put('e',".");
        translateInverse.put('t',"-");
        translateInverse.put('i',"..");
        translateInverse.put('a',".-");
        translateInverse.put('n',"-.");
        translateInverse.put('m',"--");
        translateInverse.put('s',"...");
        translateInverse.put('u',"..-");
        translateInverse.put('r',".-.");
        translateInverse.put('w',".--");
        translateInverse.put('d',"-..");
        translateInverse.put('k',"-.-");
        translateInverse.put('g',"--.");
        translateInverse.put('o',"---");
        translateInverse.put('h',"....");
        translateInverse.put('v',"...-");
        translateInverse.put('f',"..-.");
        translateInverse.put('l',".-..");
        translateInverse.put('p',".--.");
        translateInverse.put('j',".---");
        translateInverse.put('b',"-...");
        translateInverse.put('x',"-..-");
        translateInverse.put('c',"-.-.");
        translateInverse.put('y',"-.--");
        translateInverse.put('z',"--..");
        translateInverse.put('q',"--.-");
        root = null;
        output = new StringBuilder();
    }

    public void generateTree() throws EmptyStackException, FullQueueException, FullStackException, EmptyQueueException {
        KPList<Node> kp = new KPList<>();
        StringBuilder temp = new StringBuilder();
        for (char c : input_data) {
            if (c == '\\') {
                kp.enqueue(new Node(temp.toString()));
                output_array.enqueue(temp.toString());
                temp = new StringBuilder();
            } else {
                temp.append(c);
            }
        }
        root = new Node("");
        while (kp.size() != 0)
            insert(kp.dequeue().character);
    }

    public void insert(String elem) {
        root = insert(root, elem);
    }

    private Node insert(Node node, String elem) {
        if (node==null)
            node = new Node(translate.get(elem).toString());
        else {
            int cmp = elem.compareTo(node.character);
            if (cmp < 0)
                node.left = insert(node.left, elem);
            else if (cmp > 0)
                node.right = insert(node.right, elem);
            else throw new NoSuchElementException();
        }
        return node;
    }

    public void convertToCode() throws EmptyQueueException {
        int size=output_array.size();
        for (int i=0; i < size; i++)
        {
            output.append(translate.get(output_array.dequeue()));
        }
    }

    public HashMap<Character, Integer> translateMorse() {
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
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
        if (char_output[0] != '.' && char_output[0] != '-')
        {
            for (int i=0; i < char_output.length; i++) {
                this.output.append(translateInverse.get(char_output[i]));
            }
        }
        this.input_data = char_output;
        return null;
    }

    public void saveCodeToFile() throws EmptyQueueException {
        convertToCode();
        try {
            FileWriter fw = new FileWriter("temp.txt");
            fw.write(output.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
