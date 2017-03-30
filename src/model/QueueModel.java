package model;

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
    public int[] getQueue(){
        return visibleNumbers;
    }

    //set the number of queue elements that should be visible to the user
    public void setNumberElements(int input){
        numElements=input;
    }

    //return the number of elements that are allowed to be visible to the user
    public int getNumElements(){
        int tmp = numElements; //no intellij, this is not redundant, It's art
        return tmp;
    }

    //push a value into the queue
    public void add(Integer i){
        queue.addFirst(i);
    }

    //randomly reshuffle the elements of the queue
    public void shuffle(){
        Random r = new Random();
        ArrayList<Integer> tmp = new ArrayList<>();

        //collect all queue elements
        for(int el:queue){
            tmp.add(retrieve());
            System.out.println(el);
        }

        //ensure queue is empty
        queue.clear();

        //re-add elements to the queue
        for(int i=0;i<tmp.size();i++){
            int tmpInt = r.nextInt(tmp.size());//generate random index
            queue.addFirst(tmp.get((int)tmpInt));//grab index and add to queue, ensure search by index not by value
            tmp.remove((int)tmpInt);//make absolutely sure it removes the index not the value
        }

        //tell everybody we updated the queue
        this.setChanged();
        this.notifyObservers();
    }

    //pulls the value out of the end of the queue
    public Integer retrieve(){
        return queue.pollLast();
    }

    public Integer peek(){
        return queue.peekLast();
    }



}
