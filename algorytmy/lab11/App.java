package lab11;

import cw3.EmptyQueueException;
import cw3.FullQueueException;

public class App {
    public static void main(String[] args) throws EmptyQueueException, FullQueueException {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.readAndBuild();
        BTreePrinter.printNode(redBlackTree.getRoot());
        redBlackTree.przejdzWszerz();
        // redBlackTree.writeToFile();
    }
}
