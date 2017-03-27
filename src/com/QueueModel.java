package com;

import java.util.*;

public class QueueModel extends Observable {
    private static LinkedList<Integer> queue;

    public QueueModel(){
        super();
        LinkedList<Integer> data = new LinkedList<>();

    }

    public void initialize(){

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
