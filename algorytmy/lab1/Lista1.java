package lab1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Lista1 {

    /*
    * 1-as, 2-2, 3-3, ..., 11-walet, 12-dama, 13-król
    * 0-kier, 1-karo, 2-trefl, 3-pik
    * */

    public static void main(String[] args) {

        Menu.makeDeck();

        Menu.showAll();

        Menu.reduce();

        System.out.println("----------------");

        Menu.showAll();

        System.out.println("------TEST------");

        // Asy kier do testów
        Menu.insert(new Karta(1,0));
        Menu.insert(new Karta(1,0));
        Menu.insert(new Karta(1,0));

        Menu.showAll();

        System.out.println("----------------");

        Menu.reduce();

        Menu.showAll();

        Menu.showColor(3);
    }

}

class Menu {

    static ArrayList<Karta> talia;

    public static void makeDeck() {
        talia = new ArrayList<>();

        Karta karta = new Karta();

        while (karta.wartosc!=0) {
            Menu.insert(karta);
            karta = new Karta();
        }

    }

    public static void insert(Karta karta) {
        // Gdy talia jest pusta, zwyczajnie dodajemy do niej kartę
        if (talia.isEmpty()) {
            talia.add(karta);
            return;
        }

        int index = -1;

        // Segregacja względem wartości
        for (int i= talia.size()-1; (i >= 0 && karta.wartosc < talia.get(i).wartosc); i--) index = i;

        int newIndex = -1;

        // Segregacja względem koloru
        for (int j=index-1;
             (j >= 0 && karta.wartosc == talia.get(j).wartosc && karta.kolor < talia.get(j).kolor);
             j--) newIndex = j;

        // Jeżeli zmiana względem koloru poprawiamy finalny indeks
        if (newIndex!=-1) {
            index = newIndex;
        }

        // Jeżeli znaleźliśmy indeks, to wprowadzamy, inaczej są dwa przypadki
        // 1) Największa liczba, której nie było i dodajemy ją na koniec
        // 2) Największa liczba, która wystąpiła ma większy kolor i jest specjalnym przypadkiem do sprawdzenia
        if (index!=-1) {
            talia.add(index, karta);
        } else {
            index = talia.size()-1;
            if (talia.get(index).wartosc == karta.wartosc) {
                if (talia.get(index).kolor > karta.kolor) {
                    talia.add(index, karta);
                }
            } else {
                talia.add(karta);
            }
        }
    }

    public static void reduce() {

        if (talia.isEmpty()) return;

        Iterator<Karta> it = talia.iterator();

        for (Karta karta = it.next(); it.hasNext();) {
            Karta k = it.next();
            if (karta.wartosc == k.wartosc && karta.kolor == k.kolor) {
                it.remove();
            }
            karta = k;
        }
    }

    public static void showNum() {
        System.out.println(talia.size());
    }

    public static void showValue(int wartosc) {
        for (Karta karta : talia) {
            if (karta.wartosc == wartosc) {
                System.out.println(karta.toString());
            }
        }
    }

    public static void showColor(int kolor) {

        int n = 0;

        while (n < talia.size()) {
            if (talia.get(n).kolor == kolor) {
                System.out.println(talia.get(n).toString());
            }
            n++;
        }
    }

    public static void showAll() {
        for (Karta karta : talia) {
            System.out.println(karta.toString());
        }
    }
}

class Karta {

    int wartosc;
    int kolor;


    public Karta() {
        Random random = new Random();
        this.wartosc = random.nextInt(14);
        this.kolor = random.nextInt(4);
    }

    public Karta(int wartosc, int kolor) {
        this.wartosc = wartosc;
        this.kolor = kolor;
    }

    @Override
    public String toString() {
        return "lab1.Karta{" +
                "wartosc=" + wartosc +
                ", kolor=" + kolor +
                '}';
    }
}
