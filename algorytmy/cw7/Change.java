package cw7;

import java.util.Scanner;

public class Change {
    public static void main(String[] args) {
        while (input() != 1);
    }

    private static int input() {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        double[] arr_double = new double[2];
        for (int i = 0; i < arr.length; i++)
            arr_double[i] = Double.parseDouble(arr[i]);
        round(arr_double);
        if (arr_double[0] == 0 && arr_double[1] == 0)
            return 1;
        if (arr_double[0] > arr_double[1])
        {
            System.out.println("Not enough money");
            return 0;
        }
        if (arr_double[0] == arr_double[1])
        {
            System.out.println("Exact amount");
            return 0;
        }
        returnChange(arr_double);
        return 0;
    }

    private static void round(double[] arr) {
        for (int i = 0; i < arr.length; i++)
        {
            double integer = Math.floor(arr[i]);
            double rest = arr[i] % 1;
            double lower_rest = rest % 0.1;
            rest *= 10;
            rest = Math.floor(rest);
            rest /= 10;
            if (lower_rest <= 0.02)
                lower_rest = 0.00;
            else if (lower_rest <= 0.05)
                lower_rest = 0.05;
            else if (lower_rest <= 0.07)
                lower_rest = 0.05;
            else
                lower_rest = 0.10;
            rest += lower_rest;
            arr[i] = integer + rest;
            System.out.printf("%.2f ",arr[i]);
            System.out.println();
        }
    }

    private static void returnChange(double[] arr) {
        double result = arr[1] - arr[0];
        result = Math.floor(result);
        int first = (int) (arr[1]*100);
        int second = (int) (arr[0]*100);
        first %= 100;
        second %= 100;
        int result_lower = first - second;
        if (result >= 20)
        {
            int counter = 0;
            while (result >= 20)
            {
                counter++;
                result -= 20;
            }
            System.out.printf("$20*%d ", counter);
        }
        if (result >= 10)
        {
            int counter = 0;
            while (result >= 10)
            {
                counter++;
                result -= 10;
            }
            System.out.printf("$10*%d ", counter);
        }
        if (result >= 5)
        {
            int counter = 0;
            while (result >= 5)
            {
                counter++;
                result -= 5;
            }
            System.out.printf("$5*%d ", counter);
        }
        if (result >= 2)
        {
            int counter = 0;
            while (result >= 2)
            {
                counter++;
                result -= 2;
            }
            System.out.printf("$2*%d ", counter);
        }
        if (result >= 1)
        {
            int counter = 0;
            while (result >= 1)
            {
                counter++;
                result -= 1;
            }
            System.out.printf("$1*%d ", counter);
        }
        if (result_lower >= 50)
        {
            int counter = 0;
            while (result_lower >= 50)
            {
                counter++;
                result_lower -= 50;
            }
            System.out.printf("50c*%d ", counter);
        }
        if (result_lower >= 20)
        {
            int counter = 0;
            while (result_lower >= 20)
            {
                counter++;
                result_lower -= 20;
            }
            System.out.printf("20c*%d ", counter);
        }
        if (result_lower >= 10)
        {
            int counter = 0;
            while (result_lower >= 10)
            {
                counter++;
                result_lower -= 10;
            }
            System.out.printf("10c*%d ", counter);
        }
        if (result_lower >= 5)
        {
            int counter = 0;
            while (result_lower >= 5)
            {
                counter++;
                result_lower -= 5;
            }
            System.out.printf("5c*%d ", counter);
        }
        System.out.println();
    }
}
