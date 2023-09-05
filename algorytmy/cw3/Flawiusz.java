package cw3;

public class Flawiusz {

    private IQueue<Integer> queue;
    private int k;

    public Flawiusz(int k) {
        this.queue = new ArrayQueue<>();
        this.k = k;
    }

    public void makeQueue(int n) throws EmptyStackException, FullStackException, FullQueueException {
        for (int i=0; i < n; i++) {
            queue.enqueue(i+1);
        }
    }

    //TODO Gdy element jest bezpieczny usuwamy go z kolejki i wstawiamy na koniec
    //Inaczej usuwamy bez wstawiania
    public int solve() throws Exception {
        int temp = 1;
        while (queue.size() != 1) {
            if (temp % k == 0) {
                queue.dequeue();
            } else {
                int element = queue.dequeue();
                queue.enqueue(element);
            }
            temp++;
        }
        return queue.dequeue();
    }

    // https://www.youtube.com/watch?v=fZ3p2Iw-O2I
    // https://home.agh.edu.pl/~zobmat/2021/pedzich_maciej/index.html#/
//    public int index(int x) {
//        // x oznacza liczbę żyjących osób
//        // Zwracamy indeks następnej żywej osoby
//        if (x == 1) return 0;
//        // k przesunięcie,np 0-1-2 -> 0-2 dla k = 2
//        // czyli 0 + (k=2) = 2 <- od niego zaczynamy kolejny cykl, 1 umiera
//        // % pozwala na "zapętlenie" indeksów
//        // + 1 zwraca "ludzki" indeks
//        return ((index(x-1) + k) % x) + 1;
//    }

}
