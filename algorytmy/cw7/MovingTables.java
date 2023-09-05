package cw7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MovingTables {

    private static ArrayList<ArrayList<Pair>> test = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test_cases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < test_cases; i++) {
            test.add(new ArrayList<>());
            input(i);
        }
        solve();
    }

    private static void input(int index) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            String[] arr = scanner.nextLine().split(" ");
            test.get(index).add(new Pair(Integer.parseInt(arr[0])-1, Integer.parseInt(arr[1])-1));
        }
    }

    private static void solve() {
        for (int i = 0; i < test.size(); i++) {
            int minutes = 0;
            test.get(i).sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.compareTo(o2);
                }
            });
            while (test.get(i).size() != 0) {
                int[] rooms_taken = new int[400];
                int size = test.get(i).size();
                for (int j = 0; j < size; j++) {
                    Pair pair = test.get(i).get(0);
                    if (pair.a % 2 != 0)
                        pair.a -= 1;
                    if (pair.b % 2 == 0)
                        pair.b += 1;
                    boolean shared = false;
                    for (int k = pair.a; k <= pair.b; k++)
                        if (rooms_taken[k] == 1) {
                            shared = true;
                            break;
                        }
                    if (shared)
                        continue;
                    for (int k = pair.a; k <= pair.b; k++)
                        rooms_taken[k] = 1;
                    test.get(i).remove(0);
                }
                minutes++;
            }
            System.out.println("Test " + i + " Minutes: " + (10*minutes));
        }
    }
}

class Pair implements Comparable<Pair> {

    int a;
    int b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int length() {
        return Math.abs(a-b);
    }

    @Override
    public int compareTo(Pair o) {
        if (this.length() == o.length())
            return 0;
        return this.length() < o.length() ? -1 : 1;
    }
}
