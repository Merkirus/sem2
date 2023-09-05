package cw3;

public class App {

    public static void main(String[] args) throws Exception {
        Flawiusz flawiusz = new Flawiusz(2);
        flawiusz.makeQueue(10);
        System.out.println(flawiusz.solve());
    }

}
