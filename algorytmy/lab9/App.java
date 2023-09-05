package lab9;

public class App {
    public static void main(String[] args) throws Exception {
        String wyrazenie = "2.0/(2.0-2.0)";
        BSTRPN bstrpn = new BSTRPN(wyrazenie);
        bstrpn.makeTree();
        bstrpn.oblicz();
        System.out.println(bstrpn.getWynik());
        System.out.println(bstrpn.getInOrder());
        System.out.println(bstrpn.getPostOrder());
        System.out.println(bstrpn.getWysokosc());
        System.out.println(bstrpn.getLiczbaLisci());
        System.out.println(bstrpn.getLiczbaWezlow());
        bstrpn.przejdzWszerz();
    }
}
