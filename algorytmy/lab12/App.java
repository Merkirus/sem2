package lab12;

public class App {
    public static void main(String[] args) {
        Graf graf = new Graf(5);
        graf.dodajSasiedztwo(0,1, 10);
        graf.dodajSasiedztwo(0,3, 30);
        graf.dodajSasiedztwo(0,4, 100);
        graf.dodajSasiedztwo(1,2, 50);
        graf.dodajSasiedztwo(2,4, 10);
        graf.dodajSasiedztwo(3,2, 20);
        graf.dodajSasiedztwo(3,4, 60);
        // graf.wyswietl();
        Dijsktra dijsktra = new Dijsktra(graf);
        dijsktra.find(0);
        dijsktra.wyswietlDrogi();
        dijsktra.wyswietlDrogiSlownie();
        Prim prim = new Prim(graf);
        prim.find(0);
        prim.wyswietlDrogiSlownie();
        // graf.DFS();
    }
}
