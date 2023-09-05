package cw8;

public class Zad3 {
    public static void main(String[] args) {
        String tekst = "aaababaabaababaab";
        String wzorzec = "aabab";
        solve(tekst, wzorzec);
    }

    private static void solve(String tekst, String wzorzec) {
        int n = tekst.length();
        int q = 0;
        char[] alfabet = {'a', 'b'};
        int[][] sigma = fnc(wzorzec,alfabet);
        for (int i = 0; i < n; i++) {
            q = sigma[q][tekst.charAt(i)];
            if (q == wzorzec.length()) {
                System.out.println("Przesunięcie: " + (i+1-wzorzec.length()));
            }
        }
    }

    private static int[][] fnc(String wzorzec, char[] alfabet) {
        int m = wzorzec.length();
        int[][] sigma = new int[m+1][256];
        for (int q = 0; q <= m; q++) {
            for (char a : alfabet) {
                int k = q+1;
                while (!isSuffix(wzorzec, k, q, a)) {
                    k -= 1;
                }
                sigma[q][a] = k;
            }
        }
        return sigma;
    }

    private static boolean isSuffix(String wzorzec, int k, int q, char a) {
        char[] left = new char[k];
        int i = 0;
        for (char c : wzorzec.toCharArray()) {
            if (i == k)
                break;
            left[i] = c;
            i++;
        }
        i = 0;
        char[] right = new char[q+1];
        for (char c : wzorzec.toCharArray()) {
            if (i == q)
                break;
            right[i] = c;
            i++;
        }
        right[i] = a;

        if (left.length > right.length)
            return false;

        for (i = 0; i < k; i++) {
            if (left[i] != right[q+1-k+i])
                return false;
        }

        return true;
    }
}
