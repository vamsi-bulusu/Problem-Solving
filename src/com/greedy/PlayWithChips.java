package com.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PlayWithChips {
    static class Pair{
        int value;
        int count;

        private Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }

    }
    private static int maxChips(Map<Integer, Integer> map){
        List<Pair> list = new ArrayList<>();
        for(Map.Entry<Integer,Integer> pair:map.entrySet()){
            list.add(new Pair(pair.getKey(),pair.getValue()));
        }
        list.sort((Pair p1,Pair p2) -> p2.count - p1.count);
        return list.get(0).value;
    }
    private static int calculate(Map<Integer, Integer> map, int index){
        int count = 0;
        for (Map.Entry<Integer,Integer> pair:map.entrySet()) {
            if(pair.getKey() != index) {
                if (index % 2 == 0) {
                    if (pair.getKey() % 2 != 0)
                        count += pair.getValue();
                }
                else{
                    if(pair.getKey() % 2 == 0){
                        count += pair.getValue();
                    }
                }
            }
        }
        return count;
    }
    private static int minCostToMoveChips(Integer[] chips){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<chips.length;i++){
            if(!map.containsKey(chips[i]))
                map.put(chips[i], 1);
            else
                map.put(chips[i], map.get(chips[i])+1);
        }
        int index = maxChips(map);
        System.out.println(index);
        return calculate(map,index);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer[] chips = Arrays.stream(bufferedReader.readLine().split("\\s+")).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(minCostToMoveChips(chips));
    }
}
