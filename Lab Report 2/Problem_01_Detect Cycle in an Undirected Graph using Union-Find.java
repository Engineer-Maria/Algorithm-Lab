/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algo_lab_report_03;
/**
 * @author Maris
 */
import java.util.*;

class Problem_01_DetectCycle {

    static class Edge {
        int src, dest;
        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[e];
            for (int i = 0; i < e; i++)
                edges[i] = new Edge(0, 0);
        }
    }

    static int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }

    static boolean isCycle(Graph graph) {
        int[] parent = new int[graph.V];
        Arrays.fill(parent, -1);

        for (int i = 0; i < graph.E; i++) {
            int x = find(parent, graph.edges[i].src);
            int y = find(parent, graph.edges[i].dest);

            if (x == y)
                return true;
            union(parent, x, y);
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(3, 3);

        graph.edges[0] = new Edge(0, 1);
        graph.edges[1] = new Edge(1, 2);
        graph.edges[2] = new Edge(0, 2);

        if (isCycle(graph))
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
    }
}
