import java.util.Scanner;

public class SolutionTwo {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] F = new int[n + 10];

        // 1 indexed
        for(int i = 1 ; i <= m ; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            F[l] += 1;
            F[r + 1] -= 1; 
        }

        // Calculate prefix sum of F
        for(int i = 1; i < F.length ; i++) {
            F[i] = F[i] + F[i-1];
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
