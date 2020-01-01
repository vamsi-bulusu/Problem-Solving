package com.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ModularMultiplicativeInverse {
    public static long x,y,d;
    private static long modularExponentiation(long x,long n,long m){
        if(n == 0){
            return 1;
        }
        else if(n%2 == 0){
            return modularExponentiation((x*x)%m,n/2,m);
        }
        else {
            return (x*modularExponentiation((x*x)%m,(n-1)/2,m))%m;
        }
    }
    private static void extendedEuclideanAlgorithm(long a,long b){
        if(b==0){
            d = a;
            x = 1;
            y = 0;
        }
        else{
            extendedEuclideanAlgorithm(b,a%b);
            long temp = x;
            x = y;
            y = temp - (a/b)*y;
        }
    }
    private static long fermatModularMultiplicativeInverse(int c,int m){
        return modularExponentiation(c,m-2,m);
    }
    private static long modInverse(long A, long M)
    {
        extendedEuclideanAlgorithm(A,M);
        return ((x%M)+M)%M;    //x may be negative
    }
    private static long calculate(Long[] val){
        return (modularExponentiation(val[0],val[1],val[3])*modInverse(val[2],val[3]))%val[3];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Long[] buffer = Stream.of(bufferedReader.readLine().split("\\s+")).map(num->Long.parseLong(num)).toArray(Long[]::new);
        System.out.println(calculate(buffer));
    }
}
