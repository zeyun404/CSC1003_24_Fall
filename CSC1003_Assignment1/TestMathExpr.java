import java.util.Scanner;

public class TestMathExpr {

    static Scanner input = new Scanner(System.in);

    public static void parse_line(String s1, String s2, String s3) {
        try {
            int a = Integer.parseInt(s1);
            int b = Integer.parseInt(s3);
            int result = 0;

            switch (s2) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        System.out.println("invalid");
                        return;
                    }
                    result = a / b;
                    break;
                default:
                    System.out.println("invalid");
                    return;
            }

            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("invalid");
        }
    }

    public static void main(String[] args) throws Exception {

        int line_number = Integer.parseInt(input.nextLine());

        for (int i = 0; i < line_number; i++) {
            String s = input.nextLine();
            String[] t = s.split(" ");
            TestMathExpr.parse_line(t[0], t[1], t[2]);
        }
    }
}