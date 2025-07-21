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

class VertexWeightedGraph {
    private int V;
    private List<List<Node>> adj;
    private int[] vertexCosts;

    class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public VertexWeightedGraph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        vertexCosts = new int[V];
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
        adj.get(v).add(new Node(u, weight)); // For undirected graph
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
                int edgeWeight = neighbor.distance;
                // Total cost = current path cost + edge weight + destination vertex cost
                int totalCost = dist[u] + edgeWeight + vertexCosts[v];
                
                if (totalCost < dist[v]) {
                    dist[v] = totalCost;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        printSolution(dist);
    }

    private void printSolution(int[] dist) {
        System.out.println("Vertex \t\t Distance from Source (including vertex costs)");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        VertexWeightedGraph g = new VertexWeightedGraph(5);
        
        // Set vertex costs
        g.setVertexCost(0, 2);
        g.setVertexCost(1, 3);
        g.setVertexCost(2, 1);
        g.setVertexCost(3, 4);
        g.setVertexCost(4, 2);
        
        // Add edges (undirected graph)
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 9);
        g.addEdge(2, 4, 2);
        g.addEdge(3, 4, 4);
        g.addEdge(4, 0, 7);

        System.out.println("Shortest paths from vertex 0 (including vertex costs):");
        g.dijkstra(0);
    }
}