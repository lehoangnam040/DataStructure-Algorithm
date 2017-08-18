/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import stack_queue.MyStack;

/**
 *
 * @author Nam
 */
public class WeightedGraph<T> extends Graph<T> {

    public static final int INF = 99;
    int[][] weightMatrix;

    public WeightedGraph(T[] vertices) {
        super(vertices);
    }

    public void setWeightMatrix(int[][] weightMatrix) {
        if (n == weightMatrix.length && n == weightMatrix[0].length) {
            this.weightMatrix = weightMatrix;
        }
    }

    //Dijikstra
    public int findIndexOfMinPath(boolean[] selected, int[] path) {
        int index = 0;
        int min = INF;
        for (int i = 0; i < n; i++) {
            if (!selected[i] && path[i] < min) {
                index = i;
                min = path[i];
            }
        }
        return index;
    }

    public void traceToStart(int[] trace, int start, int finish) {
        MyStack<Integer> stack = new MyStack();
        int index = finish;
        while (index != -1) {
            stack.push(index);
            index = trace[index];
        }
        System.out.print("Shortest path is: ");
        while (!stack.isEmpty()) {
            visit(stack.pop());
            System.out.print("-> ");
        }
        System.out.println("");
    }

    public void dijkstraAlgorithm(int start, int finish) {
        int[] path = new int[n]; //weight of path from start to others vertices
        boolean[] selected = new boolean[n];
        int[] trace = new int[n]; //array to find trace of vertex
        //initialize path of all vertex
        for (int i = 0; i < n; i++) {
            path[i] = INF;
        }
        path[start] = 0;
        trace[start] = -1;
        //run the loop
        for (int i = 0; i < n; i++) {
            int index = findIndexOfMinPath(selected, path);
            selected[index] = true;
            if (index == finish) {
                //trace to the start
                traceToStart(trace, start, finish);
                System.out.println("Shortest path length is " + path[index]);
                break;
            } else {
                for (int j = 0; j < n; j++) {
                    if (!selected[j] && (path[j] > weightMatrix[index][j] + path[index])) {
                        path[j] = weightMatrix[index][j] + path[index];
                        trace[j] = index;
                    }
                }
            }
        }
    }

    //Floyd
    public int[][] floydMatrix(int[][] trace) {
        int[][] floyd = weightMatrix;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                        trace[i][j] = trace[i][k];
                    }
                }
            }
        }
        return floyd;
    }

    public void floydAlgorithm(int start, int finish) {
        //initialize trace matrix
        int[][] trace = new int[weightMatrix.length][weightMatrix[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trace[i][j] = j;
            }
        }
        int[][] floyd = floydMatrix(trace);
        System.out.print("shortest path is ");
        visit(start);
        System.out.print("-> ");
        int tmp = trace[start][finish];
        while (tmp != finish) {
            visit(tmp);
            System.out.print("-> ");
            tmp = trace[tmp][finish];
        }
        visit(tmp);
        System.out.println("");
        System.out.println("shortest path length is " + floyd[start][finish]);
    }

    //Prim-Jarnik
    public void primjarnikAlgorithm(int start) {
        boolean[] tree = new boolean[n];
        int[] d = new int[n]; //distance from tree to i is d[i]
        int[] trace = new int[n]; //trace to determine the vertex to add the vertex i to is trace[i]
        //initialize
        for (int i = 0; i < n; i++) {
            trace[i] = -1;
            if (i == start) {
                d[i] = 0;
            } else {
                d[i] = INF;
            }
        }
        //loop of algorithm
        int index;
        for (int i = 0; i < n; i++) {
            index = findIndexOfMinPath(tree, d);
            tree[index] = true;     //add index to tree
            for (int j = 0; j < n; j++) {
                if (!tree[j] && d[j] > weightMatrix[index][j]) {
                    d[j] = weightMatrix[index][j];
                    trace[j] = index;
                }
            }
        }
        System.out.println(Arrays.toString(d));
        System.out.println(Arrays.toString(trace));
    }

    //Kruskal
    public int getRoot(int index, int[] trace) {
        int root = index;
        while (trace[root] != -1) {
            root = trace[root];
        }
        return root;
    }

    public void kruskalAlgorithm() {
        class Edge implements Comparable<Edge> {

            public Edge(int start, int end, int weight) {
                this.start = start;
                this.end = end;
                this.weight = weight;
            }

            public int start;
            public int end;
            public int weight;

            @Override
            public int compareTo(Edge o) {
                if (this.weight == o.weight) {
                    if (this.start == o.start) {
                        return this.end - o.end;
                    } else {
                        return this.start - o.start;
                    }
                } else {
                    return this.weight - o.weight;
                }
            }

            @Override
            public String toString() {
                return "Edge " + vertices[start] + "" + vertices[end] + ": " + weight;
            }

        }
        TreeSet<Edge> edges = new TreeSet();
        ArrayList<Edge> tree = new ArrayList();
        int[] trace = new int[n];
        //initalize
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (weightMatrix[i][j] != INF) {   //add to set of edges
                    edges.add(new Edge(i, j, weightMatrix[i][j]));
                }
            }
            trace[i] = -1;
        }
        for (Edge e : edges) {
            if (getRoot(e.start, trace) != getRoot(e.end, trace)) {
                tree.add(e);
                if (tree.size() == n - 1) {
                    break;
                } else {
                    trace[getRoot(e.end, trace)] = e.start;
                }
            }
        }
        System.out.println(tree);
    }
}
