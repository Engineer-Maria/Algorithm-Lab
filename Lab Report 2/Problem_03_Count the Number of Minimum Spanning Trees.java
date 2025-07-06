/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algo_lab_report_03;
/**
 * @author Maris
 */
import java.util.*;

public class Problem_03_CountMSTs {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
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

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 1),
            new Edge(1, 2, 1),
            new Edge(2, 3, 1),
            new Edge(3, 0, 1),
            new Edge(0, 2, 1)
        );

        List<List<Edge>> combinations = new ArrayList<>();
        int n = edges.size();

        for (int i = 0; i < (1 << n); i++) {
            List<Edge> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1)
                    subset.add(edges.get(j));
            }
            if (subset.size() == V - 1) {
                Subset[] sets = new Subset[V];
                for (int k = 0; k < V; k++) {
                    sets[k] = new Subset();
                    sets[k].parent = k;
                    sets[k].rank = 0;
                }

                boolean cycle = false;
                for (Edge e : subset) {
                    int x = find(sets, e.src);
                    int y = find(sets, e.dest);
                    if (x == y) {
                        cycle = true;
                        break;
                    }
                    union(sets, x, y);
                }

                if (!cycle) {
                    combinations.add(subset);
                }
            }
        }

        System.out.println("Number of distinct MSTs: " + combinations.size());
    }
}
