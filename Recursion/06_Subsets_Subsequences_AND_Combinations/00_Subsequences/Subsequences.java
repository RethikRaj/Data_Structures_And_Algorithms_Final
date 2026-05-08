public class Subsequences {

    static void f(String s, int idx, String output) {
        if(idx >= s.length()) {
            System.out.println(output);
            return;
        }

        f(s, idx + 1, output + s.charAt(idx));
        f(s, idx + 1, output);
    }

    static void f(String s, int idx, StringBuilder output) {
        if(idx >= s.length()) {
            System.out.println(output);
            return;
        }

        output.append(s.charAt(idx));
        f(s, idx + 1, output);
        output.deleteCharAt(output.length() - 1);
        f(s, idx + 1, output);
    }

    public static void main(String[] args) {
        String s1 = "abc";
        System.out.println("Hello");
        f(s1, 0, new StringBuilder());
        // f(s1, 0, new String());
    }
}