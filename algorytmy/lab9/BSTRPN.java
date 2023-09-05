package lab9;

import cw3.*;
import cw5.BST;

import java.util.Comparator;

public class BSTRPN {

    private BST<String> bst;
    private double wynik;
    private RPN rpn;
    private String inOrder;
    private String postOrder;

    public BSTRPN(String wyrazenie) throws Exception {
        this.inOrder = wyrazenie;
        this.rpn = new RPN(wyrazenie);
        this.postOrder = rpn.getRpnVersion();
        bst = new BST<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void makeTree() {
        String temp = "";
        temp = inOrder.replace("(", "");
        temp = temp.replace(")", "");
        bst.makeBST(postOrder, temp);
    }

    public void oblicz() throws Exception {
        ObjectToStringExecutor executor = new ObjectToStringExecutor();
        bst.postOrderWalk(executor);
        rpn.setWyrazenie(executor.getResult());
        rpn.oblicz();
        wynik = rpn.getResult();
    }

    public void przejdzWszerz() throws EmptyQueueException, FullQueueException {
        bst.przejdzWszerz();
    }

    public double getWynik() {
        return wynik;
    }

    public String getInOrder() {
        return inOrder;
    }

    public String getPostOrder() {
        return postOrder;
    }

    public int getLiczbaLisci() {
        return bst.libczaLisci();
    }

    public int getWysokosc() {
        return bst.wysokosc();
    }

    public int getLiczbaWezlow() {
        return bst.liczbaWezlow();
    }
}
