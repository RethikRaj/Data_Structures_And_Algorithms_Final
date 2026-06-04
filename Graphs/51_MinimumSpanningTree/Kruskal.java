import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Edge {
    int src;
    int dest;
    int wt;
    
    Edge(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}

class Result {
    private static int[] par;
    private static int[] size;
    
    private static int find(int x) {
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }
    
    // returns true if cycle, else false
    private static boolean union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);
        
        if(leader_a == leader_b) return true;
        
        if(size[leader_a] >= size[leader_b]) {
            size[leader_a] += size[leader_b];
            par[leader_b] = leader_a;
        }else {
            size[leader_b] += size[leader_a];
            par[leader_a] = leader_b;
        }
        return false;
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.wt - e2.wt);
        
        for(int i = 0 ; i < gFrom.size(); i++) {
            pq.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }
        
        // Initialize DSU
        par = new int[gNodes + 1];
        size = new int[gNodes + 1];
        // Vertices are 1 based -> V lies in range [1, gNodes]            
        for(int i = 1; i <= gNodes; i++) {
            par[i] = i;
            size[i] = 1;
        }
        
        // Number of edges in MST = V - 1
        int requiredEdges = gNodes - 1;
        int totalWeight = 0;
        
        while(requiredEdges > 0 && !pq.isEmpty()) {
            Edge front = pq.poll();
            
            // Checking whether including this edge causes a cycle or not .
            boolean causesCycle = union(front.src, front.dest);
            
            if(causesCycle) continue;
            else {
                totalWeight += front.wt;
                requiredEdges -= 1;
            }
        }
        return totalWeight;
    }
}

