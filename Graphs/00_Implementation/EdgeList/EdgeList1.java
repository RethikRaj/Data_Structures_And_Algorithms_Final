import java.util.*;

// Clean implementation because we create separate classes for Vertex, Edge.

class Vertex {
    int val;

    Vertex(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false; // fix: false not true

        return this.val == ((Vertex) o).val;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(val);
    }

    @Override
    public String toString() {
        return "V(" + val + ")";
    }
}

class Edge {
    Vertex src;
    Vertex dest;
    int wt;
    boolean dir; // True - directed , false - undirected

    Edge(Vertex src, Vertex dest, int wt, boolean dir) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
        this.dir = dir;
    }

    @Override
    public String toString() {
        String arrow = dir ? " -> " : " <-> ";
        return src + arrow + dest + " (wt=" + wt + ")";
    }
}

class Graph {
    Set<Vertex> vertices; // Why set ? See addEdge function
    List<Edge> edges;

    Graph() {
        this.vertices = new HashSet<>();
        this.edges = new ArrayList<>();
    }

    void addVertex(Vertex v) {
        vertices.add(v);
    }

    void addEdge(Vertex src, Vertex dest, int wt, boolean dir) {
        // auto-add vertices if not present ( duplicates automatically handled since vertices is a set)
        vertices.add(src);
        vertices.add(dest);

        edges.add(new Edge(src, dest, wt, dir));

        // if undirected, add reverse edge too
        if (!dir) {
            edges.add(new Edge(dest, src, wt, dir));
        }
    }

    void printGraph() {
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges:");
        for (Edge e : edges) {
            System.out.println("  " + e);
        }
    }
}

public class EdgeList1 {
    public static void main(String[] args) {
        Graph g = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        g.addEdge(v1, v2, 5, false);  
        g.addEdge(v2, v3, 3, false);   
        g.addEdge(v3, v4, 7, false);
        g.addEdge(v1, v4, 2, false);   

        g.printGraph();
    }
}