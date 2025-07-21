/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_report_04;

/**
 *
 * @author Maria
 */
import java.util.*;

class GridDijkstra {
    private static class Cell implements Comparable<Cell> {
        int x, y;
        int dist;

        public Cell(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void dijkstra(int[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        dist[startX][startY] = grid[startX][startY];
        pq.add(new Cell(startX, startY, dist[startX][startY]));

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            int x = current.x;
            int y = current.y;

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                    int newDist = dist[x][y] + grid[nx][ny];
                    if (newDist < dist[nx][ny]) {
                        dist[nx][ny] = newDist;
                        pq.add(new Cell(nx, ny, newDist));
                    }
                }
            }
        }

        System.out.println("Shortest path distances from (" + startX + "," + startY + "):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] terrain = {
            {1, 3, 1, 2},
            {1, 5, 1, 3},
            {2, 1, 4, 1},
            {1, 1, 2, 1}
        };

        System.out.println("Grid-based Dijkstra (Terrain Pathfinding):");
        dijkstra(terrain, 0, 0);
    }
}