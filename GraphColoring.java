import java.util.Arrays;

public class GraphColoring {

    // Function to perform graph coloring
    static void greedyColoring(int[][] graph) {
        int V = graph.length;
        int result[] = new int[V];

        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);

        // Assign the first color to the first vertex
        result[0] = 0;

        boolean available[] = new boolean[V];
        Arrays.fill(available, true);

        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < V; u++) {
            // Process all adjacent vertices and flag their colors as unavailable
            for (int i = 0; i < V; i++) {
                if (graph[u][i] != 0 && result[i] != -1) {
                    available[result[i]] = false;
                }
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < V; cr++) {
                if (available[cr]) {
                    break;
                }
            }

            result[u] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }

        // Print the result
        for (int u = 0; u < V; u++) {
            System.out.println("Vertex " + u + " --->  Color " + result[u]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2661, 0, 0, 0, 0, 0, 0, 2161, 0, 1306, 0},
                {2661, 0, 661, 0, 0, 0, 0, 1532, 1483, 0, 0, 0},
                {0, 661, 0, 1613, 1145, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1613, 0, 725, 0, 338, 0, 0, 0, 0, 0},
                {0, 0, 1145, 725, 0, 383, 1709, 2113, 0, 0, 0, 0},
                {0, 0, 0, 338, 383, 0, 2145, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1709, 2145, 0, 2161, 0, 0, 0, 0},
                {0, 1532, 0, 0, 2113, 0, 2161, 0, 1258, 1983, 0, 0},
                {2161, 1483, 0, 0, 0, 0, 0, 1258, 0, 1225, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1983, 1225,0, 919, 435},
                {1306, 0, 0, 0, 0, 0, 0, 0, 0, 919, 0, 629},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 435, 629, 0}
        };

        greedyColoring(graph);
    }
}
