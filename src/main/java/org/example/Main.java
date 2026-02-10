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

        deikstra(matr, 0);
    }

    static void deikstra(int[][] matr, int start) {
        int n = matr.length;

        int[] dist = new int[n];
        boolean[] used = new boolean[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int v = -1;


            for (int j = 0; j < n; j++) {
                if (!used[j] && (v == -1 || dist[j] < dist[v])) {
                    v = j;
                }
            }

            used[v] = true;


            for (int to = 0; to < n; to++) {
                if (matr[v][to] < INF) {
                    dist[to] = Math.min(dist[to], dist[v] + matr[v][to]);
                }
            }
        }


        char vertex = 'A';
        for (int i = 0; i < n; i++) {
            System.out.println("A -> " + (char)(vertex + i) + " = " + dist[i]);
        }
    }
}