package lab6;

import cw3.EmptyStackException;
import cw3.FullQueueException;
import cw3.FullStackException;
import cw3.IQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Klient {

    private final IQueue<Zamowienie> queue;
    private String nazwaKlienta;

    public Klient() throws EmptyStackException, FullQueueException, FullStackException {
        this.queue = new HeadAndTailQueue<>();
        Random rand = new Random();
        while (rand.nextInt(20) != 1) {
            queue.enqueue(new Zamowienie());
        }
    }

    public Klient(String nazwaKlienta) throws EmptyStackException, FullQueueException, FullStackException {
        this.queue = new HeadAndTailQueue<>();
        Random rand = new Random();
        while (rand.nextInt(20) != 1) {
            queue.enqueue(new Zamowienie());
        }
        this.nazwaKlienta = nazwaKlienta;
    }

    public String getNazwa() {
        return nazwaKlienta;
    }

    public void setNazwa(String nazwaKlienta) {
        this.nazwaKlienta = nazwaKlienta;
    }

    public IQueue<Zamowienie> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return nazwaKlienta;
    }

    class Zamowienie {

        private static final HashMap<String, Double> PRODUKTY = new HashMap<>(Map.of(
                "Chleb", 2.29, "Mleko", 4.49, "Jajka", 7.99,
                "Ryż", 4.99, "Kasza", 3.49, "Woda", 2.39,
                "Sól", 10.49
        ));

        private String nazwaTowaru;
        private int liczbaSztuk;
        private double cena;

        Zamowienie() {
            int produkt = new Random().nextInt(PRODUKTY.size());
            String nazwaProduktu = "";
            double cenaProduktu = 0;
            int index = 0;
            for (Map.Entry<String, Double> entry : PRODUKTY.entrySet()) {
                if (index == produkt) {
                    nazwaProduktu = entry.getKey();
                    cenaProduktu = entry.getValue();
                    break;
                }
                index++;
            }
            this.nazwaTowaru = nazwaProduktu;
            this.cena = cenaProduktu;
            this.liczbaSztuk = new Random().nextInt(5);
        }

        public double getCena() {
            return cena*liczbaSztuk;
        }
    }
}
