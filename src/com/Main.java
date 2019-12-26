package com;

import com.linkedList.classes.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(bufferedReader.readLine().trim());
        String[] numbers = Arrays.copyOf(bufferedReader.readLine().split("\\s+"),numNodes);
        LinkedList<String> linkedList = new LinkedList<>(numbers);
        linkedList.reverse();
        linkedList.display();
        System.out.println(linkedList.middle());
        linkedList.createLoop(linkedList.size());
        System.out.println(linkedList.detectLoop());
    }
}
