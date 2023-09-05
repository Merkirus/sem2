package lab10;

import cw3.EmptyQueueException;
import cw3.EmptyStackException;
import cw3.FullQueueException;
import cw3.FullStackException;
import cw5.IntegerToStringExecutor;

import java.util.HashMap;

public class App {
    public static void main(String[] args) throws EmptyStackException, EmptyQueueException, FullQueueException, FullStackException {
        MorseTree mt = new MorseTree();
        mt.translateMorse();
        mt.generateTree();
        mt.convertToCode();
        mt.saveCodeToFile();
    }
}
