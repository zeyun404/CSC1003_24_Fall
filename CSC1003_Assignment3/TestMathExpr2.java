import java.util.Scanner;

public class TestMathExpr {

    public static double parse(String expression) {
        return evaluate(expression, 0).value;
    }

    // 递归解析整个表达式
    private static ParseResult evaluate(String expression, int start) {
        expression = expression.replaceAll("\\s+", ""); // 去掉空格
        return parseExpression(expression, start);
    }

    // 解析加法和减法
    private static ParseResult parseExpression(String expression, int start) {
        double result = 0;
        char operator = '+';
        int i = start;

        while (i < expression.length()) {
            char currentChar = expression.charAt(i);

            if (currentChar == '(') {
                // 如果遇到左括号，递归解析括号中的表达式
                ParseResult resultInside = parseExpression(expression, i + 1);
                result += (operator == '+') ? resultInside.value : -resultInside.value;
                i = resultInside.index;
            } else if (currentChar == ')') {
                // 如果遇到右括号，返回当前解析结果
                return new ParseResult(result, i + 1);
            } else if (currentChar == '+' || currentChar == '-') {
                // 处理加减法
                operator = currentChar;
                i++;
            } else if (currentChar == '*' || currentChar == '/') {
                // 处理乘除法
                ParseResult resultTerm = parseTerm(expression, i + 1);
                double termValue = resultTerm.value;
                result = (operator == '+') ? result + termValue : result - termValue;
                i = resultTerm.index;
            } else if (Character.isDigit(currentChar) || currentChar == '.') {
                // 如果是数字，直接处理数字部分
                ParseResult resultNumber = parseNumber(expression, i);
                result = (operator == '+') ? result + resultNumber.value : result - resultNumber.value;
                i = resultNumber.index;
            } else if (currentChar == 's' || currentChar == 'c' || currentChar == 't' || currentChar == 'q') {
                // 如果遇到三角函数或平方根
                ParseResult resultFunc = parseFunction(expression, i);
                result = (operator == '+') ? result + resultFunc.value : result - resultFunc.value;
                i = resultFunc.index;
            } else {
                i++;
            }
        }
        return new ParseResult(result, i);
    }

    // 解析乘除法
    private static ParseResult parseTerm(String expression, int start) {
        double result = 1;
        char operator = '*';
        int i = start;

        while (i < expression.length()) {
            char currentChar = expression.charAt(i);

            if (currentChar == '(') {
                ParseResult resultInside = parseExpression(expression, i + 1);
                result = (operator == '*') ? result * resultInside.value : result / resultInside.value;
                i = resultInside.index;
            } else if (currentChar == ')') {
                return new ParseResult(result, i + 1);
            } else if (currentChar == '*' || currentChar == '/') {
                operator = currentChar;
                i++;
            } else if (Character.isDigit(currentChar) || currentChar == '.') {
                ParseResult resultNumber = parseNumber(expression, i);
                result = (operator == '*') ? result * resultNumber.value : result / resultNumber.value;
                i = resultNumber.index;
            } else {
                i++;
            }
        }
        return new ParseResult(result, i);
    }

    // 解析数字（包括整数和浮点数）
    private static ParseResult parseNumber(String expression, int start) {
        int i = start;
        StringBuilder num = new StringBuilder();
        
        while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
            num.append(expression.charAt(i));
            i++;
        }

        return new ParseResult(Double.parseDouble(num.toString()), i);
    }

    // 解析函数（如 sin, cos, tan, sqrt）
    private static ParseResult parseFunction(String expression, int start) {
        StringBuilder func = new StringBuilder();
        int i = start;
        
        while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
            func.append(expression.charAt(i));
            i++;
        }

        if (func.toString().equals("sin") || func.toString().equals("cos") ||
            func.toString().equals("tan") || func.toString().equals("sqrt")) {
            
            // 确保函数后面跟着一个括号
            if (i < expression.length() && expression.charAt(i) == '(') {
                ParseResult resultInside = parseExpression(expression, i + 1);
                double value = evaluateFunction(func.toString(), resultInside.value);
                return new ParseResult(value, resultInside.index);
            }
        }

        return new ParseResult(0, i);
    }

    // 根据函数名执行计算
    private static double evaluateFunction(String func, double param) {
        switch (func) {
            case "sin": return Math.sin(param);
            case "cos": return Math.cos(param);
            case "tan": return Math.tan(param);
            case "sqrt": return Math.sqrt(param);
            default: return 0;
        }
    }

    private static class ParseResult {
        double value;
        int index;

        ParseResult(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String expression = input.nextLine().trim();
            double result = parse(expression);
            System.out.println(Math.round(result));
        }
        input.close();
    }
}
