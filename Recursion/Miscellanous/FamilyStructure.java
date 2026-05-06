import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class FamilyStructure {
    // Brute Force : TC : O(2^n - 1), SC : O(Width) -> O(2^(n-1))
    public static String bfs(long n, long k) {
        Queue<Character> q = new ArrayDeque<>();
        q.offer('m');
        int level = 1; // level represents generation

        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int child = 1; child <= levelSize; child++) {
                Character front = q.poll();

                if(child == k && level == n) {
                    return front == 'm' ? "Male" : "Female";
                }

                if(front == 'm') {
                    q.offer('m');
                    q.offer('f');
                }else {
                    q.offer('f');
                    q.offer('m');
                }
            }
            level += 1;
        }

        return "";
    }

    // Optimal :
    // Recursive optimal
    // TC : O(n) , SC : O(n)
    public static String f(String root, long n, long k) {
        if(n == 1 && k == 1) return root;

        long temp = ( (long)Math.pow(2, n - 1) ) / 2;

        if(k <= temp) {
            // Move left -> root doesn't change (because male first gives birth to male and female first gives birth to female)
            return f(root, n - 1, k);
        }else {
            // Move right -> root changes (because if root is male, then right is female  and if root is female, then right is male)
            return f(root.equals("Male") ? "Female" : "Male", n - 1, k - temp);
        }
    }

    // iterative optimal
    // TC : O(n), SC : O(1)
    public static String iterative(long n, long k) {
        String curr = "Male";
        for(int generation = 1; generation < n ; generation++) {
            long temp = ( (long)Math.pow(2, n - generation) ) / 2;
            if(k <= temp) {
                // Move left
                continue;
            }else {
                // Move right
                k -= temp;
                curr = curr.equals("Male") ? "Female" : "Male";
            }
        }

        return curr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            // System.out.println(bfs(n, k));

            // System.out.println(f("Male", n , k));

            System.out.println(iterative(n, k));
        }

        sc.close();
    }

}
