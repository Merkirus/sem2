package lab8;

import java.util.Random;

public class App {
	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println("#####");
		System.out.println("5,000");
	{
			final int size = 5000;
			int wersja1 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell15.sort(arr);
				long end = System.nanoTime();
				wersja1 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 1: " + wersja1/5);
			int wersja2 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell25.sort(arr);
				long end = System.nanoTime();
				wersja2 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 2: " + wersja2/5);
			int wersja3 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell35.sort(arr);
				long end = System.nanoTime();
				wersja3 += (end - start) / 1000000;
			}
			System.out.println("Średni czas werjsi 3: " + wersja3/5);
	}
		System.out.println("######");
		System.out.println("10,000");
	{
			final int size = 10000;
			int wersja1 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell15.sort(arr);
				long end = System.nanoTime();
				wersja1 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 1: " + wersja1/5);
			int wersja2= 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell25.sort(arr);
				long end = System.nanoTime();
				wersja2 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 2: " + wersja2/5);
			int wersja3 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell35.sort(arr);
				long end = System.nanoTime();
				wersja3 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 3: " + wersja3/5);
	}
		System.out.println("######");
		System.out.println("50,000");
	{
			final int size = 50000;
			int wersja1 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell15.sort(arr);
				long end = System.nanoTime();
				wersja1 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 1: " + wersja1/5);
			int wersja2 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell25.sort(arr);
				long end = System.nanoTime();
				wersja2 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 2: " + wersja2/5);
			int wersja3 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell35.sort(arr);
				long end = System.nanoTime();
				wersja3 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 3: " + wersja3/5);
	}
		System.out.println("#######");
		System.out.println("100,000");
	{
			final int size = 100000;
			int wersja1 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell15.sort(arr);
				long end = System.nanoTime();
				wersja1 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 1: " + wersja1/5);
			int wersja2 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell25.sort(arr);
				long end = System.nanoTime();
				wersja2 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 2: " + wersja2/5);
			int wersja3 = 0;
			for (int j = 0; j < 5; j++) {
				int[] arr = new int[size];
				for (int i = 0; i < arr.length; i++)
					arr[i] = rand.nextInt(size);
				long start = System.nanoTime();
				Shell35.sort(arr);
				long end = System.nanoTime();
				wersja3 += (end - start) / 1000000;
			}
			System.out.println("Średni czas wersji 3: " + wersja3/5);
	}
	}
}
