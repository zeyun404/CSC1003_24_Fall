import java.util.*;

public class AddSparseMatrix {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputStr1 = inputScanner.nextLine();
        String resultStr = "";
        int index = 0;
        resultStr += inputStr1.charAt(0);
        int rowCount = Integer.parseInt(resultStr); 
        while (index + 1 < inputStr1.length() && Character.isDigit(inputStr1.charAt(index + 1))) {
            index++;
            String digitStr = "";
            digitStr += inputStr1.charAt(index);
            int tempNum = Integer.parseInt(digitStr);
            rowCount = rowCount * 10 + tempNum;
        }
        index = index + 2;
        resultStr = "";
        resultStr += inputStr1.charAt(index);
        int colCount = Integer.parseInt(resultStr); 
        while (index + 1 < inputStr1.length() && Character.isDigit(inputStr1.charAt(index + 1))) {
            index++;
            String digitStr2 = "";
            digitStr2 += inputStr1.charAt(index);
            int tempNum2 = Integer.parseInt(digitStr2);
            colCount = colCount * 10 + tempNum2;
        }
        int[][] matrix1 = new int[500][500];
        int[][] matrix2 = new int[500][500];
        int[][] matrixSum = new int[500][500];
        for (int i = 0; i < rowCount; i++) {
            String lineStr = inputScanner.nextLine();
            for (int j = 0; j < lineStr.length(); j++) {
                char charAtJ = lineStr.charAt(j);
                if (j == 0 && j + 1 < lineStr.length()) {
                    j++;
                    charAtJ = lineStr.charAt(j);
                }
                if (charAtJ ==' ' && j + 1 < lineStr.length()) {
                    j++;
                    charAtJ = lineStr.charAt(j);
                }
                if (Character.isDigit(charAtJ)) {
                    int colIndex = charAtJ - '0'; 
                    while (j + 1 < lineStr.length() && Character.isDigit(lineStr.charAt(j + 1))) {
                        j++;
                        colIndex = colIndex * 10 + (lineStr.charAt(j) - '0');
                    }
                    int numValue = 0; 
                    if (j + 1 < lineStr.length() && lineStr.charAt(j + 1) == ':') {
                        j = j + 2;
                        if (lineStr.charAt(j) == '-') {
                            j++;
                            numValue = 0 - (lineStr.charAt(j) - '0');
                        } else {
                            numValue += (lineStr.charAt(j) - '0');
                        }
                        while (j + 1 < lineStr.length() && Character.isDigit(lineStr.charAt(j + 1))) {
                            j++;
                            numValue = numValue * 10 + (lineStr.charAt(j) - '0');
                        }
                    }
                    matrix1[i][colIndex - 1] = numValue;
                }
            }
        }
        /*for(int i=0;i<500;i++){
            for(int j=0; j<500; j++){
                if(matrix1[i][j]!=0){System.out.print(matrix1[i][j]+" ");}
            }
        }*/
        String inputStr2 = inputScanner.nextLine();
        if (!inputStr2.equals(inputStr1)) {
            System.out.println("wrong!");
        }
        for (int i = 0; i < rowCount; i++) {
            String lineStr2 = inputScanner.nextLine();
            for (int j = 0; j < lineStr2.length(); j++) {
                char charAtJ2 = lineStr2.charAt(j);
                if (charAtJ2 == 0 && j + 1 < lineStr2.length()) {
                    j++;
                    charAtJ2 = lineStr2.charAt(j);
                }
                if (charAtJ2 ==' ' && j + 1 < lineStr2.length()) {
                    j++;
                    charAtJ2 = lineStr2.charAt(j);
                }
                if (Character.isDigit(charAtJ2)) {
                    int colIndex2 = charAtJ2 - '0'; 
                    while (j + 1 < lineStr2.length() && Character.isDigit(lineStr2.charAt(j + 1))) {
                        j++;
                        colIndex2 = colIndex2 * 10 + (lineStr2.charAt(j) - '0');
                    }
                    int numValue2 = 0; 
                    if (j + 1 < lineStr2.length() && lineStr2.charAt(j + 1) == ':') {
                        j = j + 2;
                        if (lineStr2.charAt(j) == '-') {
                            j++;
                            numValue2 = 0 - (lineStr2.charAt(j) - '0');
                        } else {
                            numValue2 += (lineStr2.charAt(j) - '0');
                        }
                        while (j + 1 < lineStr2.length() && Character.isDigit(lineStr2.charAt(j + 1))) {
                            j++;
                            numValue2 = numValue2 * 10 + (lineStr2.charAt(j) - '0');
                        }
                    }
                    matrix2[i][colIndex2 - 1] = numValue2;
                }
            }
        }

        System.out.println(rowCount + " " + colCount);
        for (int i = 0; i < rowCount; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < colCount; j++) {
                matrixSum[i][j] = matrix1[i][j] + matrix2[i][j];
                if (matrixSum[i][j]!= 0) {
                    if (j == colCount - 1) {
                        System.out.print(j + 1 + ":" + matrixSum[i][j]);
                    } else {
                        System.out.print(j + 1 + ":" + matrixSum[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }
        inputScanner.close();
    }
}