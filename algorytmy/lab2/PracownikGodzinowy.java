package lab2;

public class PracownikGodzinowy extends Pracownik {

    private int liczbaGodzin;
    private double stawka;

    public PracownikGodzinowy() {
        super();
        this.liczbaGodzin = 0;
        this.stawka = 0;
    }

    public PracownikGodzinowy(String nazwisko, String imie, long pesel, String stanowisko, int staz, int liczbaGodzin, double stawka) {
        super(nazwisko, imie, pesel, stanowisko, staz);
        this.liczbaGodzin = liczbaGodzin;
        this.stawka = stawka;
    }

    public int getLiczbaGodzin() {
        return liczbaGodzin;
    }

    public void setLiczbaGodzin(int liczbaGodzin) {
        this.liczbaGodzin = liczbaGodzin;
    }

    public double getStawka() {
        return stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    @Override
    double pensja() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + ", %d, %.2f, %.2f",liczbaGodzin, stawka, pensja());
    }
}
