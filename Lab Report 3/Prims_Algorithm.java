/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.prims_algorithm;
/**
 * @author Maria Prims_Algorithm
 */
import java.util.*; 

class Edge { 
    int source, destination, weight; 
    public Edge(int source, int destination, int weight) { 
        this.source = source; 
        this.destination = destination; 
        this.weight = weight; 
    } 
} 

class Graph { 
    private int vertices; 
    private List<List<Edge>> adjacencyList; 
    
    public Graph(int vertices) { 
        this.vertices = vertices; 
        this.adjacencyList = new ArrayList<>(vertices); 
        for (int i = 0; i < vertices; i++) { 
            adjacencyList.add(new ArrayList<>()); 
        } 
    } 
    
    public void addEdge(int source, int destination, int weight) { 
        Edge edge = new Edge(source, destination, weight); 
        adjacencyList.get(source).add(edge); 
        edge = new Edge(destination, source, weight); 
        adjacencyList.get(destination).add(edge); 
    } 
    
    public List<Edge> findMST() { 
        boolean[] inMST = new boolean[vertices]; 
        Edge[] edgeTo = new Edge[vertices]; 
        int[] key = new int[vertices]; 
        Arrays.fill(key, Integer.MAX_VALUE); 
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(e -> e.weight)); 
        
        key[0] = 0; 
        pq.offer(new Edge(-1, 0, 0)); 
        
        List<Edge> mstEdges = new ArrayList<>(); 
        
        while (!pq.isEmpty()) { 
            Edge minEdge = pq.poll(); 
            int u = minEdge.destination; 
            
            if (inMST[u]) continue; 
            
            inMST[u] = true; 
            
            if (minEdge.source != -1) { 
                mstEdges.add(minEdge); 
            } 
            
            for (Edge edge : adjacencyList.get(u)) { 
                int v = edge.destination; 
                if (!inMST[v] && edge.weight < key[v]) { 
                    key[v] = edge.weight; 
                    edgeTo[v] = edge; 
                    pq.offer(new Edge(u, v, key[v])); 
                } 
            } 
        } 
        return mstEdges; 
    } 
    
    public List<Edge> findSecondBestMST() { 
        List<Edge> mstEdges = findMST(); 
        List<Edge> secondBestMST = null; 
        int minMSTWeight = calculateMSTWeight(mstEdges); 
        int secondMinWeight = Integer.MAX_VALUE; 
        
        for (int i = 0; i < mstEdges.size(); i++) { 
            Edge excludedEdge = mstEdges.get(i); 
            removeEdge(excludedEdge.source, excludedEdge.destination); 
            
            List<Edge> currentMST = findMST(); 
            if (currentMST.size() == vertices - 1) { 
                int currentWeight = calculateMSTWeight(currentMST); 
                if (currentWeight > minMSTWeight && currentWeight < secondMinWeight) { 
                    secondMinWeight = currentWeight; 
                    secondBestMST = new ArrayList<>(currentMST); 
                } 
            } 
            addEdge(excludedEdge.source, excludedEdge.destination, excludedEdge.weight); 
        } 
        return secondBestMST; 
    } 
    
    private void removeEdge(int u, int v) { 
        adjacencyList.get(u).removeIf(edge -> edge.destination == v); 
        adjacencyList.get(v).removeIf(edge -> edge.destination == u); 
    } 
    
    private int calculateMSTWeight(List<Edge> mstEdges) { 
        return mstEdges.stream().mapToInt(e -> e.weight).sum(); 
    } 
    
    public void printMST(List<Edge> mstEdges) { 
        System.out.println("Edges in the MST:"); 
        for (Edge edge : mstEdges) { 
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight); 
        } 
        System.out.println("Total weight: " + calculateMSTWeight(mstEdges)); 
    } 
} 

public class Prims_Algorithm { 
    public static void main(String[] args) { 
        int vertices = 5; 
        Graph graph = new Graph(vertices); 
        
        graph.addEdge(0, 1, 2); 
        graph.addEdge(0, 3, 6); 
        graph.addEdge(1, 2, 3); 
        graph.addEdge(1, 3, 8); 
        graph.addEdge(1, 4, 5); 
        graph.addEdge(2, 4, 7); 
        graph.addEdge(3, 4, 9); 
        
        List<Edge> mst = graph.findMST(); 
        System.out.println("Minimum Spanning Tree:"); 
        graph.printMST(mst); 
        
        List<Edge> secondBestMST = graph.findSecondBestMST(); 
        if (secondBestMST != null) { 
            System.out.println("\nSecond Best Minimum Spanning Tree:"); 
            graph.printMST(secondBestMST); 
        } else { 
            System.out.println("\nNo second best MST exists (graph might have only one MST)"); 
        } 
    } 
}