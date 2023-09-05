package lab2;

import java.io.Serializable;

public abstract class Pracownik implements Serializable {

    protected String imie;
    protected String nazwisko;

    protected long pesel;

    protected String stanowisko;
    protected int staz;

    public Pracownik() {
        this.nazwisko = "Kowalski";
        this.imie = "Jan";
        this.pesel = 0;
        this.stanowisko = "Default";
        this.staz = 0;
    }

    public Pracownik(String nazwisko, String imie, long pesel, String stanowisko, int staz) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.stanowisko = stanowisko;
        this.staz = staz;
    }

    abstract double pensja();

    public void wyswietl() {
        System.out.printf("Nazwisko: %s, Imię: %s, Pesel: %d, Stanowisko: %s, Staż: %d", nazwisko, imie, pesel, stanowisko, staz);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s, %d", nazwisko, imie, pesel, stanowisko, staz);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public int getStaz() {
        return staz;
    }

    public void setStaz(int staz) {
        this.staz = staz;
    }
}
