//Nama      : Raffly Faiza Altariq
//NRP       : 5026241158
//Kelas     : ASD - B
//PreLab 2 - LinkedList, Stack, Queue

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
 
public class PreLab {
    public static void main(String[] args) throws FileNotFoundException {

        LinkedList<String[]> transactions = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        Scanner scanner = new Scanner(new File("transactions.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            transactions.add(data);
        }

        scanner.close();

        for (String[] transaction : transactions) {
            String name = transaction[0];
            boolean exists = false;

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customers.add(new String[]{name, "0"});
            }
        }

        queue.addAll(transactions);

        while (!queue.isEmpty()) {
            String[] currentTransaction = queue.poll();

            String currentName = currentTransaction[0];
            String type = currentTransaction[1];
            int amount = Integer.parseInt(currentTransaction[2]);

            if (type.equals("DEPOSIT")) {
                for (String[] customer : customers) {
                    if (customer[0].equals(currentName)) {
                        int balance = Integer.parseInt(customer[1]);
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                        break;
                    }
                }
            }
            else if (type.equals("WITHDRAW")) {
                for (String[] customer : customers) {
                    if (customer[0].equals(currentName)) {
                        int balance = Integer.parseInt(customer[1]);
                        if (balance >= amount) {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        } else {
                            failedTransactions.push(currentTransaction);
                        }
                        break;
                    }
                }
            }
        }
        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }
        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}