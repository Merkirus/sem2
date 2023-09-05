package lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Lampiony {

    private int lampiony;
    private int polaczenia;
    private int[][] macierz;


    public Lampiony() {
        run2();
        if (lampiony >= 1 && lampiony <= 20 && polaczenia >= 0 && polaczenia <= 190)
        {
            System.out.println("Najkrótsza droga wynosi: ");
            System.out.print(Kruskal.najkrotszaDroga(macierz));
        }
        else
            System.out.print("Złe dane wejściowe");

    }

    private void run() {
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String result = "";
        do {
            if (index == 0) {
                System.out.println("Podaj liczbę lampionów oraz połączeń: ");
                result = scanner.nextLine();
                String[] res_arr = result.split(" ");
                lampiony = Integer.parseInt(res_arr[0]);
                polaczenia = Integer.parseInt(res_arr[1]);
                macierz = new int[lampiony][lampiony];
                for (int i = 0; i < lampiony; i++) {
                    for (int j = 0; j < lampiony; j++) {
                        macierz[i][j] = Integer.MAX_VALUE;
                        if (i == j)
                            macierz[i][j] = 0;
                    }
                }
                ++index;
                continue;
            }
            System.out.println("Podaj numery połączonych lampionów oraz długość kabla: ");
            result = scanner.nextLine();
            if (result.equals(""))
                break;
            String[] res_arr = result.split(" ");
            int a = Integer.parseInt(res_arr[0]);
            a -= 1;
            int b = Integer.parseInt(res_arr[1]);
            b -= 1;
            int dl = Integer.parseInt(res_arr[2]);
            macierz[a][b] = dl;
            macierz[b][a] = dl;
        } while (true);
    }

    private void run2() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
            String temp = "";
            temp = br.readLine();
            String[] res_arr = temp.split(" ");
            lampiony = Integer.parseInt(res_arr[0]);
            polaczenia = Integer.parseInt(res_arr[1]);
            macierz = new int[lampiony][lampiony];
            for (int i = 0; i < lampiony; i++) {
                for (int j = 0; j < lampiony; j++) {
                    macierz[i][j] = Integer.MAX_VALUE;
                    if (i == j)
                        macierz[i][j] = 0;
                }
            }

            while ((temp = br.readLine()) != null)
            {
                res_arr = temp.split(" ");
                int a = Integer.parseInt(res_arr[0]);
                a -= 1;
                int b = Integer.parseInt(res_arr[1]);
                b -= 1;
                int dl = Integer.parseInt(res_arr[2]);
                macierz[a][b] = dl;
                macierz[b][a] = dl;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
