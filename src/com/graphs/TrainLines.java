import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TrainLines {
    private static class Variables{
        private int cost;
        private int time;
        private int periodicity;

        private Variables(int cost, int time, int periodicity) {
            this.cost = cost;
            this.time = time;
            this.periodicity = periodicity;
        }
    }
    private static class Graph{
        private List<ArrayList<Integer>> arrayLists;
        private boolean[] visited;
        private int[] costFunc;
        private int[] totalTime;
        private int[] period;
        private int[] path;

        private Graph(List<ArrayList<Integer>> arrayLists, boolean[] visited, int[] costFunc, int[] totalTime, int[] period, int[] path) {
            this.arrayLists = arrayLists;
            this.visited = visited;
            this.costFunc = costFunc;
            this.totalTime = totalTime;
            this.period = period;
            this.path = path;
        }
    }
    private static int leastCostNeighbour(Graph graph){
        int min = Integer.MAX_VALUE,min_index = 0;
        for (int i = 0; i < graph.costFunc.length; i++){
            if(!graph.visited[i]){
                if(graph.costFunc[i] < min ){
                    min = graph.costFunc[i];
                    min_index = i;
                }
            }
        }
        return min_index;
    }
    private static int[] dijkstra(Graph graph,Map<String, Variables> map,int A,int B){
        int source = 0;
        graph.visited[source] = true;
        graph.costFunc[source] = 0;
        ArrayList<Integer> list = graph.arrayLists.get(source);
        for(int i = 0; i < list.size(); i++){
            int des = list.get(i);
            Variables variables = map.get(String.valueOf(source)+"_"+String.valueOf(des));
            graph.costFunc[des] = A*variables.time + B*variables.cost;
            graph.period[des] = variables.periodicity;
            graph.totalTime[des] = variables.time;
            graph.path[des] = source;
        }
        for (int i = 0; i < graph.arrayLists.size(); i++){
            int neighbour = leastCostNeighbour(graph);
            graph.visited[neighbour] = true;

            ArrayList<Integer> temp = graph.arrayLists.get(neighbour);
            for(int j = 0; j < temp.size(); j++){
                int des = temp.get(j);
                Variables variables = map.get(String.valueOf(neighbour)+"_"+String.valueOf(des));
                int waitingTime = variables.periodicity - (graph.totalTime[neighbour])%variables.periodicity;
                int costFun_des = A*(waitingTime+variables.time) + B*(variables.cost) + graph.costFunc[neighbour];
                if(costFun_des <= graph.costFunc[des]){
                    graph.costFunc[des] = costFun_des;
                    graph.totalTime[des] = waitingTime + variables.time;
                    graph.path[des] = neighbour;
                }
            }
        }
        return graph.path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bufferedReader.readLine().split(" ");
        int A = Integer.parseInt(inputs[2]), B = Integer.parseInt(inputs[3]);
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]);
        List<ArrayList<Integer>> arrayLists = new ArrayList<>();
        Map<String, Variables> variablesMap = new LinkedHashMap<>();
        boolean[] visited = new boolean[n];
        int[] costFunc = new int[n];
        int[] path = new int[n];
        int[] totalTime = new int[n];
        int[] period = new int[n];
        Arrays.fill(visited,false);
        Arrays.fill(path,-1);
        Arrays.fill(costFunc,Integer.MAX_VALUE);
        Arrays.fill(totalTime,Integer.MAX_VALUE);
        Arrays.fill(period,Integer.MAX_VALUE);
        for(int i = 0; i < n; i++){
            arrayLists.add(new ArrayList<>());
        }
        for(int i = 0;i < m;i++){
            String[] input = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(input[0]), end = Integer.parseInt(input[1]);
            int time = Integer.parseInt(input[2]), cost = Integer.parseInt(input[3]),periodicity = Integer.parseInt(input[4]);
            arrayLists.get(start-1).add(end-1);
            variablesMap.put(String.valueOf(start-1) +"_"+ String.valueOf(end-1),new Variables(cost,time,periodicity));
        }
        Graph graph = new Graph(arrayLists,visited,costFunc,totalTime,period,path);

        int[] trainLine = dijkstra(graph,variablesMap,A,B);
        int i = n-1;
        ArrayList<Integer> final_path = new ArrayList<>();
        while(trainLine[i]!=-1){
            final_path.add(trainLine[i]+1);
            i = trainLine[i];
        }
        for(int j = final_path.size()-1;j>=0;j--){
            System.out.print(final_path.get(j)+" ");
        }

    }

}
