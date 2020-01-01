package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MicroMaze {

    private int[][] grid;
    private MicroMaze(int N, int M){
        this.grid = new int[N][M];
    }

    private void printGrid(){
        for (int[] ints : this.grid) {
            for (int j = 0; j < this.grid[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
    private Boolean floodFill(int N,int M){
        Boolean[][] visited = new Boolean[N][M];
        for(int i = 0;i < N;i++){
            for(int j = 0;j < M;j++){
                visited[i][j] = false;
            }
        }
        return pathExists(0,0, visited, N-1, M-1);
    }

    private Boolean pathExists(int X,int Y,Boolean[][] visited,int N, int M){
        if(X == N && Y == M) return true;
        if(X > N || Y > M || X < 0 || Y < 0 || this.grid[X][Y] == 0 || visited[X][Y]) return false;
        visited[X][Y] = true;
        if(pathExists(X,Y+1,visited,N,M))
            return true;
        if(pathExists(X,Y-1,visited,N,M))
            return true;
        if (pathExists(X-1, Y,visited, N, M))
            return true;
        return pathExists(X + 1, Y, visited, N, M);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = reader.readLine().split("\\s+");
        int N = Integer.parseInt(buffer[0]);
        int M = Integer.parseInt(buffer[1]);
        MicroMaze maze = new MicroMaze(N, M);
        for(int i = 0;i < N;i++){
            String[] buffer1 = reader.readLine().split("\\s+");
            for(int j = 0;j < M;j++){
                maze.grid[i][j] = Integer.parseInt(buffer1[j]);
            }
        }
        maze.printGrid();
        System.out.println(maze.floodFill(N, M) ? "Yes" : "No");
    }
}
