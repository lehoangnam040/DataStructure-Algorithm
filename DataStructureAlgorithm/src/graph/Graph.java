/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import stack_queue.MyQueue;
import stack_queue.MyStack;

/**
 *
 * @author Nam
 * @param <T>
 */
public class Graph<T> {

    int[][] adjMatrix;
    T[] vertices;
    int n;
    int[] deg;

    public Graph(T[] vertices) {
        this.vertices = vertices;
        n = vertices.length;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        if (n == adjMatrix.length && n == adjMatrix[0].length) {
            //check row and column of matrix
            this.adjMatrix = adjMatrix;
        }
    }

    public void displayAdjMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void visit(int index) {
        //index of vertex to be visited in array vertices
        if (index < n) {
            System.out.print(vertices[index] + " ");
        }
    }

    public void breathFirstSearch(int start) {
        //index of start node to be visited in vertices
        MyQueue<Integer> queue = new MyQueue();
        boolean[] visited = new boolean[n];
        queue.enqueue(start);
        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            visit(vertex);
            visited[vertex] = true;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && adjMatrix[vertex][i] > 0) {
                    //find adjacency vertex of this vertex that not visited yet
                    queue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void visitDFS(int start, boolean[] visited) {
        visit(start);
        visited[start] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && adjMatrix[start][i] > 0) {
                //find adjacency vertex of this vertex that not visited yet
                visitDFS(i, visited);
            }
        }
    }

    public void depthFirstSearch(int start) {
        boolean[] visited = new boolean[n];
        visitDFS(start, visited);
    }

    public int oddDegreeVertices(int[] deg) {
        int oddDegVertices = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += adjMatrix[i][j];
            }
            if (deg[i] % 2 == 1) {
                oddDegVertices++;
            }
        }
        return oddDegVertices;
    }

    public int indexEvenVertex() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += adjMatrix[i][j];
            }
            if (deg[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    boolean isolate(int vertex, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            if (matrix[vertex][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public void eulerCycle(int start) {
        MyStack<Integer> stack = new MyStack<>();
        MyQueue<Integer> queue = new MyQueue();
        int[][] matrix = adjMatrix; //temporary matrix for remove the edges
        stack.push(start);
        while (!stack.isEmpty()) {
            int top = stack.top();
            if (isolate(top, matrix)) {
                queue.enqueue(stack.pop());
            } else {
                for (int i = 0; i < n; i++) {
                    if (matrix[top][i] != 0) {
                        stack.push(i);
                        matrix[top][i] -= 1;
                        matrix[i][top] -= 1;
                        break;
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            visit(queue.dequeue());
            System.out.print("-> ");
        }
        System.out.println("");
    }

    public void eulerAlgorithm() {
        deg = new int[n];
        int oddDegVertices = oddDegreeVertices(deg);
        switch (oddDegVertices) {
            case 0:
                System.out.println("Euler cycle");
                eulerCycle(0);
                break;
            case 2:
                System.out.println("Euler path");
                eulerCycle(indexEvenVertex());
                break;
            default:
                System.out.println("neither Euler cycler nor Euler path");
                break;
        }
    }
}
