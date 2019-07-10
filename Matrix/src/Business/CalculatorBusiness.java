
package Business;


public class CalculatorBusiness {

    public int[][] addMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null) {
            return null;
        }
        if (matrix2 == null) {
            return null;
        }
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        if (row1 != row2) {
            return null;
        }
        if (col1 != col2) {
            return null;
        }
        int[][] result = new int[row1][col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public int[][] subMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null) {
            return null;
        }
        if (matrix2 == null) {
            return null;
        }
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        if (row1 != row2) {
            return null;
        }
        if (col1 != col2) {
            return null;
        }
        int[][] result = new int[row1][col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public int[][] mulMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null) {
            return null;
        }
        if (matrix2 == null) {
            return null;
        }
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        if (row1 != col2) {
            return null;
        }
        int[][] result = new int[row1][col2];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}
