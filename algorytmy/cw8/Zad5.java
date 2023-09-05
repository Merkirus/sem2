package cw8;

public class Zad5 {
    public static void main(String[] args) {
        String tekst = "aaababaabaababaab";
        String wzorzec = "aabab";
        solve(tekst, wzorzec);
    }

    private static void solve(String tekst, String wzorzec) {
        int n = tekst.length();
        int m = wzorzec.length();
        int[] pi = pi(wzorzec);
        int q = -1;

        for (int i = 0; i < n; i++) {
            while (q >= 0 && wzorzec.charAt(q+1) != tekst.charAt(i)) {
                q = pi[q] - 1;
            }
            if (wzorzec.charAt(q+1) == tekst.charAt(i))
                q += 1;
            if (q == m-1) {
                System.out.println("Przesunięcie: " + (i-m+1));
                q = pi[q] - 1;
            }
        }
    }

    private static int[] pi(String wzorzec) {
        int[] pi = new int[wzorzec.length()];
        int j,i;
        i = 1;
        j = -1;
        for (;i < pi.length; i++) {
            while (j >= 0 && wzorzec.charAt(j+1) != wzorzec.charAt(i))
                j = pi[j] - 1;
            if (wzorzec.charAt(j+1) == wzorzec.charAt(i))
                j += 1;
            pi[i] = j + 1;
        }
        for (int k : pi) {
            System.out.println(k);
        }
        return pi;
    }
}
