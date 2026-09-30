package lw02.unguided;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Code untuk nyimpen semua order
        LinkedList<String[]> orders = new LinkedList<>();
        //Code untuk nyimpen stock makanan
        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});
        //Code untuk nyimpen stock minuman
        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        //Code untuk baca file orders.txtnya
        Scanner scanner = new Scanner(new File("src/lw02/unguided/orders.txt"));
        while (scanner.hasNext()) {
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();
            String[] order = {name, food, drink, table};
            orders.add(order);
        }
        scanner.close();

        //Code untuk buat queue dari ordersnya
        Queue<String[]> queue = new LinkedList<>();
        while (!orders.isEmpty()) {
            queue.add(orders.remove());
        }
        //Code untuk nyimpen order yang berhasil
        LinkedList<String[]> successfulOrders = new LinkedList<>();
        //Code untuk nyimpen order yang gagal
        Stack<String[]> failedOrders = new Stack<>();
        //Code untuk proses order
        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];
            boolean foodAvailable = true;
            boolean drinkAvailable = true;
            //Code untuk ngecek stock makanan
            if (!food.equals("-")) {
                for (int i = 0; i < foods.size(); i++) {
                    String[] item = foods.get(i);
                    if (item[0].equals(food)) {
                        int stock = Integer.parseInt(item[1]);
                        if (stock <= 0) {
                            foodAvailable = false;
                        }
                    }
                }
            }
            //Code untuk ngecek stock minuman
            if (!drink.equals("-")) {
                for (int i = 0; i < drinks.size(); i++) {
                    String[] item = drinks.get(i);
                    if (item[0].equals(drink)) {
                        int stock = Integer.parseInt(item[1]);
                        if (stock <= 0) {
                            drinkAvailable = false;
                        }
                    }
                }
            }
            //Ini code kalo semua item tersedia
            if (foodAvailable && drinkAvailable) {
                //Ngurangin stock makanannya ini
                if (!food.equals("-")) {
                    for (int i = 0; i < foods.size(); i++) {
                        String[] item = foods.get(i);
                        if (item[0].equals(food)) {
                            int stock = Integer.parseInt(item[1]);
                            item[1] = String.valueOf(stock - 1);
                        }
                    }
                }
                //Ngurangin stock minumannya ini
                if (!drink.equals("-")) {
                    for (int i = 0; i < drinks.size(); i++) {
                        String[] item = drinks.get(i);
                        if (item[0].equals(drink)) {
                            int stock = Integer.parseInt(item[1]);
                            item[1] = String.valueOf(stock - 1);
                        }
                    }
                }
                //Untuk masukkin order ke successful orders
                successfulOrders.add(order);
            } else {
                //Code untuk masukkin order gagal ke Stack
                failedOrders.push(order);
            }
        }
        System.out.println();
        //Code untuk nampilin successful ordersnya
        System.out.println("=== Successfully Processed Orders ===");

        for (int i = 0; i < successfulOrders.size(); i++) {
            String[] order = successfulOrders.get(i);
            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
        System.out.println();
        //Code untuk nampilin remaining food stocknya
        System.out.println("=== Remaining Food Stock ===");

        for (int i = 0; i < foods.size(); i++) {
            String[] item = foods.get(i);
            System.out.println(item[0] + " : " + item[1]);
        }
        System.out.println();
        //Code untuk nampilin remaining drink stocknya
        System.out.println("=== Remaining Drink Stock ===");

        for (int i = 0; i < drinks.size(); i++) {
            String[] item = drinks.get(i);
            System.out.println(item[0] + " : " + item[1]);
        }
        System.out.println();
        //Code untuk nampilin failed ordersnya di LIFO order
        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}