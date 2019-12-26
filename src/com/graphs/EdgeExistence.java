package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EdgeExistence {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int N = Integer.parseInt(buffer[0]);
        int M = Integer.parseInt(buffer[1]);
        int[][] grid = new int[N][N];
        for(int i = 0;i < M;i++){
            String[] input = bufferedReader.readLine().split("\\s+");
            int x = Integer.parseInt(input[0]), y = Integer.parseInt(input[1]);
            grid[x-1][y-1] = 1;
            grid[y-1][x-1] = 1;
        }
        int Q = Integer.parseInt(bufferedReader.readLine().trim());
        for(int i = 0;i < Q;i++){
            String[] queries = bufferedReader.readLine().split("\\s+");
            int x = Integer.parseInt(queries[0]), y = Integer.parseInt(queries[1]);
            if(grid[x-1][y-1] == 0){
                System.out.println("NO");
            }
            else{
                System.out.println("YES");
            }
        }

    }
}
