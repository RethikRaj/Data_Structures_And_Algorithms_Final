/*

Pattern : 
n = 4
****
***
**
*

 */

public class PatternThree {
    public static void f(int row, int col, int n) {
        if(row >= n) return;

        if(col >= n-row) {
            System.out.println();
            f(row + 1, 0, n);
            return;
        }
        // My work print 1 star at [row, col]
        System.out.print("*");
        // Recursion
        f(row, col + 1, n);
    }
    public static void main(String[] args) {
        int n = 5;
        f(0,0,n);
    }
}
