public class Sum {
    public static void main(String[] args) {
        System.out.println(sum());           // 0
        System.out.println(sum(5));          // 5
        System.out.println(sum(1, 2, 3));    // 6
        System.out.println(sum(10, 20, 30, 40)); // 100
    }

    public static double sum(double... numbers) {
        double sum = 0;
        for(double num : numbers) {
            sum += num;
        }
        return sum;
    }
}