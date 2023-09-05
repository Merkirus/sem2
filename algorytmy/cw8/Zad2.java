package cw8;

public class Zad2 {
    public static void main(String[] args) {
        String wzorzec = "26";
        String tekst = "3141592653589793";
        solve(tekst, wzorzec);
    }

    private static void solve(String tekst, String wzorzec) {
        int d = 256;
        int q = 11;
        int tekst_len = tekst.length();
        int wzorzec_len = wzorzec.length();
        int hash = (int) Math.pow(d, wzorzec_len-1) % q;
        int p = 0;
        int t = 0;
        for (int i = 0; i < wzorzec_len; i++) {
            p = (d * p + wzorzec.charAt(i)) % q;
            t = (d * t + tekst.charAt(i)) % q;
        }
        int f = 0;
        for (int i = 0; i <= tekst_len-wzorzec_len; i++) {
            if (p == t) {
                int j;
                for (j = 0; j < wzorzec_len; j++) {
                    if (wzorzec.charAt(j) != tekst.charAt(j+i))
                        break;
                }
                if (j == wzorzec_len) {
                    System.out.println("Przesunięcie: " + i);
                    f--;
                }
            }
            f++;
            if (i < tekst_len-wzorzec_len) {
                t = (d * (t-tekst.charAt(i) * hash) + tekst.charAt(i+wzorzec_len)) % q;
                if (t < 0)
                    t = t + q;
            }
        }
        System.out.println(f);
    }
}
