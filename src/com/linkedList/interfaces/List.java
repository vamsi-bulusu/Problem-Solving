package com.linkedList.interfaces;

public interface List<T> {

    void add(T data);

    void createLoop(int numNodes);

    Boolean detectLoop();

    void display();

    void reverse();

    int size();
}
