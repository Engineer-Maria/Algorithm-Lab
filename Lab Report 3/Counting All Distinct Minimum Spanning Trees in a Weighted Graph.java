/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prims_algorithm;
/**
 * @author Maria
 */
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class DistinctMSTCounter {
    private int V;
    private List<Edge> edges;
    private int[] parent;
    private int mstWeight;
    private int count;
    
    public DistinctMSTCounter(int V) {
        this.V = V;
        edges = new ArrayList<>();
        parent = new int[V];
    }
    
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }
    
    private int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }
    
    private void union(int x, int y) {
        parent[find(x)] = find(y);
    }
    
    public int countDistinctMSTs() {
        Collections.sort(edges);
        
        // Find MST weight first
        mstWeight = 0;
        for (int i = 0; i < V; i++) parent[i] = i;
        
        for (Edge edge : edges) {
            int x = find(edge.src);
            int y = find(edge.dest);
            if (x != y) {
                union(x, y);
                mstWeight += edge.weight;
            }
        }
        
        // Now count all MSTs with same weight
        count = 0;
        generateMSTs(0, 0, 0, new ArrayList<>());
        return count;
    }
    
    private void generateMSTs(int index, int edgesUsed, int currentWeight, List<Edge> currentEdges) {
        if (edgesUsed == V - 1) {
            if (currentWeight == mstWeight) count++;
            return;
        }
        if (index >= edges.size()) return;
        
        Edge edge = edges.get(index);
        int x = find(edge.src);
        int y = find(edge.dest);
        
        if (x != y) {
            int[] parentCopy = parent.clone();
            union(x, y);
            currentEdges.add(edge);
            generateMSTs(index + 1, edgesUsed + 1, currentWeight + edge.weight, currentEdges);
            currentEdges.remove(currentEdges.size() - 1);
            parent = parentCopy;
        }
        
        generateMSTs(index + 1, edgesUsed, currentWeight, currentEdges);
    }
    
    public static void main(String[] args) {
        DistinctMSTCounter graph = new DistinctMSTCounter(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 0, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 2);
        
        System.out.println("Number of distinct MSTs: " + graph.countDistinctMSTs());
    }
}