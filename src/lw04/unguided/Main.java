
package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/lw04/unguided/parcels.txt"));
        int N = input.nextInt();
        int[] parcels = new int[N];
        for (int i = 0; i < N; i++) {
            parcels[i] = input.nextInt();
        }
        int M = input.nextInt();
        int[] targets = new int[M];
        for (int i = 0; i < M; i++) {
            targets[i] = input.nextInt();
        }
        input.close();

        int[] afterTwoPasses = new int[N];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (parcels[j] < parcels[j + 1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }
            if (i == 1) {
                for (int j = 0; j < N; j++) {
                    afterTwoPasses[j] = parcels[j];
                }
            }
        }
        System.out.println("==== After 2 Passes ====");

        for (int i = 0; i < N; i++) {
            System.out.print(afterTwoPasses[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("==== Fully Sorted ====");

        for (int i = 0; i < N; i++) {
            System.out.print(parcels[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("==== Search Results ====");

        for (int i = 0; i < M; i++) {
            int target = targets[i];
            int low = 0;
            int high = N - 1;
            int steps = 0;
            int foundIndex = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {
                    foundIndex = mid;
                    break;
                } else if (target > parcels[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index "+ foundIndex + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: "+ steps + ")");
            }
        }
    }
}
