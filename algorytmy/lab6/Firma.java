package lab6;

import cw3.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Firma {

    private IQueue<Magazyn> queue;
    private double totalIncome;
    private boolean isRunning = true;

    public Firma() throws EmptyStackException, FullQueueException, FullStackException {
        this.queue = new HeadAndTailQueue<>();
        Random rand = new Random();
        int index = 1;
        while (rand.nextInt(10)!=1) {
            queue.enqueue(new Magazyn(String.valueOf(index)));
            index++;
        }
    }

    public Firma(int n) throws EmptyStackException, FullQueueException, FullStackException {
        this.queue = new HeadAndTailQueue<>();
        Random rand = new Random();
        int index = 1;
        while (n>0) {
            queue.enqueue(new Magazyn(String.valueOf(index)));
            index++;
            n--;
        }
    }

    public void run() throws Exception {
        while (isRunning) helpRun();
    }

    private void helpRun() throws Exception {
        System.out.println("Proszę wybrać operację: ");
        System.out.println("1. Obsłuż wszystkie magazyny");
        System.out.println("2. Wyjdź");

        Scanner sc = new Scanner(System.in);

        String temp = sc.nextLine();

        switch (temp) {
            case "1" -> {
                if (queue.isEmpty()) System.out.println("Nie ma już magazynów");
                System.out.println("############################");
                System.out.printf("# %-15s # %-6s #\n","Magazyn/klienci", "Kwota");
                System.out.println("############################");
                while (!queue.isEmpty()) obsluga();
                System.out.println("############################");
                System.out.printf("Suma kwot wynosi: %.2f\n", totalIncome);
            }
            case "2" -> {
                isRunning = false;
            }
        }
    }


    private void wyswietl() throws EmptyStackException, EmptyQueueException, FullQueueException, FullStackException {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Magazyn magazyn = queue.dequeue();
            System.out.println(magazyn);
            queue.enqueue(magazyn);
        }
    }

    private void obsluga() throws Exception {
        Magazyn magazyn = queue.dequeue();
        double kwota = 0;
        System.out.printf("# %-15s # %-6s #\n",magazyn.getNazwa(), "-");
        while (!magazyn.getQueue().isEmpty()) {
            magazyn.obsluga();
        }
        totalIncome += magazyn.getTotalPrice();
    }


}
