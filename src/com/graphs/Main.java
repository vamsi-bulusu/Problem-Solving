package com.graphs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.*;

class Main {

    static class Cost {
        int time;
        int cost;
        int total;

        Cost() {
            this.time = this.cost = this.total = 0;
        }

        Cost(Edge edge, Cost previousCost, int A, int B) {
            this.time = previousCost.time + waitingTime(edge, previousCost) + edge.t;
            this.cost = previousCost.cost + edge.c;
            this.total = A * (this.time) + B * (this.cost);
        }

        private int waitingTime(Edge edge, Cost previousCost) {
            int time = previousCost.time % edge.h;
            return (time == 0) ? time : edge.h - time;
        }

        public int compare(Cost givenCost) {
            return Integer.compare(this.total, givenCost.total);
        }
    }

    static class Edge {
        public int f, e;
        public int t, c, h;

        public Edge(int f, int e, int t, int c, int h) {
            this.f = f;
            this.e = e;
            this.t = t;
            this.c = c;
            this.h = h;
        }
    }

    static class Graph {
        public int n, m, A, B;
        public HashMap<Integer, List<Edge>> adjacencyList;

        Graph(int n, int m, int A, int B) {
            this.n = n;
            this.m = m;
            this.A = A;
            this.B = B;
            adjacencyList = new HashMap<Integer, List<Edge>>();
        }

        public void addEdge(int f, int e, int t, int c, int h) {
            Edge edge = new Edge(f, e, t, c, h);
            if (adjacencyList.containsKey(f)) {
                adjacencyList.get(f).add(edge);
            } else {
                List<Edge> edgeList = new LinkedList<Edge>();
                edgeList.add(edge);
                adjacencyList.put(f, edgeList);
            }
        }

        public Map<Integer, Cost> getSP() {
            Map<Integer, Cost> costs = new HashMap<Integer, Cost>();
            Set<Integer> visited = new HashSet<Integer>();
            Queue<Integer> unvisited = new LinkedList<Integer>();

            unvisited.add(1);
            costs.put(1, new Cost());

            while (!unvisited.isEmpty()) {
                int s = unvisited.poll();

                if (!visited.contains(s)) {
                    visited.add(s);
                }

                Cost sCost = costs.get(s);
                if (adjacencyList.containsKey(s)) {
                    for (Edge edge : adjacencyList.get(s)) {
                        if (!visited.contains(edge.e) && !unvisited.contains(edge.e)) {
                            unvisited.add(edge.e);
                        }

                        if (!costs.containsKey(edge.e)) {
                            costs.put(edge.e, new Cost(edge, sCost, A, B));
                        } else {
                            Cost existingCost = costs.get(edge.e);
                            Cost newCost = new Cost(edge, sCost, A, B);
                            if (newCost.compare(existingCost) < 0) {
                                costs.put(edge.e, newCost);
                            }
                        }
                    }
                }
            }

            return costs;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputBuffer = br.readLine().split("\\s+");
        int n = Integer.parseInt(inputBuffer[0]);
        int m = Integer.parseInt(inputBuffer[1]);
        int A = Integer.parseInt(inputBuffer[2]);
        int B = Integer.parseInt(inputBuffer[3]);

        Graph g = new Graph(n, m, A, B);

        for (int i = 0; i < m; i++) {
            inputBuffer = br.readLine().split("\\s+");
            int f = Integer.parseInt(inputBuffer[0]);
            int e = Integer.parseInt(inputBuffer[1]);
            int t = Integer.parseInt(inputBuffer[2]);
            int c = Integer.parseInt(inputBuffer[3]);
            int h = Integer.parseInt(inputBuffer[4]);
            g.addEdge(f, e, t, c, h);
        }

        Map<Integer, Cost> costs = g.getSP();

        for (Map.Entry<Integer, Cost> entry : costs.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().total);
        }

    }
}