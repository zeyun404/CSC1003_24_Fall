import java.util.*;

public class MultiplySparseMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read dimensions of the first matrix
        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        scanner.nextLine();

        // Read the first sparse matrix
        Map<Integer, Map<Integer, Integer>> matrix1 = readSparseMatrix(scanner, rows1);

        // Read dimensions of the second matrix
        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();
        scanner.nextLine();

        // Read the second sparse matrix
        Map<Integer, Map<Integer, Integer>> matrix2 = readSparseMatrix(scanner, rows2);

        // Multiply matrices
        Map<Integer, Map<Integer, Integer>> result = new HashMap<>();
        for (int i = 1; i <= rows1; i++) { // Iterate through rows of matrix1
            if (!matrix1.containsKey(i)) continue;

            Map<Integer, Integer> row1 = matrix1.get(i);
            Map<Integer, Integer> resultRow = new HashMap<>();

            for (int k : row1.keySet()) { // For each non-zero entry in the current row of matrix1
                if (!matrix2.containsKey(k)) continue;

                Map<Integer, Integer> row2 = matrix2.get(k);
                for (int j : row2.keySet()) { // Multiply with corresponding entries in matrix2
                    resultRow.put(j, resultRow.getOrDefault(j, 0) + row1.get(k) * row2.get(j));
                }
            }

            if (!resultRow.isEmpty()) {
                result.put(i, resultRow);
            }
        }

        // Print result
        printSparseMatrix(result, rows1, cols2);
    }

    private static Map<Integer, Map<Integer, Integer>> readSparseMatrix(Scanner scanner, int rows) {
        Map<Integer, Map<Integer, Integer>> matrix = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            int row = Integer.parseInt(parts[0]);
            Map<Integer, Integer> rowMap = new HashMap<>();
            for (int j = 1; j < parts.length; j++) {
                String[] pair = parts[j].split(":");
                int col = Integer.parseInt(pair[0]);
                int value = Integer.parseInt(pair[1]);
                rowMap.put(col, value);
            }
            matrix.put(row, rowMap);
        }
        return matrix;
    }

    private static void printSparseMatrix(Map<Integer, Map<Integer, Integer>> matrix, int rows, int cols) {
        System.out.println(rows + " " + cols);
        for (int i = 1; i <= rows; i++) {
            if (matrix.containsKey(i)) {
                System.out.print(i);
                Map<Integer, Integer> row = matrix.get(i);
                for (int col : new TreeSet<>(row.keySet())) { // Sort columns in ascending order
                    int value = row.get(col);
                    if (value != 0) {
                        System.out.print(" " + col + ":" + value);
                    }
                }
                System.out.println();
            } else {
                // Print rows with no non-zero elements
                System.out.println(i);
            }
        }
    }
}