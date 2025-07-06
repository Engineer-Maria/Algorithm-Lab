/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algo_lab_report_03;
/**
 * @author Maris
 */
import java.util.*;

class SecondBestMST {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.weight = w;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }

        public String toString() {
            return src + " -- " + dest + " == " + weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    static List<Edge> kruskal(List<Edge> edges, int V) {
        Collections.sort(edges);
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            int x = find(subsets, edge.src);
            int y = find(subsets, edge.dest);
            if (x != y) {
                result.add(edge);
                union(subsets, x, y);
                if (result.size() == V - 1)
                    break;
            }
        }
        return result;
    }

    static int totalWeight(List<Edge> edges) {
        int sum = 0;
        for (Edge e : edges) sum += e.weight;
        return sum;
    }

    public static void main(String[] args) {
        int V = 5; 

        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0, 1, 13)); 
        edgeList.add(new Edge(0, 2, 28)); 
        edgeList.add(new Edge(0, 3, 7));  
        edgeList.add(new Edge(1, 2, 27)); 
        edgeList.add(new Edge(1, 4, 39)); 
        edgeList.add(new Edge(2, 3, 2));  
        edgeList.add(new Edge(2, 4, 34)); 
        edgeList.add(new Edge(2, 4, 14)); 
        edgeList.add(new Edge(3, 4, 7));  
        edgeList.add(new Edge(4, 1, 36)); 

        List<Edge> mst = kruskal(edgeList, V);
        int minCost = totalWeight(mst);
        System.out.println("Minimum Spanning Tree:");
        for (Edge e : mst) System.out.println(e);
        System.out.println("MST Cost = " + minCost);

        int secondBestCost = Integer.MAX_VALUE;
        List<Edge> secondBestMST = null;

        for (Edge excluded : mst) {
            List<Edge> tempEdges = new ArrayList<>(edgeList);
            tempEdges.remove(excluded);
            List<Edge> newMST = kruskal(tempEdges, V);
            if (newMST.size() == V - 1) {
                int weight = totalWeight(newMST);
                if (weight > minCost && weight < secondBestCost) {
                    secondBestCost = weight;
                    secondBestMST = newMST;
                }
            }
        }

        if (secondBestMST != null) {
            System.out.println("\nSecond Best MST:");
            for (Edge e : secondBestMST) System.out.println(e);
            System.out.println("Second Best MST Cost = " + secondBestCost);
        } else {
            System.out.println("No second best MST found.");
        }
    }
}
