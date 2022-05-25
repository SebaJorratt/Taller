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
        tablero = rellenaMatriz(tablero, 0, num);
        tablero = busquedaReinas(tablero, 0, num);
        impresionMatriz(tablero, 0, num);
    }

    public static String[][] rellenaMatriz(String[][] matriz, int fila, int num) {
        // Recorrido de columnas j
        for (int j = 0; j < num; j++) {
            matriz[j][fila] = "| |";
            if (j == num - 1 && fila != num - 1) {
                matriz = rellenaMatriz(matriz, fila + 1, num);
            }
        }
        return matriz;
    }

    public static String[][] busquedaReinas(String[][] tablero, int fila, int num) {
        // recorrido de cada una de las columnas (variable j)
        for (int j = 0; j < num; j++) {
            // Pregunta si la posición actual es válida
            if (valido(tablero, j, fila)) {
                // Si es válida la marca y pasa a la siguiente fila
                tablero[j][fila] = "|R|";
                // Mientras la fila sea menor al valor N, sigue a la siguiente fila
                if (fila < num - 1) {
                    // Realiza la recursión
                    tablero = busquedaReinas(tablero, fila + 1, num);
                } else {
                    // Retorno del tablero actual como tablero final
                    return tablero;
                }
            }
            // Revisa si no es la ultima fila
            if (fila < num - 1) {
                // Verifica si la fila siguiente contiene reinas insertadas
                if (verificador(tablero, fila + 1, num)) {
                    tablero[j][fila] = "| |";
                }
            }
        }
        // Return final
        return tablero;
    }

    public static boolean verificador(String[][] tablero, int fila, int num) {
        // Revisa por cada columna de la fila si contiene una reina, si es asi, return
        // false
        for (int j = 0; j < num; j++) {
            if (tablero[j][fila] == "|R|") {
                return false;
            }
        }
        return true;
    }

    public static void impresionMatriz(String[][] tablero, int fila, int num) {
        for (int j = 0; j < num; j++) {
            if (j == num - 1) {
                System.out.println(tablero[j][fila] + " ");
                if (fila != num - 1) {
                    impresionMatriz(tablero, fila + 1, num);
                }
            } else {
                System.out.print(tablero[j][fila] + " , ");
            }
        }
    }

    public static boolean valido(String[][] tablero, int colum, int fila) {
        if (colum == 1) {
            return true;
        } else {
            return false;
        }
    }

}