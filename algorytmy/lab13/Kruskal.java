package lab13;

public class Kruskal {
    static int najkrotszaDroga(int[][] macierz) {
        int[][] A = new int[macierz.length][macierz.length];
        initA(A);
        int[][][] las = new int[macierz.length][macierz.length][macierz.length];
        initLas(las);
        boolean[][] conn = new boolean[macierz.length][macierz.length];
        initConn(conn);
        int[][] krawedzie = posortujKrawedzie(macierz);
        for (int[] krawedz : krawedzie)
        {
            int a = krawedz[0];
            int b = krawedz[1];
            int dl = krawedz[2];
            if (!conn[a][b])
            {
                unionA(A, a, b, dl);
            }
            unionLas(las, a, b, dl, conn);
        }
        return dlugosc(A);
    }

    private static void initA(int[][] A) {
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {
                A[i][j] = Integer.MAX_VALUE;
                if (i == j)
                    A[i][j] = 0;
            }
        }
    }

    private static void initLas(int[][][] las) {
        for (int i = 0; i < las.length; i++)
            for (int j = 0; j < las.length; j++)
                for (int k = 0; k < las.length; k++)
                {
                    las[i][j][k] = Integer.MAX_VALUE;
                    if (j == k && i == j)
                        las[i][j][k] = 0;
                }
    }

    private static void initConn(boolean[][] conn) {
        for (int i = 0; i < conn.length; i++)
            for (int j = 0; j < conn.length; j++)
                conn[i][j] = false;
    }

    private static int dlugosc(int[][] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 1 + i; j < A.length; j++)
                if (A[i][j] != Integer.MAX_VALUE)
                    sum += A[i][j];
        }
        return sum;
    }

    private static boolean equal(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a.length; j++)
            {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }

    private static void unionLas(int[][][] las, int a, int b, int dl, boolean[][] conn) {
        las[a][a][b] = dl;
        las[b][b][a] = dl;
        las[a][b] = las[b][b];
        for (int i = 0; i < conn.length; i++)
            if (conn[b][i])
                las[a][i] = las[i][i];
        conn[a][b] = true;
        conn[b][a] = true;
        for (int i = 0; i < conn.length; i++)
            if (conn[b][i])
                conn[a][i] = true;
        for (int i = 0; i < conn.length; i++)
            if (conn[a][i])
                conn[i] = conn[a];
        for (int i = 0; i < conn.length; i++)
            if (conn[a][i])
                for (int j = 0; j < las.length; j++)
                    System.arraycopy(las[a][j], 0, las[i][j], 0, las.length);
    }

    private static void unionA(int[][] A, int a, int b, int dl) {
        A[a][b] = dl;
        A[b][a] = dl;
    }

    private static int[][] posortujKrawedzie(int[][] macierz) {
        int x = 0;
        for (int i = 0; i < macierz.length; i++)
            x += i;
        int[][] result = new int[x][3];
        int index = 0;
        for (int i = 0; i < macierz.length; i++)
        {
            for (int j = 1 + i; j < macierz.length; j++)
            {
                result[index][0] = i;
                result[index][1] = j;
                result[index][2] = macierz[i][j];
                ++index;
            }
        }
        for (int i = 0; i < result.length; i++)
        {
            for (int right = 1; right < result.length - i; right++)
            {
                int left = right - 1;
                if (result[left][2] > result[right][2])
                {
                    int[] temp = new int[3];
                    System.arraycopy(result[left], 0, temp, 0, temp.length);
                    result[left] = result[right];
                    result[right] = temp;
                }
            }
        }
        return result;
    }
}
