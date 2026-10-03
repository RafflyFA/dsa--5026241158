package lw03.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //BAGIAN PROBLEM 1
        List<String> playlist = new ArrayList<>();
        Scanner playlistScanner = new Scanner(new File("src/lw03/prelab/playlist.txt"));
        while (playlistScanner.hasNextLine()) {
            String line = playlistScanner.nextLine();
            String[] parts = line.split(" ", 2);
            String operation = parts[0];
            if (operation.equals("ADD")) {
            String song = parts[1];
            playlist.add(song);

            } else if (operation.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                String song = insertParts[1];
                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);
            }
        }
        playlistScanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
        System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println(); 

        //BAGIAN PROBLEM 2
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;
        Scanner participantScanner = new Scanner(new File("src/lw03/prelab/participants.txt"));
        while (participantScanner.hasNextLine()) {
            String name = participantScanner.nextLine();
            if (participants.contains(name)) {
            duplicateRegistrations++;
            } else {
            participants.add(name);
            }
        }
        participantScanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String participant : participants) {
        System.out.println(number + ". " + participant);
        number++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
        System.out.println();

        //BAGIAN PROBLEM 3
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner inventoryScanner = new Scanner(new File("src/lw03/prelab/inventory.txt"));
        while (inventoryScanner.hasNextLine()) {
            String line = inventoryScanner.nextLine();
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product,currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)
                    && inventory.get(product) >= quantity) {
                    int currentStock = inventory.get(product);
                    inventory.put(product,currentStock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        inventoryScanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}