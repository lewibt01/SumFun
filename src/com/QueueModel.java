package com;

import java.util.*;

public class QueueModel extends Observable {
    private LinkedList<Integer> queue;
    private int numElements;//number of elements for the queue to actively display
    private int visibleNumbers[] = new int[5];//where the visible numbers in the queue are stored


    public QueueModel(){
        super();
        queue = new LinkedList<>();
        numElements=5;
    }

    //set the number of queue elements that should be visible to the user
    public void setNumberElements(int input){
        numElements=input;
    }

    //return the number of elements that are allowed to be visible to the user
    public int getNumElements(){
        int tmp = numElements; //no intellij, this is not redundant
        return tmp;
    }

    //push a value into the queue
    public void add(Integer i){
        queue.addFirst(i);
    }

    //pulls the value out of the end of the queue
    public Integer retrieve(){
        return queue.getLast();
    }

    //update attached views
    public void update(Observable o, Object arg){

    }


}
