package cw5;

import cw3.ArrayQueue;
import cw3.EmptyQueueException;
import cw3.FullQueueException;

import java.util.*;

public class BST<T> {

    private class Node {
        Node left, right;
        T value;
        Node(T obj) {
            value = obj;
        }
        Node(T obj, Node leftNode, Node rightNode) {
            value = obj;
            left = leftNode;
            right = rightNode;
        }
    }

    private Comparator<T> comparator;
    private Node root;

    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    public T find(T elem) {
        Node node = search(elem);
        return node==null ? null :node.value;
    }

    private Node search(T elem) {
        Node node = root;
        int cmp = 0;
        while (node!=null && (cmp=comparator.compare(elem, node.value))!=0)
            node = cmp < 0 ? node.left : node.right;
        return node;
    }

    public T getMin() {
        if (root==null) throw new NoSuchElementException();
        Node node = getMin(root);
        return node.value;
    }

    private Node getMin(Node node) {
        assert(node!=null);
        while (node.left!=null)
            node = node.left;
        return node;
    }

    public T getMax() {
        if (root==null) throw new NoSuchElementException();
        Node node = getMax(root);
        return node.value;
    }

    private Node getMax(Node node) {
        assert(node!=null);
        while (node.right != null)
            node = node.right;
        return node;
    }

    public T successor(T elem) {
        Node succNode = successorNode(root, elem);
        return succNode == null ? null : succNode.value;
    }

    private Node successorNode(Node node, T elem) {
        if (node == null) throw new NoSuchElementException();
        int cmp = comparator.compare(elem, node.value);
        if (cmp == 0 ) {
            if (node.right != null)
                return getMin(node.right);
            else return null;
        } else if (cmp < 0) {
            Node retNode = successorNode(node.left, elem);
            return retNode == null ? node : retNode;
        } else {
            return successorNode(node.right, elem);
        }
    }

    public void insert(T elem) {
        root = insert(root, elem);
    }

    private Node insert(Node node, T elem) {
        if (node==null)
            node = new Node(elem);
        else {
            int cmp = comparator.compare(elem, node.value);
            if (cmp < 0)
                node.left = insert(node.left, elem);
            else if (cmp > 0)
                node.right = insert(node.right, elem);
            else throw new NoSuchElementException();
        }
        return node;
    }

    public void delete(T elem) {
        root = delete(root, elem);
    }

