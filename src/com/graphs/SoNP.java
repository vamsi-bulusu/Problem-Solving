package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SoNP {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int N = Integer.parseInt(buffer[0]);
        int M = Integer.parseInt(buffer[1]);
        int K = Integer.parseInt(buffer[2]);
        for(int i = 0;i < M;i++){
            String[] input = bufferedReader.readLine().split("\\s+");
        }
        System.out.println((M-N+K) < 0 ? -1 : M+K-N);
    }
}
