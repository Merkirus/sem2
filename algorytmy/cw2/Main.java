package cw2;

public class Main {

    public static void main(String[] args) {

    }

    static int[] nextPascalLine(int n) {

        int[] tablica = {1};

        while (n!=1) {
            int[] newTablica = new int[tablica.length+1];

            newTablica[0] = 1;
            newTablica[tablica.length] = 1;

            for (int i=1; i<tablica.length; i++) {
                newTablica[i] = tablica[i] + tablica[i-1];
            }

            n--;

            tablica = newTablica;
        }

        return tablica;
    }

    static int getSecondSmallest(int[] tablica) throws NoAnswerExcepiton {

        if (tablica.length < 2) throw new NoAnswerExcepiton();

        int last = tablica[0];
        int nextLast;

        int index=0;

        for (; index<tablica.length; index++) {
            if (last != tablica[index]) {
                if (tablica[index] > last) {
                    nextLast = tablica[index];
                } else {
                    nextLast = last;
                    last = tablica[index];
                    break;
                }
            }
        }

        return  last;
    }

    // Podwalone
    static boolean nextPermutation(int[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element greater than the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }
}

class NoAnswerExcepiton extends Exception {
    void printErr() {
        System.out.println("Error");
    }
}
