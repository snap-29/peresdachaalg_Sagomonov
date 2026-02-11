package org.example;

import java.util.Arrays;

public class Main {

    static final int INF = 1000000000;

    public static void main(String[] args) {
        int[][] matr = {
                {0, 7, 9, INF, INF, 14},
                {7, 0, 10, 15, INF, INF},
                {9, 10, 0, 11, INF, 2},
                {INF, 15, 11, 0, 6, INF},
                {INF, INF, INF, 6, 0, 9},
                {14, INF, 2, INF, 9, 0}
        };

        int newEdgeFrom = 4;
        int newEdgeTo = 1;
        int newEdgeWeight = 4;

        System.out.println("Исходная матрица:");
        printMatrix(matr);

        System.out.println("\nДобавляем новое ребро " +
                (char)('A' + newEdgeFrom) + " -> " +
                (char)('A' + newEdgeTo) + " с весом " + newEdgeWeight);
        matr[newEdgeFrom][newEdgeTo] = newEdgeWeight;
        matr[newEdgeTo][newEdgeFrom] = newEdgeWeight;

        System.out.println("\nМатрица после добавления ребра:");
        printMatrix(matr);

        deikstra(matr, 0);
    }

    static void printMatrix(int[][] matr) {
        System.out.print("   ");
        for (int i = 0; i < matr.length; i++) {
            System.out.print((char)('A' + i) + "   ");
        }
        System.out.println();

        for (int i = 0; i < matr.length; i++) {
            System.out.print((char)('A' + i) + ": ");
            for (int j = 0; j < matr[i].length; j++) {
                if (matr[i][j] == INF) {
                    System.out.print("∞   ");
                } else {
                    System.out.printf("%-3d ", matr[i][j]);
                }
            }
            System.out.println();
        }
    }

    static void deikstra(int[][] matr, int start) {
        int n = matr.length;
        int[] dist = new int[n];
        boolean[] used = new boolean[n];
        int[] prev = new int[n]; // Для восстановления пути

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        char[] vertexNames = new char[n];
        for (int i = 0; i < n; i++) {
            vertexNames[i] = (char)('A' + i);
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("АЛГОРИТМ ДЕЙКСТРЫ (стартовая вершина: " + vertexNames[start] + ")");
        System.out.println("=".repeat(80));

        System.out.printf("%-10s | %-40s | %-20s%n", "Итерация", "Расстояния до вершин", "Выбранная вершина");
        System.out.println("-".repeat(80));

        for (int iter = 0; iter < n; iter++) {
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (v == -1 || dist[j] < dist[v])) {
                    v = j;
                }
            }

            if (dist[v] == INF) break;

            used[v] = true;

            for (int to = 0; to < n; to++) {
                if (matr[v][to] < INF) {
                    int newDist = dist[v] + matr[v][to];
                    if (newDist < dist[to]) {
                        dist[to] = newDist;
                        prev[to] = v;
                    }
                }
            }

            System.out.printf("%-10d | ", iter + 1);
            for (int i = 0; i < n; i++) {
                if (dist[i] == INF) {
                    System.out.print(vertexNames[i] + ":∞ ");
                } else {
                    System.out.print(vertexNames[i] + ":" + dist[i] + " ");
                }
            }
            System.out.printf(" | %-20s%n", vertexNames[v] + " (посещена)");
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("РЕЗУЛЬТАТЫ:");
        System.out.println("=".repeat(80));

        for (int i = 0; i < n; i++) {
            System.out.print("A -> " + vertexNames[i] + " = ");
            if (dist[i] == INF) {
                System.out.println("∞ (недостижима)");
            } else {
                System.out.println(dist[i]);
                System.out.print("   Путь: ");
                printPath(prev, i, vertexNames);
                System.out.println();
            }
        }
    }

    static void printPath(int[] prev, int vertex, char[] vertexNames) {
        if (vertex == -1) {
            return;
        }
        printPath(prev, prev[vertex], vertexNames);
        System.out.print(vertexNames[vertex] + " ");
    }
}