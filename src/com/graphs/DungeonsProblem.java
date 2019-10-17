package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class DungeonsProblem {
    static class Graph{
        String[][] grid;
        Integer[] visited;
        Integer count;
        Integer[] path;

        public Graph(String[][] grid, Integer[] visited, Integer count, Integer[] path) {
            this.grid = grid;
            this.visited = visited;
            this.count = count;
            this.path = path;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer[] dimension = Arrays.stream(bufferedReader.readLine().split("\\s+")).map(x -> Integer.parseInt(x)).toArray(Integer[]::new);
        Integer[][] grid = new Integer[dimension[0]][dimension[1]];
        Integer[] visited = new Integer[dimension[0]*dimension[1]];

    }
}
