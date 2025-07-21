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

class CountShortestPaths {
    private int V;
    private List<List<Node>> adj;

    class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public CountShortestPaths(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
        adj.get(v).add(new Node(u, weight)); // Undirected graph
    }

    public void countShortestPaths(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        int[] count = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        count[src] = 1;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    count[v] = count[u];
                    pq.add(new Node(v, dist[v]));
                } 
                else if (dist[v] == dist[u] + weight) {
                    count[v] += count[u];
                }
            }
        }

        System.out.println("Vertex\tDistance\tNumber of Shortest Paths");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + dist[i] + "\t\t" + count[i]);
        }
    }

    public static void main(String[] args) {
        CountShortestPaths g = new CountShortestPaths(5);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 3);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 2);

        System.out.println("Counting shortest paths from vertex 0:");
        g.countShortestPaths(0);
    }
}