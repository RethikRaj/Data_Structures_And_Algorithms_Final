/*
Problem : 
You are given an integer ‘N’, you need to find the count of binary strings of length equal to ‘N’ that do not contain consecutive 1’s
 */

public class Solution {
    public static int solutionOne(int n) {
        if (n == 1) return 2;
        if (n == 2) return 3;

        return solutionOne(n-1) + solutionOne(n-2);
    }

    // Using include/exclude pattern
    public static int solutionTwo(int n, int lastBit) {
        // Base case: all bits placed, valid string found
        if (n == 0) return 1;

        int count = 0;
        // We can include zero always
        count += solutionTwo(n-1, 0);

        // We can include one only when lastBit != 1 (that is either no last bit or lastbit == 0)
        if(lastBit != 1) {
            count += solutionTwo(n-1, 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(solutionOne(n));

        System.out.println(solutionTwo(n, Integer.MIN_VALUE));
    }
}
