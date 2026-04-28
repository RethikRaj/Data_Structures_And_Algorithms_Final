import java.util.Scanner;

public class SolutionOne {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] L = new int[n + 1];
        int[] R = new int[n + 1];

        // 1 indexed
        for(int i = 1 ; i <= m ; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            L[l] += 1;
            R[r] += 1;
        }

        // Process L and R to form F
        int[] F = new int[n + 1];
        F[1] = L[1];
        for(int i = 2; i <= n ; i++) {
            F[i] = L[i] + F[i-1] - R[i-1];
        }

        // Create C array -> C[i] = No.of.boxes with i coins (imagine it as map)
        int[] C = new int[1000005];
        for(int coin : F) {
            C[coin] += 1;
        }

        // suffix sum
        for(int i = C.length - 2 ; i >= 0 ; i--) {
            C[i] = C[i] + C[i + 1];
        }

        int q = sc.nextInt();

        for(int i = 0 ; i < q; i++) {
            int x = sc.nextInt();

            int ans = C[x];
            System.out.println(ans);
        }
    }
}
