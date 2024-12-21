import java.util.*;

public class LinkStateRouting {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of routers:");
        int n = scanner.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the cost matrix (use " + INF + " for no direct connection):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Routing table for router " + i + ":");
            dijkstra(graph, n, i);
        }

        scanner.close();
    }

    private static void dijkstra(int[][] graph, int n, int src) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = selectMinVertex(dist, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != INF && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        printRoutingTable(src, dist, prev, n);
    }

    private static int selectMinVertex(int[] dist, boolean[] visited, int n) {
        int min = INF, minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void printRoutingTable(int src, int[] dist, int[] prev, int n) {
        System.out.println("Destination\tCost\tPath");
        for (int i = 0; i < n; i++) {
            if (i != src) {
                System.out.print(i + "\t\t" + (dist[i] == INF ? "INF" : dist[i]) + "\t");
                printPath(prev, i);
                System.out.println();
            }
        }
    }

    private static void printPath(int[] prev, int node) {
        if (node == -1) {
            return;
        }
        printPath(prev, prev[node]);
        System.out.print(node + " ");
    }
}
