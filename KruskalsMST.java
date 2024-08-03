// Java program for Kruskal's algorithm

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalsMST {

    // Defines edge structure
    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight)
        {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Defines subset element structure
    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // Starting point of program execution
    public static void main(String[] args)
    {
        int V = 12;


        int graph[][] = new int[][]{
                {0, 2661, 0, 0, 0, 0, 0, 0, 2161, 0, 1306, 0},
                {2661, 0, 661, 0, 0, 0, 0, 1532, 1483, 0, 0, 0},
                {0, 661, 0, 1613, 1145, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1613, 0, 725, 0, 338, 0, 0, 0, 0, 0, 0},
                {0, 0, 1145, 725, 0, 383, 1709, 2113, 0, 0, 0, 0},
                {0, 0, 0, 338, 383, 0, 2145, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1709, 2145, 0, 2161, 0, 0, 0, 0},
                {0, 1532, 0, 0, 2113, 0, 2161, 0, 1258, 1983, 0, 0},
                {2161, 1483, 0, 0, 0, 0, 0, 1258, 0, 1225, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1983, 1225,0, 919, 435},
                {1306, 0, 0, 0, 0, 0, 0, 0, 0, 919, 0, 629},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 435, 629, 0}
        };

        List<Edge> graphEdges = new ArrayList<Edge>();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    graphEdges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        // Sort the edges in non-decreasing order
        // (increasing with repetition allowed)
        graphEdges.sort(new Comparator<Edge>() {
            @Override public int compare(Edge o1, Edge o2)
            {
                return o1.weight - o2.weight;
            }
        });

        kruskals(V, graphEdges);
    }

    // Function to find the MST
    private static void kruskals(int V, List<Edge> edges)
    {
        int j = 0;
        int noOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[V];

        // Allocate memory for results
        Edge results[] = new Edge[V];

        // Create V subsets with single elements
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.src);
            int y = findRoot(subsets, nextEdge.dest);

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }

        // Print the contents of result[] to display the
        // built MST
        System.out.println(
                "Following are the edges of the constructed MST:");
        int minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {
            System.out.println(results[i].src + " -- "
                    + results[i].dest + " == "
                    + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y)
    {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i)
    {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
}
// This code is contributed by Salvino D'sa
