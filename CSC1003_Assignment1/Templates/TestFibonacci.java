import java.util.*;

public class TestFibonacci {
    static Scanner input = new Scanner(System.in);

    // Here is the function you need to implement
    public static void parse_line(int n, int d) {
        
    }

    public static void main(String[] args) throws Exception {
        int line_number = Integer.parseInt(input.nextLine());
        for (int i = 0; i < line_number; i++) {
            String s = input.nextLine();
            String[] t = s.split(", ");
            int n = Integer.parseInt(t[0]);
            int d = Integer.parseInt(t[1]);
            TestFibonacci.parse_line(n, d);
        }
    }
}