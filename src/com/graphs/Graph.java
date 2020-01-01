package com.graphs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph {

    private List<LinkedList<Integer>> linkedLists;
    private Boolean[] visited;

    private Graph(int numNodes){
        this.visited = new Boolean[numNodes];
        Arrays.fill(this.visited,false);
        this.linkedLists = new LinkedList<>();
        for(int i = 0;i < numNodes; i++){
            this.linkedLists.add(new LinkedList<>());
        }
    }

    private void add(int v,int u){
        this.linkedLists.get(v-1).add(u-1);
        this.linkedLists.get(u-1).add(v-1);
    }

    private void bfsTraversal(int source){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()){
            int startNode = queue.poll();
            this.visited[startNode] = true;
            System.out.print(startNode +" ");
            List<Integer> list = this.linkedLists.get(startNode);
            for (int item : list) {
                if (!this.visited[item]) {
                    queue.add(item);
                    this.visited[item] = true;
                }
            }
        }
        System.out.println();
    }

    private void dfsTraversal(int source){
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (!stack.empty()){
            int top = stack.pop();
            this.visited[top] = true;
            System.out.print(top+" ");
            LinkedList<Integer> list = this.linkedLists.get(top);
            for (int item : list) {
                if(!this.visited[item]) {
                    this.visited[item] = true;
                    stack.push(item);
                }
            }
        }
        System.out.println();
    }

    private void recursiveDfs(int source){
        this.visited[source] = true;
        System.out.print(source + " ");
        LinkedList<Integer> list = this.linkedLists.get(source);
        for (int integer : list) {
            if (!this.visited[integer])
                recursiveDfs(integer);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(bufferedReader.readLine().trim());
        int numEdges = Integer.parseInt(bufferedReader.readLine().trim());
        Graph graph = new Graph(numNodes);
        Graph graph1 = new Graph(numNodes);
        for(int i = 0;i < numEdges;i++){
            String[] buffer = bufferedReader.readLine().split("\\s+");
            int Source = Integer.parseInt(buffer[0]);
            int Destination = Integer.parseInt(buffer[1]);
            graph.add(Source, Destination);
            graph1.add(Source, Destination);
        }
        int source = Integer.parseInt(bufferedReader.readLine().trim());
        //graph.bfsTraversal(source);
        //graph1.dfsTraversal(source);
          graph.recursiveDfs(source);
    }
}
