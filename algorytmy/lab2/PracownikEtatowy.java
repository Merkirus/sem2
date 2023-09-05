package lab2;

public class PracownikEtatowy extends Pracownik {

    private double etat;
    private double stawka;

    public PracownikEtatowy() {
        super();
        this.etat = 0;
        this.stawka = 0;
    }

    public PracownikEtatowy(String nazwisko, String imie, long pesel, String stanowisko, int staz, double etat, double stawka) {
        super(nazwisko, imie, pesel, stanowisko, staz);
        this.etat = etat;
        this.stawka = stawka;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }

    public double getEtat() {
        return etat;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    public double getStawka() {
        return stawka;
    }

    @Override
    double pensja() {
        return stawka*etat;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + ", %.2f, %.2f, %.2f", etat, stawka, pensja());
    }
}
