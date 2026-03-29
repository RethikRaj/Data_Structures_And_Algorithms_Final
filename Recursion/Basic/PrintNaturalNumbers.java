public class PrintNaturalNumbers {
    public static void printIncreasing(int n) {
        if (n == 0) {
            return;
        }

        printIncreasing(n-1);
        System.out.println(n);
    }

    public static void printDecreasing(int n) {
        if( n == 0) {
            return;
        }

        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void main(String[] args) {
        printIncreasing(5);;
        printDecreasing(5);
    }
}