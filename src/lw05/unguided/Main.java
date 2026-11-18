
package lw05.unguided;

import java.util.*;

public class Main {

    static int[][] graph;
    static int size;
    static String[] names;

    static int[] bfsLevel;
    static int[] bfsPrev;
    static int[] dijkstraPrev;

    // Adds a two-way track (complete, do not change)
    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    // BFS: finds the route with the fewest stops
    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        bfsLevel = new int[size];
        bfsPrev = new int[size];

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0 && !visited.contains(next)) {
                    visited.add(next);
                    bfsLevel[next] = bfsLevel[current] + 1;
                    bfsPrev[next] = current;
                    queue.add(next);
                }
            }
        }

        return order;
    }

    // Prints stations level by level
    static void printLevels(List<Integer> order) {
        int lastLevel = bfsLevel[order.get(order.size() - 1)];

        for (int level = 0; level <= lastLevel; level++) {
            System.out.println();
            System.out.print("Level " + level + ": ");

            boolean first = true;

            for (int i = 0; i < order.size(); i++) {
                int station = order.get(i);

                if (bfsLevel[station] == level) {
                    if (!first) {
                        System.out.print(", ");
                    }

                    System.out.print(names[station]);
                    first = false;
                }
            }
        }

        System.out.println();
    }

    // Dijkstra: finds the fastest route
    static void dijkstraAlg(int start) {
        int[] dist = new int[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        boolean[] visited = new boolean[size];
        dijkstraPrev = new int[size];

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(visited, dist);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0
                        && !visited[next]) {

                    int newDist = dist[current] + graph[current][next];

                    if (newDist < dist[next]) {
                        dist[next] = newDist;
                        dijkstraPrev[next] = current;
                    }
                }
            }
        }
    }

    // Finds the unvisited station with the smallest distance
    static int findTheNextNode(boolean[] visited, int[] dist) {
        int min = Integer.MAX_VALUE;
        int closest = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                closest = i;
            }
        }

        return closest;
    }

    // Rebuilds the route using prev[]
    static List<Integer> buildPath(int current, int[] prev, int start) {
        List<Integer> path;

        if (current == start) {
            path = new ArrayList<>();
        } else {
            path = buildPath(prev[current], prev, start);
        }

        path.add(current);
        return path;
    }

    // Calculates the total travel time
    static int pathTime(List<Integer> path) {
        int total = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            total += graph[path.get(i)][path.get(i + 1)];
        }

        return total;
    }

    // Prints the route (complete, do not change)
    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // Prints route information (complete, do not change)
    static void printSummary(String title, List<Integer> path) {
        System.out.println("=== " + title + " ===");
        printPath(path);

        System.out.println("Stops: " + (path.size() - 1)
                + " | Time: " + pathTime(path) + " min");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(Main.class.getResourceAsStream("metro1.txt"));
        size = input.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = input.next();
        }
        graph = new int[size][size];
        int E = input.nextInt();

        for (int i = 0; i < E; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            int minutes = input.nextInt();
            addEdge(from, to, minutes);
        }
        int start = input.nextInt();
        int destination = input.nextInt();
        input.close();

        List<Integer> bfsOrder = bfs(start);
        System.out.println("=== Station Levels from " + names[start] + " ===");
        printLevels(bfsOrder);
        List<Integer> fewestPath = buildPath(destination, bfsPrev, start);
        printSummary("Fewest Stops Route from " + names[start] + " to " + names[destination], fewestPath);
        dijkstraAlg(start);
        List<Integer> fastestPath = buildPath(destination, dijkstraPrev, start);
        printSummary("Fastest Route from " + names[start] + " to " + names[destination], fastestPath);
        System.out.println("=== Comparison ===");
        if (fewestPath.equals(fastestPath)) {
            System.out.println("Both routes are the same.");
        } else {
            int timeSaved = pathTime(fewestPath) - pathTime(fastestPath);
            int extraStops = fastestPath.size() - fewestPath.size();
            System.out.println("The fastest route saves " + timeSaved + " min but needs " + extraStops + " more stops.");
        }
    }
}
