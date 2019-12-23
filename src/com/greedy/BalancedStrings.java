package com.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BalancedStrings {
    public static int balancedStringSplit(String s) {
        int count_L = 0, count_R = 0, max = 0;
        for(int i = 0;i < s.length();i++){
              if(s.charAt(i) == 'L')
                  count_L++;
              else
                  count_R++;
              if(count_L == count_R){
                  max++;
                  count_L = 0;count_R=0;
              }
          }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        System.out.println(balancedStringSplit(s));
    }
}
