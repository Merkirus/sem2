package lab3;

public class ArrayCheck {

    private boolean[] arr;

    public ArrayCheck() {
        this.arr = new boolean[54];
    }

    void addToDeck(Karta karta) {
        if (!isInDeck(karta)) {
            int indeks = (karta.getWartosc()-1)+(13*(karta.getKolor()));
            arr[indeks] = true;
        }
    }

    boolean isInDeck(Karta karta) {
        if (!karta.isZnacznik()) return true;
        int indeks = (karta.getWartosc()-1)+(13*(karta.getKolor()));
        return arr[indeks];
    }

    void display() {
        for (boolean is : arr) {
            System.out.println(is);
        }
    }

}
