import java.util.Scanner;

public class floodfill {
    
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] grid = new char[20][20];
        for (int i = 0; i < 20; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }

        String[] coords = scanner.nextLine().split(",");
        int startX = Integer.parseInt(coords[0]);
        int startY = Integer.parseInt(coords[1]);

        floodFill(grid, startX, startY);

        for (int i = 0; i < 20; i++) {
            System.out.println(new String(grid[i]));
        }
    }

    public static void floodFill(char[][] grid, int x, int y) {

        if (x < 0 || x >= 20 || y < 0 || y >= 20 || grid[x][y] != '-') {
            return;
        }

        grid[x][y] = '*';

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            floodFill(grid, newX, newY);  
        }
    }
}
