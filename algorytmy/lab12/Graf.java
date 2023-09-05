package lab12;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graf {

    private static final int WHITE = 1;
    private static final int GREY = 2;
    private static final int BLACK = 3;
    private static final int NIL = -1;

    private ArrayList<LinkedList<Pair>> arrayList = new ArrayList<>();

    class Pair {
        int sasiad;
        int waga;
        int color;
        

        @Override
        public String toString() {
            return sasiad + " waga: " + waga;
        }
    }

    public Graf(int n) {
        arrayList.ensureCapacity(n);
        for (int i = 0; i < n; i++) {
            arrayList.add(new LinkedList<>());
        }
    }

    public ArrayList<LinkedList<Pair>> getArrayList() {
        return arrayList;
    }

    public void dodajSasiedztwo(int indeks, int sasiad, int waga) {
        Pair pair = new Pair();
        pair.sasiad = sasiad;
        pair.waga = waga;
        arrayList.get(indeks).add(pair);
    }

    public void DFS() {
        System.out.println("DFS");
        int[] color = new int[arrayList.size()];
        int[] parent = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            color[i] = WHITE;
            parent[i] = NIL;
        }
        int time = 0;
        for (int i = 0; i < arrayList.size(); i++)
        {
            if (color[i] == WHITE)
                DFS_Visit(i, color, parent);
        }
        System.out.println("KONIEC DFS");
    }

    private void DFS_Visit(int i, int[] color, int[] parent) {
        color[i] = GREY;
        for (Pair pair : arrayList.get(i))
        {
            if (color[pair.sasiad] == WHITE)
            {
                parent[pair.sasiad] = i;
                System.out.println(i + " -> " + pair.sasiad);
                DFS_Visit(pair.sasiad, color, parent);
            }
        }
        color[i] = BLACK;
    }

    public void wyswietl() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < arrayList.get(i).size(); j++) {
                System.out.print(arrayList.get(i).get(j) + ", ");
            }
            System.out.println();
        }
    }
}
