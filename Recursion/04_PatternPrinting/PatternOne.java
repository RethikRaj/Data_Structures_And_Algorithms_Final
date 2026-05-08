/*

Pattern : 
n = 4
****
****
****
****

 */

public class PatternOne {

    // Approach 1 :
    public static void printStarsInARow(int starsInEachRow) {
         for(int i = 0; i < starsInEachRow; i++) {
            System.out.print("*");
        }
    }
    public static void f1(int n, int starsInEachRow) {
        if(n == 0) {
            return;
        }

        // My work (1 case me solve ): print one row
        printStarsInARow(starsInEachRow);
        System.out.println();
        // Remaining recursion will handle
        f1(n-1, starsInEachRow);
    }

    // Approach 2: (2 recursive functions)
    // g(n) -> print n stars in a row
    public static void g(int n) {
        if(n == 0) return;
        System.out.print("*");
        g(n-1);
    }
    public static void f2(int n, int starsInEachRow) {
        if(n == 0) return;

        // My work (1 case me solve ): print one row
        g(starsInEachRow);
        System.out.println();
        f2(n-1, starsInEachRow);
    }


    // Approach 3 : 
    public static void f3(int row, int col, int n) {
        if(row >= n) return;

        if(col >= n) {
            System.out.println();
            f3(row + 1, 0, n);
            return;
        }
        // My work print 1 star at [row, col]
        System.out.print("*");
        // Recursion
        f3(row, col + 1, n);
    }

    public static void main(String[] args) {
        int n = 5;
        
        // f1(n, n);
        // f2(n, n);
        f3(0, 0, n);
    }
}
