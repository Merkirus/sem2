package cw8;

public class Zad1 {
    public static void main(String[] args) {
        String tekst = "0000100001010001";
        String wzorzec = "0001";
        solve(tekst, wzorzec);
    }

    private static void solve(String tekst, String wzorzec) {
        int tekst_len = tekst.length();
        int wzorzec_len = wzorzec.length();

        int porownania = 0;

        for (int i = 0; i < tekst_len-wzorzec_len; i++) {
            boolean found = true;
            for (int j = 0; j < wzorzec_len; j++) {
                porownania++;
                if (wzorzec.charAt(j) != tekst.charAt(i + j)) {
                    found = false;
                    break;
                }
            }
            if (!found)
                continue;
            System.out.println("Przesunięcie: " + i);
            break;
        }
        System.out.println("Porównania: " + porownania);
    }
}
