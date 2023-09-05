package lab3;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    private OneWayLinkedListWithHead<Karta> lista;
    private ArrayCheck arrayCheck;

    private boolean isRunning;

    public Main(OneWayLinkedListWithHead<Karta> lista, ArrayCheck arrayCheck) {
        this.lista = lista;
        this.arrayCheck = arrayCheck;
        this.isRunning = true;
    }

    public void run() {
        makeList();
        while (isRunning) {
            helpRun();
        }
    }

    private void makeList() {
        Karta karta = new Karta();

        while (karta.getWartosc()!=0) {
            insert(karta);
            karta = new Karta();
        }
    }

    private void insert(Karta karta) {
        if (!arrayCheck.isInDeck(karta)) {
            arrayCheck.addToDeck(karta);
            insertAlgo(karta);
        } else if (karta.getWartosc() == 14) {
            lista.add(karta);
        }
    }

    private void insertAlgo(Karta karta) {
        int wartosc = karta.getWartosc();
        int kolor = karta.getKolor();

        if (lista.isEmpty()) {
            lista.add(karta);
            return;
        }

        int index = -1;

        for (int i = lista.size() - 1; (i >= 0 && wartosc < lista.get(i).getWartosc()); i--) index = i;

        int kolorIndex = -1;

        for (int i = index - 1;
             (i >= 0 && wartosc == lista.get(i).getWartosc() && kolor < lista.get(i).getKolor());
             i--) kolorIndex = i;

        if (kolorIndex!=-1) index = kolorIndex;

        if (index!=-1) lista.add(index, karta);
        else {
            index = lista.size() - 1;
            if (lista.get(index).getWartosc() == wartosc) {
                if (lista.get(index).getKolor() > kolor) {
                    lista.add(index, karta);
                }
            } else {
                lista.add(karta);
            }
        }
    }

    private void remove() {
        for (Karta temp : lista) {
            if (!temp.isZnacznik()) {
                lista.remove(temp);
            }
        }
    }

    private void helpRun() {
        System.out.println("Proszę wybrać operację: ");
        System.out.println("1. Wyświetl elementy listy");
        System.out.println("2. Ilość elementów listy oraz stosunek zakrytych/odkrytych");
        System.out.println("3. Wyświetl karty o podanej wartości");
        System.out.println("4. Wyświetl karty o podanym kolorze");
        System.out.println("5. Usuwanie kart zakrytych");
        System.out.println("6. Zakończ program");

        Scanner sc = new Scanner(System.in);

        String zmienna = sc.nextLine();

        switch (zmienna) {
            case "1":
                display();
                break;
            case "2":
                displayStats();
                break;
            case "3":
                System.out.println("Proszę podać wartość: (1-13)");
                zmienna = sc.nextLine();
                displayWartosc(Integer.parseInt(zmienna));
                break;
            case "4":
                System.out.println("Proszę podać kolor: (0-3)");
                zmienna = sc.nextLine();
                displayKolor(Integer.parseInt(zmienna));
                break;
            case "5":
                remove();
                break;
            case "6":
                isRunning = false;
                break;
            default:
                helpRun();
                break;
        }
    }

    private void displayStats() {
        int rozmiar = lista.size();
        int zakryte = covHelper();
        System.out.printf("Liczba elementów listy: %d Liczba kart odkrytych: %d Liczba kart zakrytych %d\n",
                rozmiar,
                rozmiar-zakryte,
                zakryte);
    }

    private int covHelper() {
        int result = 0;
        Iterator<Karta> it = lista.iterator();
        while (it.hasNext()) {
            if (!it.next().isZnacznik()) result++;
        }
        return result;
    }

    private void display() {
        Iterator<Karta> it = lista.iterator();
        while (it.hasNext()) {
            Karta temp = it.next();
            if (!temp.isZnacznik()) {
                System.out.println("()");
            } else {
                System.out.printf("Wartość: %d Kolor: %d\n", temp.getWartosc(), temp.getKolor());
            }
        }
    }

    private void displayWartosc(int wartosc) {
        Iterator<Karta> it = lista.iterator();
        while (it.hasNext()) {
            Karta temp = it.next();
            if (temp.getWartosc() == wartosc) {
                System.out.printf("Wartość: %d Kolor: %d\n", temp.getWartosc(), temp.getKolor());
            }
        }
    }

    private void displayKolor (int kolor) {
        Iterator<Karta> it = lista.iterator();
        while (it.hasNext()) {
            Karta temp = it.next();
            if (temp.getKolor() == kolor) {
                System.out.printf("Wartość: %d Kolor: %d\n", temp.getWartosc(), temp.getKolor());
            }
        }
    }

}
