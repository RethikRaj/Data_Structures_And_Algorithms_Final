import java.util.ArrayList;

public class FirstLargest {
    public static void main(String[] args) {
        
    }

    public static int firstLargest(ArrayList<Integer> arr) {
        int max = Integer.MIN_VALUE;

        for(int num : arr) {
            if(num > max) {
                max = num;
            }
        }

        return max;
    }
}
