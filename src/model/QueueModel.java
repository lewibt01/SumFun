package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

//will populate the values needed into the view
public class QueueModel extends Observable {
    private ArrayList<Integer> queue;
    private int numElements;
    private int numQueueItems;
    private Boolean hasShuffled = false;

    public QueueModel() {
        reset();
    }

    public ArrayList<Integer> getQueue() {
        return queue;
    }

    //set the number of queue elements that should be visible to the user
    public void setNumberElements(int input) {
        numQueueItems = input;
        setChanged();
        notifyObservers(queue);
    }

    //return the number of elements that are allowed to be visible to the user
    @SuppressWarnings("UnnecessaryLocalVariable")
    public int getNumElements() {
        int tmp = numElements; //no intellij, this is not redundant, It's art
        return tmp;
    }

    //randomly reshuffle the elements of the queue
    public void shuffle() {
        Collections.shuffle(this.getQueue());
        //tell everybody we updated the queue
        this.setChanged();
        this.notifyObservers(queue);
    }

    public void forceUpdate() {
        setChanged();
        notifyObservers(queue);
    }

    //push a value into the queue
    public void enqueue(Integer i) {
        queue.add(i);
        setChanged();
        notifyObservers(queue);
    }

    //removes the least recently added element from the queue
    public int dequeue() {
        int tmp;
        if (queue.size() <= 0) {
            tmp = -1;//illegal value to show emptiness
        } else {
            tmp = queue.get((int) 0);
            //System.out.println(tmp + " Dequeued");
            queue.remove((int) 0);
        }
        setChanged();
        notifyObservers(queue);

        return tmp;
    }

    //return, but not remove, the first element in the queue (at index 0 of the array)
    public Integer peek() {
        return queue.get(0);
    }

    //return the element at the specified index. 0 being the first element of the array,
    // and queue.size() being the last element of the array.
    public Integer peek(int i) {
        return queue.get(i);
    }

    public int size() {
        return queue.size();
    }

    public Boolean getHasShuffled() {
        return hasShuffled;
    }

    public void setHasShuffled(boolean flag) {
        hasShuffled = flag;
        setChanged();
        notifyObservers(queue);
    }

    public void reset() {
        hasShuffled = false;
        numElements = 5;
        numQueueItems = 51;
        queue = new ArrayList<>();
        Random ranNum = new Random();

        for (int i = 0; i < numQueueItems; i++) {
            enqueue(ranNum.nextInt(10));
        }

        setChanged();
        notifyObservers(queue);
    }


}
