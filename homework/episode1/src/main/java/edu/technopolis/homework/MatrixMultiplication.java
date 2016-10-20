package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);

            if (args.length > n*m + x*y + 4) {
                System.err.println("Too much input!");
                System.exit(0);
            }

            if ((m != x) || (n <= 0) || (m <= 0) || (x <= 0) || (y <= 0)) {
                System.err.println("Wrong matrix size!");
            }

            int A[][] = new int[n][m];
            int B[][] = new int[x][y];
            int index = 4;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    A[i][j] = Integer.parseInt(args[index]);
                    index++;
                }
            }

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    B[i][j] = Integer.parseInt(args[index]);
                    index++;
                }
            }

            multiply(n, m, x, y, A, B);

        } catch (NumberFormatException e) {
            System.err.println("Wrong arguments format!");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong arguments count!");
            System.exit(0);
        }
    }

    private static int[][] multiply(int n, int m, int x, int y, int[][] A, int[][] B) {
        if (m != x) {
            return null;
        }
        int result[][] = new int[n][y];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < y; j++) {
                int elem = 0;
                for (int k = 0; k < m; k++) {
                    elem += A[i][k] * B[k][j];
                }
                result[i][j] = elem;
                System.out.print(elem + " ");
            }
            System.out.println();
        }
        return result;
    }
}
