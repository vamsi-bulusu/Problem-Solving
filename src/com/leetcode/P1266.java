package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1266 {

    private int minTimeToVisitAllPoints(int[][] points) {
         int time = 0;
         for (int i = 1;i < points.length;i++){
             int deltaX = Math.abs(points[i][0]-points[i-1][0]);
             int deltaY = Math.abs(points[i][1]-points[i-1][0]);
             time += Math.max(deltaX,deltaY);
         }
         return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numPoints = Integer.parseInt(bufferedReader.readLine());
        int[][] points = new int[numPoints][2];
        for(int i = 0;i < numPoints;i++){
            String[] buffer = bufferedReader.readLine().split("\\s+");
            points[i][0] = Integer.parseInt(buffer[0]);
            points[i][1] = Integer.parseInt(buffer[1]);
        }
        P1266 p1266 = new P1266();
        System.out.println(p1266.minTimeToVisitAllPoints(points));
    }
}
