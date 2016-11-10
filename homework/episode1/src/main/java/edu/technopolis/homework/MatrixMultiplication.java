package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("There are no arguments. For help type \"--help\"");
            return;
        }
        if ((args.length == 1) && (args[0].equals("--help"))) {
            System.out.println("\nWelcome to the program for matrix multiplication!\n" +
                    "If you want to multiply the matrix, you get to the right place!\n" +
                    "This program multiplies 2 matrices. First of all, you have to type their sizes: width and height. And then type values of each cell, firstly for the first matrix and then for the second.\n" +
                    "For example, if you want to multiply matrices A and B:\n\n" +
                    "      a11 a12         b11 b12\n" +
                    "A = (        ), B = (        ),\n" +
                    "      a21 a22         b21 b22\n\n" +
                    "your arguments should be:\n" +
                    "2 2 2 2 a11 a12 a21 a22 b11 b12 b21 b22\n" +
                    "Be careful! The height of first matrix should equals to the width of the second!\n" +
                    "All matrix's elements should be an integer and be more than -2147483648 and less then 2147483647.\n");
            return;
        }
        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);

            if (args.length > n * m + x * y + 4) {
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

    private static long[][] multiply(int n, int m, int x, int y, int[][] A, int[][] B) {
        if (m != x) {
            return null;
        }
        long result[][] = new long[n][y];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < y; j++) {
                long elem = 0;
                for (int k = 0; k < m; k++) {
                    elem += (long)A[i][k] * (long)B[k][j];
                }
                result[i][j] = elem;
                System.out.print(elem + " ");
            }
            System.out.println();
        }
        return result;
    }
}
