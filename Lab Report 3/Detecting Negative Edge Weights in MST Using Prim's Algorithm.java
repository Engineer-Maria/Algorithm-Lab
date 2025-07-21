/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prims_algorithm;

/**
 * @author Maria
 */
import java.util.*;

public class NegativeWeightMST {
    private int V;
    private List<List<Node>> adj;
    
    public NegativeWeightMST(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest, int weight) {
        adj.get(src).add(new Node(dest, weight));
        adj.get(dest).add(new Node(src, weight));
    }
    
    public boolean hasNegativeWeightMST() {
        boolean[] visited = new boolean[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            visited[u] = true;
            
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.key;
                
                if (!visited[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.add(new Node(v, key[v]));
                }
            }
        }
        
        for (int i = 0; i < V; i++) {
            if (key[i] < 0) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        NegativeWeightMST graph = new NegativeWeightMST(4);
        graph.addEdge(0, 1, -1);
        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 3, -3);
        graph.addEdge(3, 0, 1);
        
        if (graph.hasNegativeWeightMST()) {
            System.out.println("The graph contains negative weight edges in its MST");
        } else {
            System.out.println("No negative weight edges in the MST");
        }
    }
}

class Node implements Comparable<Node> {
    int vertex, key;
    
    public Node(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }
    
    @Override
    public int compareTo(Node other) {
        return this.key - other.key;
    }
}