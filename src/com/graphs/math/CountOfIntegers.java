package com.graphs.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CountOfIntegers {
    static class Count{
        Map<Integer, Integer> integerMap;
        int count;

        private Count(Map<Integer, Integer> integerMap, int count) {
            this.integerMap = integerMap;
            this.count = count;
        }
        private void fillMap(String[] elements){
            for(String val:elements){
                if(!integerMap.containsKey(Integer.parseInt(val)))
                    integerMap.put(Integer.parseInt(val),0);
            }
        }
    }
    private static List<Integer> simpleSieve(Count countObj,int LIMIT){
        List<Integer> primes = new ArrayList<>();
        boolean[] mark = new boolean[LIMIT+1];
        Arrays.fill(mark,true);
        for(int i = 2; i*i <= LIMIT; i++){
            if(mark[i]){
                for(int j = 2*i; j < LIMIT;j+=i){
                    mark[j] = false;
                }
            }
        }
        for(int i = 2; i < LIMIT;i++){
            if(mark[i]){
                primes.add(i);
                if(countObj.integerMap.containsKey(i)){
                    countObj.count++;
                }
            }
        }
        return primes;
    }
    private static void segmentedSieve(Count countObj, int LIMIT){
        int limit = (int) Math.floor(Math.sqrt(LIMIT))+1;
        List<Integer> primes = simpleSieve(countObj,limit);

        int low = limit;
        int high = 2*limit;

        while (low < LIMIT){
            if (high >= LIMIT)
                high = LIMIT;

            boolean[] mark = new boolean[limit+1];
            Arrays.fill(mark,true);

            for (Integer prime : primes) {
                int lowLimit = (int) (Math.floor(low / prime) * prime);
                if (lowLimit < LIMIT) {
                    lowLimit += prime;
                }
                for (int j = lowLimit; j < high; j += prime)
                    mark[j - low] = false;
            }
            for(int j = low;j < high;j++){
                if(mark[j-low] && countObj.integerMap.containsKey(j)){
                     countObj.count++;
                }
            }
            low = low + limit;
            high = high + limit;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(bufferedReader.readLine());
        while(test-- != 0){
            int numberOfElements = Integer.parseInt(bufferedReader.readLine());
            String[] elements = bufferedReader.readLine().split("\\s+");
            Map<Integer,Integer> integerMap = new HashMap<>();
            Count countObj = new Count(integerMap,0);
            countObj.fillMap(elements);
            segmentedSieve(countObj,10000000);
            System.out.println(countObj.count);
        }
    }
}