    private Node delete(Node node, T elem) {
        if (node == null) throw new NoSuchElementException();
        else {
            int cmp = comparator.compare(elem, node.value);
            if (cmp < 0)
                node.left = delete(node.left, elem);
            else if (cmp > 0)
                node.right = delete(node.right, elem);
            else if (node.left!=null && node.right!=null)
                node.right = detachMin(node, node.right);
            else node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    private Node detachMin(Node del, Node node) {
        if (node.left != null)
            node.left = detachMin(del, node.left);
        else {
            del.value = node.value;
            node = node.right;
        }
        return node;
    }

    public int liczbaWezlow() {
        return liczbaWezlow(root);
    }

    private int liczbaWezlow(Node node) {
        if (node == null) return 0;
        return liczbaWezlow(node.left) + liczbaWezlow(node.right) + 1;
    }

    public int libczaLisci() {
        return liczbaLisci(root);
    }

    private int liczbaLisci(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return liczbaLisci(node.left) + liczbaLisci(node.right);
    }

    public int liczbaParzystychKluczy() {
        return liczbaParzystychKluczy(root);
    }

    private int liczbaParzystychKluczy(Node node) {
        if (node == null) return 0;
        if (((int) node.value) % 2 != 0)
            return liczbaParzystychKluczy(node.left) + liczbaParzystychKluczy(node.right);
        return liczbaParzystychKluczy(node.left) + liczbaParzystychKluczy(node.right) + 1;
    }

    public int liczbaWezlowZJednymDzieckiem() {
        return liczbaWezlowZJednymDzieckiem(root);
    }

    private int liczbaWezlowZJednymDzieckiem(Node node) {
        if (node == null) return 0;
        if ((node.left == null || node.right == null) && !(node.left == null && node.right == null))
            return liczbaWezlowZJednymDzieckiem(node.right) + liczbaWezlowZJednymDzieckiem(node.left) + 1;
        return liczbaWezlowZJednymDzieckiem(node.right) + liczbaWezlowZJednymDzieckiem(node.left);
    }

    public int wysokosc() {
        if (root == null) return -1;
        return wysokosc(root, 0);
    }

    private int wysokosc(Node node, int curr_height) {
        if (node == null) return curr_height-1;
        int left = wysokosc(node.left, curr_height+1);
        int right = wysokosc(node.right, curr_height+1);
        return Math.max(left, right);
    }

    public int liczbaWezlowZJednymBratem() {
        return liczbaWezlowZJednymBratem(root);
    }

    private int liczbaWezlowZJednymBratem(Node node) {
        ArrayList<ArrayList<Node>> container = new ArrayList<>();
        for (int i = 0; i < wysokosc()+1; i++)
            container.add(new ArrayList<Node>());
        wysokoscDlaBraci(root, 0, container);
        int sum = 0;
        for (ArrayList<Node> nodes : container) {
            if (nodes.size() == 2)
                sum++;
        }
        return sum;
    }

    private int wysokoscDlaBraci(Node node, int curr_height, ArrayList<ArrayList<Node>> arr) {
        if (node == null) return curr_height-1;
        arr.get(curr_height).add(node);
        int left = wysokoscDlaBraci(node.left, curr_height+1, arr);
        int right = wysokoscDlaBraci(node.right, curr_height+1, arr);
        if (left >= right)
            return left;
        else
            return right;
    }

    public T findNajdluzszaSekwencja() {
        HashMap<Node, Integer> map = new HashMap<>();
        findNajdluzszaSekwencja(root, map);
        int max = 0;
        Node longestNode = null;
        for (Map.Entry<Node, Integer> entry : map.entrySet()) {
            System.out.println("Klucz: " + entry.getKey().value + " Wartość: " + entry.getValue());
            if (entry.getValue() > max) {
                max = entry.getValue();
                longestNode = entry.getKey();
            }
        }
        assert longestNode != null;
        return longestNode.value;
    }

    private int findNajdluzszaSekwencja(Node node, HashMap<Node, Integer> memo) {
        if (node.left == null && node.right == null) {
            memo.put(node, 0);
            return 0;
        }
        if (node.left != null && node.right != null) {
            memo.put(node, 0);
            findNajdluzszaSekwencja(node.left, memo);
            findNajdluzszaSekwencja(node.right, memo);
            return 0;
        }
        int path = 0;
        if (node.left == null) {
            path = findNajdluzszaSekwencja(node.right, memo) + 1;
        } else {
            path = findNajdluzszaSekwencja(node.left, memo) + 1;
        }
        memo.put(node, path);
        return path;
    }

    public void tablicaPozimow() {
        ArrayList<Node> finalArray = pomocPoziomy(root);
        for (Node nodus : finalArray) {
            System.out.print(nodus.value + ", ");
        }
    }

    private ArrayList<Node> pomocPoziomy(Node node) {
        ArrayList<ArrayList<Node>> container = new ArrayList<>();
        for (int i = 0; i < wysokosc()+1; i++)
            container.add(new ArrayList<Node>());
        wysokoscDlaBraci(root, 0, container);
        ArrayList<Node> finalArray = new ArrayList<>();
        for (ArrayList<Node> arr : container) {
            finalArray.addAll(arr);
        }
        return finalArray;
    }

    private ArrayList<ArrayList<Node>> getPoziomy() {
        return pomocPoziomy2(root);
    }

    private ArrayList<ArrayList<Node>> pomocPoziomy2(Node node) {
        ArrayList<ArrayList<Node>> container = new ArrayList<>();
        for (int i = 0; i < wysokosc()+1; i++)
            container.add(new ArrayList<Node>());
        wysokoscDlaBraci(root, 0, container);
        return container;
    }

    public void przejdzWszerz() throws FullQueueException, EmptyQueueException {
        przejdzWszerz(root);
    }

    private void przejdzWszerz(Node node) throws FullQueueException, EmptyQueueException {
        ArrayQueue<Node> queue = new ArrayQueue<>();
        queue.enqueue(node);
        System.out.println("Przechodzenie wszerz");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i < size; i++) {
                Node current = queue.dequeue();
                System.out.print(current.value.toString() + " | ");
                if (current.left != null)
                    queue.enqueue(current.left);
                if (current.right != null)
                    queue.enqueue(current.right);
            }
            System.out.println();
        }
        System.out.println("Przechodzenie wszerz - koniec");
    }

