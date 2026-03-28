import java.util.ArrayList;

public class SecondLargest {
    public static void main(String[] args) {
    }

    public static int secondLargest(ArrayList<Integer> arr) {
        int n = arr.size();

        if(n < 2){
            return -1; // There is no second largest
        }

        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int num = arr.get(i);
            if(num > firstLargest){
                secondLargest = firstLargest;
                firstLargest = num;
            }
            else if(num != firstLargest && num > secondLargest) {
                secondLargest = num;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            return -1;
        }
        return secondLargest;
    }
}
