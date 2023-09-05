package lab3;

import java.util.Random;

public class Karta {

    private int wartosc;
    private int kolor;
    private boolean znacznik;

    private Random rand;

    public Karta() {
        this.rand = new Random();
        this.wartosc = rand.nextInt(15);
        this.kolor = rand.nextInt(4);
        this.znacznik = wartosc != 14;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getKolor() {
        return kolor;
    }

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }

    public boolean isZnacznik() {
        return znacznik;
    }

    public void setZnacznik(boolean znacznik) {
        this.znacznik = znacznik;
    }
}
