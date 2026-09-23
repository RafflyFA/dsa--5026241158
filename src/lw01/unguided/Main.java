package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("washes.txt"));
        int total = scanner.nextInt();
        WashService[] washes = new WashService[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                washes[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                washes[i] = new CarWash(id, days);
            }

            final int charge = washes[i].calculateCharge(units);
        }

        scanner.close();
        for (WashService wash : washes) {
            System.out.println(
                wash.getId() + " | " + wash.label() + " | " + wash.calculateCharge(
                )
            );
        }
    }
}