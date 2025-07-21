/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prims_algorithm;

/**
 * @author Maria
 */
import java.util.*;

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

public class OptimizedPrimMST {
    private int V;
    private List<List<Node>> adj;
    
    public OptimizedPrimMST(int V) {
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
    
    public void primMST() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];
        
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        
        key[0] = 0;
        pq.add(new Node(0, key[0]));
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            
            inMST[u] = true;
            
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.key;
                
                if (!inMST[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                    pq.add(new Node(v, key[v]));
                }
            }
        }
        
        printMST(parent);
    }
    
    private void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + getWeight(parent[i], i));
        }
    }
    
    private int getWeight(int u, int v) {
        for (Node node : adj.get(u)) {
            if (node.vertex == v) {
                return node.key;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        OptimizedPrimMST graph = new OptimizedPrimMST(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        
        graph.primMST();
    }
}