package com.graphs.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class EulerTotient {

    private static Set<Integer> eulersTotient(int number){
        Set<Integer> set = new HashSet<>();
        for(int i=2;i*i<=number;i++){
            while (number%i==0){
                set.add(i);
                number /= i;
            }
        }
        if(number!= 1){
            set.add(number);
        }
        return set;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());
        Set<Integer> set = eulersTotient(number);
        for (Integer integer:set) {
            number = (number/integer)*(integer-1);
        }
        System.out.println(number);
    }
}
