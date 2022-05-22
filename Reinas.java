package Taller;

import java.util.Scanner;

public class Reinas {
    public static void main(String args[]) {
        // Solicita al usuario el tamaño
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese N: ");
        // Input del numero
        Integer num = scan.nextInt();
        System.out.println(num);

        // Creación del tablero con filas y columnas
        String[][] tablero = new String[num][num];
        // Recorrido de filas i
        for (int i = 0; i < tablero.length; i++) {
            // Recorrido de columnas j
            for (int j = 0; j < tablero.length; j++) {
                // Espacios vacios en cada posición i,j
                tablero[i][j] = "| |";
            }
        }
        tablero = busquedaReinas(tablero, 0, num);
        impresionMatriz(tablero);
    }

    public static String[][] busquedaReinas(String[][] tablero, int fila, int num) {
        // recorrido de cada una de las filas
        for (int i = 0; i < num; i++) {
            if (i == 1) {
                tablero[i][fila] = "|R|";
            }
            if (i == num - 1) {
                if (fila < num - 1) {
                    busquedaReinas(tablero, fila + 1, num);
                }
            }
        }
        return tablero;
    }

    public static void impresionMatriz(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (j == tablero.length - 1) {
                    System.out.println(tablero[j][i] + " ");
                } else {
                    System.out.print(tablero[j][i] + " , ");
                }

            }
        }
    }

}