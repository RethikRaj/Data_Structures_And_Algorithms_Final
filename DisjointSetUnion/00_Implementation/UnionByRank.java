import java.util.Scanner;

class DSUbyRank {
    int n;
    int[] par;
    int[] rank;

    DSUbyRank(int n) {  
        this.n = n;
        par = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;   // Each node is its own parent initially
            rank[i] = 0;  // Initial rank is 0 
        }
    }

    int find(int x) {
        if(x == par[x]) return x;
        int leader_x = find(par[x]);
        par[x] = leader_x; // Backtrack - Path Compression
        return leader_x;
    }

    void union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);

        if(leader_a == leader_b) return; // Already in same group

        if(rank[leader_a] == rank[leader_b]) {
            rank[leader_a] += 1;
            par[leader_b] = leader_a;
        }else if(rank[leader_a] > rank[leader_b]) {
            par[leader_b] = leader_a;
        }else {
            par[leader_a] = leader_b;
        }
    }
}

public class UnionByRank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        DSUbyRank dsu = new DSUbyRank(n);

        while(true) {
            String input = sc.next();

            if(input.equals("exit")) break;

            if(input.equals("Union")) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                dsu.union(a, b);
            }else if(input.equals("Find")) {
                int x = sc.nextInt();
                System.out.println(dsu.find(x));
            }else {
                System.out.println("Invalid op");
            }
        }

        sc.close();
    }
}

