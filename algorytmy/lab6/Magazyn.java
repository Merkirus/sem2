package lab6;

import cw3.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Magazyn {

    private static final String[] KLIENCI = {
            "Herman", "Emanuel", "Ryszard", "Barbara",
            "Ołdrzych", "Hieronim", "Honorata", "Świętosław",
            "Matylda", "Genowefa"
    };

    private IQueue<Klient> queue;
    private double totalPrice = 0;
    private boolean isRunning = true;

    private String nazwa;

    public Magazyn(String nazwa) throws EmptyStackException, FullQueueException, FullStackException {
        this.nazwa = nazwa;
        this.queue = new HeadAndTailQueue<>();
        Random rand = new Random();
        while (rand.nextInt(10) != 1) {
            queue.enqueue(new Klient(KLIENCI[rand.nextInt(KLIENCI.length)]));
        }
    }

    public void run() throws Exception {
        while (isRunning) helpRun();
    }

    private void helpRun() throws Exception {
        System.out.println("Proszę wybrać operację: ");
        System.out.println("1. Obsłuż wszystkich klientów");
        System.out.println("2. Czekający klienci");
        System.out.println("3. Dodaj klienta");
        System.out.println("4. Wyjdź");

        Scanner sc = new Scanner(System.in);

        String temp = sc.nextLine();

        switch (temp) {
            case "1" -> {
                if (queue.isEmpty()) System.out.println("Nie ma już klientów");
                System.out.println("############################");
                System.out.printf("# %-15s # %-6s #\n","Nazwa klienta", "Kwota");
                System.out.println("############################");
                while (!queue.isEmpty()) obsluga();
                System.out.println("############################");
                System.out.printf("Suma kwota wynosi: %.2f\n", totalPrice);
            }
            case "2" -> {
                if (queue.isEmpty()) System.out.println("Nikt obecnie nie czeka");
                wyswietl();
            }
            case "3" -> {
                System.out.println("Proszę wprowadzić imię klienta");
                System.out.println("(Klient sam wybierze produkty)");
                temp = sc.nextLine();
                queue.enqueue(new Klient(temp));
            }
            case "4" -> {
                isRunning = false;
            }
        }
    }

    private void wyswietl() throws EmptyStackException, EmptyQueueException, FullQueueException, FullStackException {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Klient klient = queue.dequeue();
            System.out.println(klient);
            queue.enqueue(klient);
        }
    }

    public void obsluga() throws Exception {
        Klient klient = queue.dequeue();
        double kwota = 0;
        while (!klient.getQueue().isEmpty()) {
            kwota += klient.getQueue().dequeue().getCena();
        }
        System.out.printf("# %-15s # %-6.2f #\n",klient.getNazwa(), kwota);
        totalPrice += kwota;
    }

    public String getNazwa() {
        return nazwa;
    }

    public IQueue<Klient> getQueue() {
        return queue;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
