import java.util.*;

public class DistanceVectorRouting {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of routers:");
        int n = scanner.nextInt();

        int[][] costMatrix = new int[n][n];
        System.out.println("Enter the cost matrix (use " + INF + " for no direct connection):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = scanner.nextInt();
                if (i != j && costMatrix[i][j] == 0) {
                    costMatrix[i][j] = INF;
                }
            }
        }

        distanceVectorRouting(costMatrix, n);

        scanner.close();
    }

    private static void distanceVectorRouting(int[][] costMatrix, int n) {
        int[][] distance = new int[n][n];
        int[][] nextHop = new int[n][n];

        // Initialize distances and next hops
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = costMatrix[i][j];
                if (costMatrix[i][j] != INF && i != j) {
                    nextHop[i][j] = j;
                } else {
                    nextHop[i][j] = -1;
                }
            }
        }

        // Update distances using the distance vector algorithm
        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (distance[i][k] != INF && distance[k][j] != INF && distance[i][k] + distance[k][j] < distance[i][j]) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);

        printRoutingTables(distance, nextHop, n);
    }

    private static void printRoutingTables(int[][] distance, int[][] nextHop, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouting table for router " + i + ":");
            System.out.println("Destination\tCost\tNext Hop");
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.println(j + "\t\t" + (distance[i][j] == INF ? "INF" : distance[i][j]) + "\t" + (nextHop[i][j] == -1 ? "-" : nextHop[i][j]));
                }
            }
        }
    }
}
