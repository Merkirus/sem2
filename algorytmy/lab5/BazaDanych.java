package lab5;

import lab4.ArrayStack;
import lab4.EmptyStackException;
import lab4.FullStackException;
import lab4.IStack;

import java.io.*;
import java.util.*;

public class BazaDanych {

    private final static String[] MARKI = {
            "Audi", "BMW", "Kia", "Hyundai",
            "Ford", "Toyota", "Honda", "Subaru",
            "Opel", "Chrysler", "Dodge", "Volkswagen",
            "Peugeot", "Renault", "Citroen", "Ferrari",
            "Porshe", "Fiat", "Rimac", "Łada", "Trabant"
    };

    private final static String[] TYP = {
            "Kabriolet", "Hatchback", "Van", "Sedan",
            "Limuzyna", "Pick-up", "Kombi", "Roadster",
            "SUV", "Jeep"
    };

    private final static String[] KOLOR = {
            "Różowy", "Czerwony", "Zielony", "Pomarańczowy",
            "Czarny", "Biały", "Szary", "Grafitowy", "Niebieski",
            "Srebrny", "Żółty", "Fioletowy", "Beżowy"
    };

    private ArrayList<String> bazaMarek;
    private ArrayList<String> bazaRocznikow;
    private L2KCzS<Auto> lista;

    private boolean isRunning;

    public BazaDanych() {
        this.lista = new L2KCzS<>();
        this.bazaMarek = new ArrayList<>();
        this.bazaRocznikow = new ArrayList<>();
        this.isRunning = true;
    }

    public void run() throws FullStackException, EmptyStackException {
        makeDatabase();
        while (isRunning) {
            helpRun();
        }
    }

    private void helpRun() throws FullStackException, EmptyStackException {
        System.out.println("Proszę wybrać operację: ");
        System.out.println("1. Wyświetl bazę danych");
        System.out.println("2. Wyświetl dany samochód");
        System.out.println("3. Dodaj samochód do bazy danych");
        System.out.println("4. Usuń samochód z bazy danych");
        System.out.println("5. Zmodyfikuj dane samochodu");
        System.out.println("6. Wyświetl daną markę");
        System.out.println("7. Wyświetl dany rocznik");
        System.out.println("8. Wyświetl samochody do podanej ceny");
        System.out.println("9. Zapisz i wyjdź");
        System.out.println("10. Odczytaj z pliku");
        System.out.println("11. SYMULACJA PARKINGU");

        Scanner sc = new Scanner(System.in);

        String temp = sc.nextLine();

        switch (temp) {
            case "1" -> {
                display();
            }
            case "2" -> {
                System.out.println("Proszę podać numer indeksu samochodu");
                temp = sc.nextLine();
                displayCar(temp);
            }
            case "3" -> {
                System.out.println("Proszę wprowadzić następujące dane");
                System.out.println("Nr silnika");
                int nr_silnika = Integer.parseInt(sc.nextLine());
                System.out.println("Marka");
                String marka = sc.nextLine();
                System.out.println("Typ");
                String typ = sc.nextLine();
                System.out.println("Data produkcji");
                int data_produkcji = Integer.parseInt(sc.nextLine());
                System.out.println("Cena");
                double cena = Double.parseDouble(sc.nextLine());
                System.out.println("Kolor");
                String kolor = sc.nextLine();
                System.out.println("Czas składowania");
                int skladowanie = Integer.parseInt(sc.nextLine());
                insert(new Auto(nr_silnika, marka, typ, data_produkcji,
                        cena, kolor, skladowanie));
                System.out.println("Auto zostało wprowadzone do bazy danych");
            }
            case "4" -> {
                System.out.println("Proszę podać numer indeksu samochodu");
                temp = sc.nextLine();
                remove(temp);
                System.out.println("Auto zostało usunięte z bazy danych");
            }
            case "5" -> {
                System.out.println("Proszę podać numer indeksu samochodu");
                int index = Integer.parseInt(sc.nextLine());
                boolean silnikChanged = false;
                System.out.println("Nr silnika (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setNr_silnika(Integer.parseInt(temp));
                    silnikChanged = true;
                }
                System.out.println("Marka (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setMarka(temp);
                }
                System.out.println("Typ (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setTyp(temp);
                }
                System.out.println("Data produkcji (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setData_produkcji(Integer.parseInt(temp));
                }
                System.out.println("Cena (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setCena(Double.parseDouble(temp));
                }
                System.out.println("Kolor (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setKolor(temp);
                }
                System.out.println("Czas składowania (Enter - bez zmian)");
                if (!Objects.equals(temp = sc.nextLine(), "")) {
                    lista.get(index).setSkladowanie(Integer.parseInt(temp));
                }
                if (silnikChanged) {
                    Auto auto = lista.get(index);
                    lista.remove(index);
                    insert(auto);
                }
            }
            case "6" -> {
                System.out.println("Proszę podać markę samochodu");
                String marki = String.join(", ", bazaMarek);
                System.out.println(marki);
                temp = sc.nextLine();
                displayMake(temp);
            }
            case "7" -> {
                System.out.println("Proszę podać rocznik samochodu");
                String roczniki = String.join(", ", bazaRocznikow);
                System.out.println(roczniki);
                temp = sc.nextLine();
                displayDate(temp);
            }
            case "8" -> {
                System.out.println("Proszę podać maksymalną cenę");
                temp = sc.nextLine();
                displayPrice(temp);
            }
            case "9" -> {
                isRunning = false;
                save();
            }
            case "10" -> {
                load();
            }
            case "11" -> {
                System.out.println("Proszę wybrać kupowane auto");
                temp = sc.nextLine();
                simulation(temp);
            }
        }
    }

