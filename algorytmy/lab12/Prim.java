package lab12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Prim {

    private Graf graf;
    private int grafSize;
    HashMap<Integer, Integer> droga;
    HashMap<Integer, String> drogaSlownie;
    ArrayList<Integer> beingProcessed;
    ArrayList<Integer> toBeProcessed;

    public Prim(Graf graf) {
        this.droga = new HashMap<>();
        this.drogaSlownie = new HashMap<>();
        this.beingProcessed = new ArrayList<>();
        this.toBeProcessed = new ArrayList<>();
        this.graf = graf;
        this.grafSize = graf.getArrayList().size();
        for (int i = 0; i < grafSize; i++)
            droga.put(i, Integer.MAX_VALUE);
        for (int i = 0; i < grafSize; i++)
            toBeProcessed.add(i);
    }

    public void find(int start) {
        addToBeingProcessed(start);
        drogaSlownie.put(start, "" + start);
        droga.put(start, 0);
        for (Graf.Pair pair : getNeighboursAndWeights(start)) {
            drogaSlownie.put(pair.sasiad, drogaSlownie.get(start) + " -> " + pair.sasiad);
            droga.put(pair.sasiad, pair.waga);
        }
        while (!toBeProcessed.isEmpty()) {
            int min_droga = Integer.MAX_VALUE;
            int min_wierzcholek = Integer.MAX_VALUE;
            for (int wierzcholek : toBeProcessed) {
                if (droga.get(wierzcholek) < min_droga) {
                    min_droga = droga.get(wierzcholek);
                    min_wierzcholek = wierzcholek;
                }
            }
            if (min_wierzcholek == Integer.MAX_VALUE)
                break;
            addToBeingProcessed(min_wierzcholek);
            for (Graf.Pair pair : getNeighboursAndWeights(min_wierzcholek)) {
                if (droga.get(pair.sasiad) < pair.waga) {
                    drogaSlownie.put(pair.sasiad, drogaSlownie.get(pair.sasiad));
                } else {
                    drogaSlownie.put(pair.sasiad, drogaSlownie.get(min_wierzcholek) + " -> " + pair.sasiad);
                }
                droga.put(pair.sasiad, Math.min(droga.get(pair.sasiad), pair.waga));
            }
        }
    }

//    public void wyswietlDrogi() {
//        for (Map.Entry<Integer, Integer> entry : droga.entrySet()) {
//            if (entry.getValue() == Integer.MAX_VALUE)
//                System.out.println("Miasto: " + entry.getKey() + " Droga: ∞");
//            else
//                System.out.println("Miasto: " + entry.getKey() + " Droga: " + entry.getValue());
//        }
//    }

    public void wyswietlDrogiSlownie() {
        for (Map.Entry<Integer, String> entry : drogaSlownie.entrySet()) {
            System.out.println("Miasto: " + entry.getKey() + " Droga: " + entry.getValue());
        }
    }

    private void addToBeingProcessed(int wierzcholek) {
        if (beingProcessed.contains(wierzcholek))
            return;
        beingProcessed.add(wierzcholek);
        toBeProcessed.remove((Integer) wierzcholek);
    }

    private LinkedList<Graf.Pair> getNeighboursAndWeights(int from) {
        return graf.getArrayList().get(from);
    }
}
