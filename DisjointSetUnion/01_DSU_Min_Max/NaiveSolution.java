import java.util.Scanner;


public class NaiveSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        DSU dsu = new DSU(n);

        for(int i = 0; i < m; i++) {
            String input = sc.next();

            if(input.equals("union")) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                dsu.union(a - 1, b - 1); // input is 1- base
            } else {
                int x = sc.nextInt();
                Info temp = dsu.get(x - 1);
                temp.min++; // Input is 1-based so output also should be 1- based
                temp.max++;

                System.out.println(temp.min + " " + temp.max + " " + temp.total);
            }
        }

        sc.close();
    }
}

class Info {
    int max;
    int min;
    int total;

    Info(int max, int min, int total) {
        this.max = max;
        this.min = min;
        this.total = total;
    }
}

class DSU {
    private int n;
    private int[] par;
    private int[] rank;

    DSU(int n) {
        this.n = n;
        par = new int[n];
        rank = new int[n];
        for(int i = 0; i < n;i++) {
            par[i] = i;
            rank[i] = 0;
        }
    }

    void union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);

        if(leader_a == leader_b) return;

        if(rank[leader_a] == rank[leader_b]) {
            rank[leader_a]++;
            par[leader_b] = leader_a;
        }else if(rank[leader_a] > rank[leader_b]) {
            par[leader_b] = leader_a;
        }else {
            par[leader_a] = leader_b;
        }
    }

    int find(int x) {
        if(x == par[x]) return x;

        return par[x] = find(par[x]);
    }

    Info get(int x) {
        int leader_x = find(x);

        Info result = new Info(x, x, 0);
        for(int i = 0 ; i < n ; i++) {
            if(leader_x == find(i)) {
                result.total += 1;
                result.max = Math.max(result.max, i);
                result.min = Math.min(result.min, i);
            }
        }

        return result;
    }
}
