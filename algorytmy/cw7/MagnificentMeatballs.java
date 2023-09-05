package cw7;

import java.util.ArrayList;
import java.util.Scanner;

public class MagnificentMeatballs {

    private static ArrayList<ArrayList<Integer>> guests = new ArrayList<>();

    public static void main(String[] args) {
        int current_table = 0;
        while (input(current_table) != 1)
            current_table++;
        solve();
    }

    private static int input(int current_table) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        int[] arr_int = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            arr_int[i] = Integer.parseInt(arr[i]);
        if (arr_int[0] == 0)
            return 1;
        guests.add(new ArrayList<>());
        for (int i = 0; i < arr_int[0]; i++)
            guests.get(current_table).add(0);
        for (int i = 0; i < arr_int[0]; i++)
            guests.get(current_table).set(i, arr_int[i+1]);
        return 0;
    }

    private static void solve() {
        for (int i = 0; i < guests.size(); i++) {
            ArrayList<Integer> table = guests.get(i);
            int indexSam = -1;
            int meatballsSam = 0;
            int indexElla = table.size();
            int meatballsElla = 0;

            // Sam's turn - false, Ella's turn - true

            boolean ellasTurn = false;

            while (true) {
                if (ellasTurn) {
                    indexElla--;
                    if (indexElla == indexSam) {
                        indexElla++;
                        break;
                    }
                    meatballsElla += table.get(indexElla);
                    if (meatballsSam < meatballsElla)
                        ellasTurn = false;
                } else {
                    indexSam++;
                    if (indexElla == indexSam) {
                        indexSam--;
                        break;
                    }
                    meatballsSam += table.get(indexSam);
                    if (meatballsElla < meatballsSam)
                        ellasTurn = true;
                }
            }

            if (meatballsElla != meatballsSam)
                System.out.println("No equal partitioning");
            else
                System.out.println("Sam stops at position " + (indexSam+1) + 
                " and Ella stops at position " + (indexElla+1));
        }
    }
}
