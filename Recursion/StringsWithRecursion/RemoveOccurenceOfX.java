/*
    Input = "abcxxdexx"
    Output = "abcde"
 */

public class RemoveOccurenceOfX {
    public static void f(String input, int index, StringBuilder output) {
        // Base case 
        if(index >= input.length()) return;

        // 
        if(input.charAt(index) == 'x') {
            f(input, index + 1, output);
        } else {
            f(input, index + 1, output.append(input.charAt(index)));
        }
    }
    
    public static void main(String[] args) {
        String s = "abcxxdexx";
        StringBuilder output = new StringBuilder("");
        f(s, 0, output);
        System.out.println(output);
    }
}
