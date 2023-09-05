package lab2;

import java.io.*;
import java.util.*;

public class Main {

    private Pracownik[] arr = new Pracownik[0];
    private boolean isRunning = true;

    public void go() {
        while (isRunning) {
            run();
        }
    }

    private void run() {
        System.out.println("Proszę wybrać operację: ");
        System.out.println("1. Wprowadź dane");
        System.out.println("2. Odczytaj dane");
        System.out.println("3. Zapisz dane i zakończ");
        System.out.println("4. Odczytaj dane z pliku");

        Scanner sc = new Scanner(System.in);

        String numer = sc.nextLine();

        switch (numer) {
            case "1":
                wprowadz();
                break;
            case "2":
                wyswietl();
                break;
            case "3":
                zapisz();
                return;
            case "4":
                this.arr = wczytaj();
                break;
            default:
                run();
                break;
        }
    }

    public void wprowadz() {

        System.out.println("Rodzaj pracownika: ");
        System.out.println("1. Pracownik etatowy");
        System.out.println("2. Pracownik godzinowy");

        Scanner sc = new Scanner(System.in);

        String prac = sc.nextLine();

        switch (prac) {
            case "1":
                PracownikEtatowy pracownikEtatowy = new PracownikEtatowy();
                pytania(pracownikEtatowy);
                System.out.println("Podaj podstawową stawkę: ");
                prac = sc.nextLine();
                pracownikEtatowy.setStawka(Double.parseDouble(prac));
                System.out.println("Wymiar etatu: ");
                prac = sc.nextLine();
                pracownikEtatowy.setEtat(Double.parseDouble(prac));
                poszerzTablice(pracownikEtatowy);
                break;
            case "2":
                PracownikGodzinowy pracownikGodzinowy = new PracownikGodzinowy();
                pytania(pracownikGodzinowy);
                System.out.println("Podaj stawkę godzinową: ");
                prac = sc.nextLine();
                pracownikGodzinowy.setStawka(Double.parseDouble(prac));
                System.out.println("Wymiar etatu: ");
                prac = sc.nextLine();
                pracownikGodzinowy.setLiczbaGodzin(Integer.parseInt(prac));
                poszerzTablice(pracownikGodzinowy);
                break;
            default:
                wprowadz();
                return;
        }

        System.out.println("Wprowadzono pracownika\n");
    }

    private void poszerzTablice(Pracownik pracownik) {
        int nowaDlugosc = arr.length+1;
        Pracownik[] temp = arr;
        this.arr = new Pracownik[nowaDlugosc];

        for (int i=0; i < nowaDlugosc-1; i++) {
            arr[i] = temp[i];
        }
        arr[nowaDlugosc-1] = pracownik;
    }

    private void pytania(Pracownik newPrac) {
        Scanner sc = new Scanner(System.in);

        String prac;

        System.out.println("Podaj nazwisko: ");
        prac = sc.nextLine();
        newPrac.setNazwisko(prac);
        System.out.println("Podaj imię: ");
        prac = sc.nextLine();
        newPrac.setImie(prac);
        System.out.println("Podaj PESEL: ");
        prac = sc.nextLine();
        newPrac.setPesel(Long.parseLong(prac));
        System.out.println("Podaj stanowisko: ");
        prac = sc.nextLine();
        newPrac.setStanowisko(prac);
        System.out.println("Podaj staż: ");
        prac = sc.nextLine();
        newPrac.setStaz(Integer.parseInt(prac));
    }

    public void wyswietl() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf(" %-10s| %-10s| %-10s| %-12s| %-10s| %-10s\n", "Naziwsko", "Imię", "PESEL", "Stanowisko", "Staż", "Pensja");
        System.out.println("-----------------------------------------------------------------------------");
        Iterator<Pracownik> it = new PracownikIterator(arr);
        while (it.hasNext()) {
            Pracownik p = it.next();
            System.out.printf(" %-10s| %-10s| %10d| %-12s| %10d| %10.2f\n",
                    p.getNazwisko(), p.getImie(), p.getPesel(), p.getStanowisko(), p.getStaz(), p.pensja());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void zapisz() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/lab/Pracownicy.ser"))) {

            isRunning = false;

            Iterator<Pracownik> it = new PracownikIterator(arr);

            os.writeInt(arr.length);

            while (it.hasNext()) {
                os.writeObject(it.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pracownik[] wczytaj() {

        File plik = new File("src/lab/Pracownicy.ser");

        if (plik.length() != 0L) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(plik))) {

                int dlugosc = is.readInt();

                Pracownik[] lista = new Pracownik[dlugosc];

                for (int i=0; i < dlugosc; i++) {
                    lista[i] = (Pracownik) is.readObject();
                }

                return lista;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Pracownik[0];
    }

    PracownikIterator getIterator(Pracownik[] arr) {
        return new PracownikIterator(arr);
    }

    class PracownikIterator implements Iterator<Pracownik> {

        private Pracownik[] arr;
        private int pos;

        public PracownikIterator(Pracownik[] arr) {
            this.arr = arr;
        }

        @Override
        public boolean hasNext() {
            return pos < arr.length;
        }

        @Override
        public Pracownik next() throws NoSuchElementException {
            return arr[pos++];
        }
    }
}