    private void makeDatabase() {

        Random rand = new Random();

        do {
            Auto auto = new Auto(
                    rand.nextInt(1000,10000),
                    MARKI[rand.nextInt(MARKI.length)],
                    TYP[rand.nextInt(TYP.length)],
                    rand.nextInt(1950,2023),
                    rand.nextInt(10000, 500000),
                    KOLOR[rand.nextInt(KOLOR.length)],
                    rand.nextInt(25)
            );
            insert(auto);
        } while (rand.nextInt(10) != 1);
    }

    private void remove(String stringIndex) {
        int index = Integer.parseInt(stringIndex);
        lista.remove(index);
    }

    private void insert(Auto auto) {

        if (!bazaMarek.contains(auto.getMarka())) {
            bazaMarek.add(auto.getMarka());
            Collections.sort(bazaMarek);
        }
        if (!bazaRocznikow.contains(Integer.toString(auto.getData_produkcji()))) {
            bazaRocznikow.add(Integer.toString(auto.getData_produkcji()));
            Collections.sort(bazaRocznikow);
        }

        int nr_silnika = auto.getNr_silnika();

        if (lista.isEmpty()) {
            lista.add(auto);
            return;
        }

        int index = -1;

        for (int i = lista.size() - 1; (i >= 0 && nr_silnika < lista.get(i).getNr_silnika()); i--) index = i;

        if (index!=-1) lista.add(index, auto);
        else lista.add(auto);
    }

    private void display() {
        for (int i=0; i < lista.size(); i++) {
            System.out.println(lista.get(i) + " Index: " + i);
        }
    }

    private void displayCar(String stringIndex) {
        int index = Integer.parseInt(stringIndex);
        System.out.println(lista.get(index));
    }

    private void displayMake(String marka) {
        for (Auto auto : lista) {
            if (auto.getMarka().equals(marka)) System.out.println(auto);
        }
    }

    private void displayDate(String stringDate) {
        int date = Integer.parseInt(stringDate);
        for (Auto auto : lista) {
            if (auto.getData_produkcji() == date) System.out.println(auto);
        }
    }

    private void displayPrice(String stringPrice) {
        double price = Double.parseDouble(stringPrice);
        for (Auto auto : lista) {
            if (auto.getCena() <= price) System.out.println(auto);
        }
    }

    private void save() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/lab5/BazaDanych.ser"))) {

            os.writeInt(lista.size());
            for (Auto auto : lista) os.writeObject(auto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {

        File plik = new File("src/lab5/BazaDanych.ser");

        if (plik.length() != 0L) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(plik))) {

                int dlugosc = is.readInt();
                this.lista.clear();
                this.bazaMarek.clear();
                this.bazaRocznikow.clear();
                for (int i=0; i < dlugosc; i++) {
                    Auto auto = (Auto) is.readObject();
                    if (!bazaMarek.contains(auto.getMarka())) bazaMarek.add(auto.getMarka());
                    if (!bazaRocznikow.contains(Integer.toString(auto.getData_produkcji())))
                        bazaRocznikow.add(Integer.toString(auto.getData_produkcji()));
                    this.lista.add(auto);
                }

                Collections.sort(bazaMarek);
                Collections.sort(bazaRocznikow);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void simulation(String stringIndex) throws FullStackException, EmptyStackException {
        int index = Integer.parseInt(stringIndex);
        IStack<Auto> parking = new LinkedListStack<>();
        System.out.printf("Kupowane auto\n%s\n", lista.get(index));
        System.out.println("Muszą wyjechać kolejno");
        for (int i=lista.size()-1;i >= index+1; i--) {
            Auto auto = lista.get(i);
            System.out.println(auto);
            parking.push(auto);
        }
        System.out.println("Następnie kolejno wjeżdżają");
        while (!parking.isEmpty()) {
            Auto auto = parking.pop();
            System.out.println(auto);
        }
        lista.remove(index);
    }
}
