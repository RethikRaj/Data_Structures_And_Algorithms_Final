import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basics {
    // The function prints array from [index, n-1]
    public static void print(List<Integer> arr, int index) {
        if (index >= arr.size()) return;

        System.out.println(arr.get(index));
        print(arr, index + 1);
    }

    // The function return the maximum element in range [index, n-1]
    public static int getMax(List<Integer> arr, int index) {
        if (index >= arr.size()) return Integer.MIN_VALUE;

        return Math.max(getMax(arr, index+1), arr.get(index));
    }

    // The function return the sum of elements in range [index, n-1]
    public static int getSum(List<Integer> arr, int index) {
        if (index >= arr.size()) return 0;

        return arr.get(index) + getSum(arr, index+1);
    }

    // Linear Search 
    public static int linearSearch(List<Integer> arr, int index, int key) {
        if (index >= arr.size()) return -1;

        return (arr.get(index) == key) ? index : linearSearch(arr, index+1, key);
    }


    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,2,3,4,5);

        print(l, 0);
        System.out.println(getMax(l, 0));
        System.out.println(getSum(l, 0));
        System.out.println(linearSearch(l, 0, 10));
    }
}
