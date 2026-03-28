public class CharacterToNumber {
    public static void main(String[] args) {

        // To get Unicode Value
        char ch = 'A';
        int ich = ch;

        System.out.println(ch + "->" + ich); // A->65
        System.out.println(ch + "->" + (int)ch); // A->65

        // To get actual number 
        char cha = '8';
        int icha = cha - '0';
        System.out.println(icha);

    }
}