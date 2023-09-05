package cw6;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        RedBlackTree rbTree = new RedBlackTree();
        rbTree.insertNode(15);
        rbTree.insertNode(10);
        rbTree.insertNode(5);
        rbTree.insertNode(20);
        rbTree.insertNode(30);
        rbTree.insertNode(35);
        rbTree.insertNode(7);
        rbTree.insertNode(6);
        rbTree.insertNode(8);
        rbTree.insertNode(3);
        rbTree.insertNode(33);
        rbTree.deleteNode(8);
        rbTree.deleteNode(10);
        rbTree.deleteNode(33);
        BTreePrinter.printNode(rbTree.getRoot());
    }
}
