package com.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeNumbers {

    private static boolean isPrime(int n){
        for(int i=2;i*i<=n;i++){
            if(n%i==0)
                return false;
        }
        return true;
    }

    private static int numOfPrimes(int range) {
        int count = 0;
        for (int i = 2; i <= range; i++) {
           if(isPrime(i)){
               count++;
           }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(bufferedReader.readLine());
        System.out.println(numOfPrimes(range));
    }
}
