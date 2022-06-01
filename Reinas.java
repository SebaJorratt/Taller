package Taller;

import java.net.SocketAddress;
import java.util.Scanner;

public class Reinas {
    public static void main(String args[]) {

        // Para compilar en CMD:
        // 1°: javac Reinas.java
        // 2°: java Reinas.java

        // Solicita al usuario el tamaño
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese N: ");
        // Input del numero
        Integer num = scan.nextInt();
        System.out.println(" ");

        // Inicio del tiempo ejecucion
        long inicio = System.currentTimeMillis();
        // Creación del tablero con filas y columnas
        String[][] tablero = new String[num][num];
        // Se rellena la matriz con posiciones válidas
        tablero = rellenaMatriz(tablero, 0, num);
        // Se realiza el algoritmo recursivo (bt) para la inserción de reinas
        tablero = busquedaReinas(tablero, 0, num);
        // Recorre la matriz e imprime el resultado
        impresionMatriz(tablero, 0, num);

        // Fin del tiempo ejecucion
        long fin = System.currentTimeMillis();
        // Conversion del tiempo total a segundos
        float tiempo = (float) (fin - inicio) / 1000;
        System.out.println(" ");
        System.out.println("Tiempo ejecucion: " + tiempo + " segundos");
    }

    public static String[][] rellenaMatriz(String[][] matriz, int fila, int num) {
        // Recorrido de columnas j
        for (int j = 0; j < num; j++) {
            // Por cada columna recorrida inserta una posicion disponible
            matriz[j][fila] = " ";
            // Si llega a la última columna pero aún no termina a nivel de filas, sigue
            // rellenando
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
            if (valido(tablero, j, fila, num)) {
                // Si es válida la marca y pasa a la siguiente fila
                tablero[j][fila] = "R";
                // Si la fila es menor al valor N, sigue a la siguiente fila
                if (fila < num - 1) {
                    // Recursión con la siguiente fila
                    tablero = busquedaReinas(tablero, fila + 1, num);
                } else {
                    // Retorno del tablero actual como tablero final
                    return tablero;
                }
            }
            // Revisa si no es la ultima fila
            if (fila < num - 1) {
                // Si la fila siguiente contiene reinas insertadas, no vacía la posición
                if (verificador(tablero, fila + 1, num)) {
                    tablero[j][fila] = " ";
                }
            }
        }
        // Return final
        return tablero;
    }

    public static boolean verificador(String[][] tablero, int fila, int num) {
        // Revisa por cada columna de la fila si contiene reinas, si hay, return false
        for (int j = 0; j < num; j++) {
            if (tablero[j][fila] == "R") {
                return false;
            }
        }
        // Si no encuentra, retorna true y luego vacía la posición
        return true;
    }

    public static void impresionMatriz(String[][] tablero, int fila, int num) {
        System.out.print("|");
        // Recorrido por columnas
        for (int j = 0; j < num; j++) {
            // Si es la ultima columna, salta print hacia otra fila
            if (j == num - 1) {
                System.out.println(tablero[j][fila] + "|");
                // Si fila aún no coincide con tamaño total, pasa a la siguiente
                if (fila != num - 1) {
                    impresionMatriz(tablero, fila + 1, num);
                }
                // Se continúa recorriendo el tablero
            } else {
                System.out.print(tablero[j][fila] + "|");
            }
        }
    }

    public static boolean valido(String[][] tablero, int colum, int fila, int num) {
        // Recorre todas las filas anteriores a la actual
        for (int y = 0; y < fila; y++) {
            // Verifica en cada fila de la columna si existe alguna reina
            if (tablero[colum][y] == "R") {
                return false;
            }
        }
        // Recorre todas las columnas anteriores a la actual
        for (int y = 0; y < colum; y++) {
            if (tablero[y][fila] == "R") {
                return false;
            }
        }

        // Se recorre a partir de una columna de la matriz con su valor N (num)
        // Comprobando que sea o no una posicion válida
        for (int x = 0; x < num; x++) {

            // Recorre la diagonal derecha superior
            if (colum + x < num && fila - x >= 0) {
                if (tablero[colum + x][fila - x] == "R") {
                    return false;
                }
            }

            // Recorre la diagonal izquierda superior
            if (colum - x >= 0 && fila - x >= 0) {
                if (tablero[colum - x][fila - x] == "R") {
                    return false;
                }
            }
        }
        // Se comprueba como posicion valida, entonces retorna true
        return true;
    }
}