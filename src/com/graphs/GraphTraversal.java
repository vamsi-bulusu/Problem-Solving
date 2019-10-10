package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphTraversal {
    private static void dfsTraversal(List<ArrayList<Integer>> arrayLists,Boolean []visited,int source){
          if(visited[source]){
              return;
          }
          visited[source] = true;
          System.out.println(source);
          ArrayList<Integer> temp =  arrayLists.get(source);
        for (Integer integer : temp) {
            dfsTraversal(arrayLists, visited, integer);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<ArrayList<Integer>> arrayLists = new ArrayList<>();
        int num_nodes = Integer.parseInt(bufferedReader.readLine());
        int num_edges = Integer.parseInt(bufferedReader.readLine());
        Boolean[] visited = new Boolean[num_nodes];
        Arrays.fill(visited,Boolean.FALSE);
        for(int i=0;i<num_nodes;i++){
            arrayLists.add(new ArrayList<>());
        }
        for(int i=0;i<num_edges;i++){
            String[] node = bufferedReader.readLine().split(" ");
            arrayLists.get(Integer.parseInt(node[0])).add(Integer.parseInt(node[1]));
        }
        int source = Integer.parseInt(bufferedReader.readLine());
        dfsTraversal(arrayLists,visited,source);
    }
}
