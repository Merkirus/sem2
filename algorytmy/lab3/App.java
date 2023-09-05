package lab3;

public class App {

    public static void main(String[] args) {
        OneWayLinkedListWithHead<Karta> lista = new OneWayLinkedListWithHead<>();
        ArrayCheck arrayCheck = new ArrayCheck();
        Main main = new Main(lista, arrayCheck);

        main.run();
    }

}
