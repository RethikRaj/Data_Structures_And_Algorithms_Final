import java.util.*;

class Graph {
    private int V; // Vertices are : [0, V - 1]
    private List<List<Integer>> adjList;

    Graph(int V) {
        this.V = V;
        this.adjList = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            // adjList.add(new LinkedList<>());
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int src, int dest, boolean dir) {
        if (src < 0 || dest < 0 || src >= V || dest >= V) {
            throw new IllegalArgumentException("Invalid vertex: " + src + ", " + dest);
        }
        adjList.get(src).add(dest);

        if(!dir) {
            adjList.get(dest).add(src);
        }
    }

    void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print("Vertex " + i + " : ");
            List<Integer> neighbors = adjList.get(i);

            for (int j = 0; j < neighbors.size(); j++) {
                System.out.print(neighbors.get(j));
                if (j < neighbors.size() - 1) System.out.print(" -> ");
            }

            System.out.println();
        }
    }   
}


public class AdjListUnWeighted {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, false);
        g.addEdge(0, 2, false);
        g.addEdge(1, 2, false);

        g.printGraph();
    }
}