    public <R> void inOrderWalk(IExecutor<T,R> executor) {
        inOrderWalk(root,executor);
    }

    private <R> void inOrderWalk(Node node, IExecutor<T,R> executor) {
        if (node!=null) {
            inOrderWalk(node.left, executor);
            executor.execute(node.value);
            inOrderWalk(node.right, executor);
        }
    }

    public <R> void preOrderWalk(IExecutor<T,R> executor) {
        preOrderWalk(root, executor);
    }

    private <R> void preOrderWalk(Node node, IExecutor<T,R> executor) {
        if (node!=null) {
            executor.execute(node.value);
            preOrderWalk(node.left, executor);
            preOrderWalk(node.right, executor);
        }
    }

    public <R> void postOrderWalk(IExecutor<T,R> executor) {
        postOrderWalk(root, executor);
    }

    private <R> void postOrderWalk(Node node, IExecutor<T,R> executor) {
        if (node!=null) {
            postOrderWalk(node.left, executor);
            postOrderWalk(node.right, executor);
            executor.execute(node.value);
        }
    }

    public void makeBST(String post, String in) {
        ArrayList<String> postArr = new ArrayList<>();
        String[] temp = post.split(", ");
        Collections.addAll(postArr, temp);
        ArrayList<String> inArr = new ArrayList<>();
        String[] temp2 = in.split("(?<=\\G.{1})");
        String prev = "";
        for (int i = 0; i < temp2.length; i++) {
            if (!temp2[i].equals("*")
                    && !temp2[i].equals("/")
                    && !temp2[i].equals("+")
                    && !temp2[i].equals("-")
                    && !temp2[i].equals("%")) {
                if (i != 0) {
                    if (!prev.equals("*")
                            && !prev.equals("/")
                            && !prev.equals("+")
                            && !prev.equals("-")
                            && !prev.equals("%")) {
                        temp2[i-1] += temp2[i];
                        for (int j = i; j < temp2.length-1; j++)
                            temp2[j] = temp2[j+1];
                        String[] add = new String[temp2.length-1];
                        for (int j = 0; j < add.length; j++)
                            add[j] = temp2[j];
                        temp2 = add;
                        i--;
                    }
                }
            }
            prev = temp2[i];
        }
        Collections.addAll(inArr, temp2);
        int[] index = {postArr.size()-1};
        root = makePostOrder((ArrayList<T>) postArr, (ArrayList<T>) inArr, 0, inArr.size()-1, index);
    }

    private Node makePostOrder(ArrayList<T> postArr, ArrayList<T> inArr, int inStart, int inEnd, int[] postIndex) {

        if (inStart > inEnd) return null;

        Node node = new Node(postArr.get(postIndex[0]));

        postIndex[0] = postIndex[0] - 1;

        if (inStart == inEnd)
            return node;

        int index = seek(inArr, inStart, inEnd, node.value);

        node.right = makePostOrder(postArr, inArr, index+1, inEnd, postIndex);
        node.left = makePostOrder(postArr, inArr, inStart, index-1, postIndex);

        return node;
    }

    private int seek(ArrayList<T> arrayList, int inStart, int inEnd, T value) {
        int i;
        for (i = inStart; i <= inEnd; i++) {
            if (((String)arrayList.get(i)).equals((String)value))
                break;
        }
        return i;
    }
}
