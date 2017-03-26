package com;

import java.util.ArrayList;

public class QueueModel {
    private static ArrayList<Integer> queue;

    public QueueModel(){
        queue=new ArrayList<>();
        initializeQueue();
    }

    //populate the queue with random values
    public void initializeQueue(){
        //generate random numbers here
    }

    public ArrayList<Integer> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<Integer> queue) {
        QueueModel.queue = queue;
    }

    //push a new value to the queue
    public void push(int value){
        //ensure only single digit numbers...
        if(!(value<0 || value>9)){
            queue.add(value);
        }
    }

    //pull a value from the bottom of the queue
    public void pop(){
        queue.get(queue.size()-1);
        queue.remove((int)queue.size()-1);
    }

    //view the last 5 values in the queue
    public int[] peek(){
        int tmp[] = new int[5];
        //store the values in an array to return in a batch
        //  the first value in the array will be 5 from the end,
        //  and the last value will be the end of the queue
        if(queue.size()>=5) {
            for (int i = 4; i >= 0; i--) {
                tmp[i] = queue.get((int) (queue.size() - i));
            }
        }
        return tmp;
    }


}
