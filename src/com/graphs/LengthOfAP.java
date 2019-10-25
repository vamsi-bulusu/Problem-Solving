package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class LengthOfAP {
    private static int countSubArrLen(Integer[] elements, int start, int end,int diff){
        int count = 1,max = 0;
        for(int i = start; i < end; i++) {
            int actDiff = elements[i] - elements[i - 1];
            if (actDiff == diff) {
                 count++;
                 if (max < count)
                      max = count;
            } else
                count = 1;
        }
        return max == 0 ? 1:max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputBuffer = bufferedReader.readLine().split("\\s+");
        Integer[] elements = Stream.of(bufferedReader.readLine().split("\\s+")).map(Integer::parseInt).toArray(Integer[]::new);
        for(int i = 0;i < Integer.parseInt(inputBuffer[1]); i++){
            String[] queries = bufferedReader.readLine().split("\\s+");
            int start = Integer.parseInt(queries[0]);
            int end = Integer.parseInt(queries[1]);
            int diff = Integer.parseInt(queries[2]);
            System.out.println(countSubArrLen(elements,start,end,diff));
        }

    }
}
