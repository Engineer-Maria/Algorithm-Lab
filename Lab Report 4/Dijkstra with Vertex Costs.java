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

class DijkstraVertexCost {
    private int V;
    private List<List<Node>> adj;
    private int[] vertexCosts;

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

    public DijkstraVertexCost(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        vertexCosts = new int[V];
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
        adj.get(v).add(new Node(u, weight)); // Undirected graph
    }

    public void setVertexCost(int v, int cost) {
        vertexCosts[v] = cost;
    }

    public void dijkstra(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = vertexCosts[src]; // Include source vertex cost
        pq.add(new Node(src, dist[src]));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int edgeWeight = neighbor.weight;
                int totalCost = dist[u] + edgeWeight + vertexCosts[v];

                if (totalCost < dist[v]) {
                    dist[v] = totalCost;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("Vertex\tTotal Cost (including vertex costs)");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        DijkstraVertexCost g = new DijkstraVertexCost(5);
        
        g.setVertexCost(0, 2);
        g.setVertexCost(1, 3);
        g.setVertexCost(2, 1);
        g.setVertexCost(3, 4);
        g.setVertexCost(4, 2);
        
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 9);
        g.addEdge(2, 4, 2);
        g.addEdge(3, 4, 4);

        System.out.println("Shortest paths with vertex costs from vertex 0:");
        g.dijkstra(0);
    }
}