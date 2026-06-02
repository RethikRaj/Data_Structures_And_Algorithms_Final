/*
We need total elements => Go by union by size + path compression
 */

import java.util.Scanner;

public class Main {
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

                dsu.union(a - 1, b - 1);
            } else {
                int x = sc.nextInt();
                Info temp = dsu.get(x - 1);
                temp.min++;
                temp.max++;

                System.out.println(temp.min + " " + temp.max + " " + temp.total);
            }
        }

        sc.close();
    }
}

class Info {
    int min; 
    int max;
    int total;

    Info(int min, int max, int total) {
        this.min = min;
        this.max = max;
        this.total = total;
    }
}

class DSU {
    int n;
    int[] par;
    int[] size;
    int[] maximum;
    int[] minimum;

    DSU(int n) {
        this.n = n;
        this.par = new int[n];
        this.size = new int[n];
        this.minimum = new int[n];
        this.maximum = new int[n];

        for(int i = 0 ; i < n ; i++) {
            par[i] = i;
            minimum[i] = i;
            maximum[i] = i;
            size[i] = 1;
        }
    }

    void union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);

        if(leader_a == leader_b) return;

        if(size[leader_a] >= size[leader_b]) {
            size[leader_a] += size[leader_b];
            par[leader_b] = leader_a;
            maximum[leader_a] = Math.max(maximum[leader_a], maximum[leader_b]);
            minimum[leader_a] = Math.min(minimum[leader_a], minimum[leader_b]);
        }else {
            size[leader_b] += size[leader_a];
            par[leader_a] = leader_b;
            maximum[leader_b] = Math.max(maximum[leader_a], maximum[leader_b]);
            minimum[leader_b] = Math.min(minimum[leader_a], minimum[leader_b]);
        }
    }

    int find(int x) {
        if(x == par[x]) return x;

        return par[x] = find(par[x]);
    }

    Info get(int x) {
        int leader_x = find(x);

        return new Info(minimum[leader_x], maximum[leader_x], size[leader_x]);
    }
}

