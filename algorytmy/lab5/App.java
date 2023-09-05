package lab5;

import lab4.EmptyStackException;
import lab4.FullStackException;

public class App {

    public static void main(String[] args) throws FullStackException, EmptyStackException {
        BazaDanych bazaDanych = new BazaDanych();
        bazaDanych.run();
    }

}
