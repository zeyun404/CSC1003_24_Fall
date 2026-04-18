import java.util.*;

public class TestFibonacci {
	static Scanner input = new Scanner(System.in);
	static int[] fibonacci = new int[18];
	static {
		fibonacci[0] = 1;
		fibonacci[1] = 1;
		for (int i = 2; i < 18; i++) {
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}
	}
public static void parse_line(int n, int d) {

	StringBuilder result = new StringBuilder();
        
	for (int i = 0; i < d; i++) {
		result.append(fibonacci[start - i -1 ]);
		}
	

		System.out.println(result.toString().trim());
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
