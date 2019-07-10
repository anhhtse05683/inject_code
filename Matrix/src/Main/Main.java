package Main;

import Business.CalculatorBusiness;
import java.util.Scanner;

public class Main {

    public static int valid(String mess, String error, int min, int max) {
        int number = 0;
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(mess);
                number = Integer.parseInt(scan.nextLine());
                if (number < min || number > max) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(error);
            }
        }
        return number;

    }

    public static void display(int[][] result) {
        int row = result[0].length;
        int col = result[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int[][] readMatrix(int row, int col) {
        int[][] result = new int[row][col];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("Enter Matrix[" + (i + 1) + "][" + (j + 1) + "]:");
                result[i][j] = sc.nextInt();
            }
        }
        return result;
    }

    public static int[][] addition(int[][] result) {
        System.out.println("Addition");
        CalculatorBusiness cb = new CalculatorBusiness();
        int row1 = valid("Enter row of matrix 1:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        int col1 = valid("Enter column of matrix 1:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        int matrix1[][] = readMatrix(row1, col1);
        int row2 = valid("Enter row of matrix 2:", "input must positive number larger than 1", 1, Integer.MAX_VALUE);
        while (row1 != row2) {
            System.out.println("Row of matrix 1 diffirent than matrix 2");
            row2 = valid("Enter row of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        }
        int col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        while (col1 != col2) {
            System.out.println("Column of matrix 1 diffirent than matrix 2");
            col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        }
        int matrix2[][] = readMatrix(row2, col2);
        display(matrix1);
        System.out.println("+");
        display(matrix2);
        System.out.println("=");
        result = cb.addMatrix(matrix1, matrix2);
        return result;
    }

    public static int[][] subtraction(int[][] result) {
        System.out.println("Subtraction");
        CalculatorBusiness cb = new CalculatorBusiness();
        int row1 = valid("Enter row of matrix 1:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        int col1 = valid("Enter column of matrix 1:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        int matrix1[][] = readMatrix(row1, col1);
        int row2 = valid("Enter row of matrix 2:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        while (row1 != row2) {
            System.out.println("Row of matrix 1 diffirent than matrix 2");
            row2 = valid("Enter row of matrix 2:", "input must be positive number larger than 1", 1, Integer.MAX_VALUE);
        }
        int col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        while (col1 != col2) {
            System.out.println("Column of matrix 1 diffirent than matrix 2");
            col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        }
        int matrix2[][] = readMatrix(row2, col2);
        display(matrix1);
        System.out.println("-");

        display(matrix2);
        System.out.println("=");
        result = cb.subMatrix(matrix1, matrix2);
        return result;
    }

    public static int[][] multiply(int[][] result) {
        System.out.println("Multiplication");
        CalculatorBusiness cb = new CalculatorBusiness();
        int row1 = valid("Enter row of matrix 1:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        int col1 = valid("Enter column of matrix 1:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        int matrix1[][] = readMatrix(row1, col1);
        int row2 = valid("Enter row of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        int col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        while (row1 != col2) {
            System.out.println("Row of matrix 1 diffirent than matrix 2");
            col2 = valid("Enter column of matrix 2:", "input positive number larger than 1", 1, Integer.MAX_VALUE);
        }
        int matrix2[][] = readMatrix(row2, col2);
        display(matrix1);
        System.out.println("*");
        display(matrix2);
        System.out.println("=");
        result = cb.mulMatrix(matrix1, matrix2);
        return result;
    }

    public static void main(String[] args) {
        boolean cont = true;
        int[][] result = null;
        int[][] add;
        int[][] sub;
        int[][] multi;
        while (cont == true) {
            System.out.print("1.Addtion Matrix \n"
                    + "2.Subtraction Matrix \n"
                    + "3.Multiply Matrix \n"
                    + "4.Exit\n");
            int select = valid("Please Choose your option", "Input 1-3", 1, 4);
            switch (select) {
                case 1:
                    add = addition(result);
                    display(add);
                    break;
                case 2:
                    sub = subtraction(result);
                    display(sub);
                    break;
                case 3:
                    multi = multiply(result);
                    display(multi);
                    break;
                case 4:
                    cont = false;
                    break;
            }
        }
    }
}
