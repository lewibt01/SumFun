package model;

import java.util.Observable;
import java.util.ArrayList;
import java.util.Random;

//will populate the values needed into the view
public class QueueModel extends Observable {
    private ArrayList<Integer> queue;
    private int numElements;
    private Random r;

    public QueueModel() {
        numElements = 5;
        queue = new ArrayList<>();
        r = new Random();

        for(int i=0;i< 50;i++){
            enqueue(r.nextInt(8)+1);
        }

        setChanged();
        notifyObservers(queue);
    }

    public ArrayList<Integer> getQueue() {
        return queue;
    }

    //set the number of queue elements that should be visible to the user
    public void setNumberElements(int input) {
        numElements = input;
        setChanged();
        notifyObservers(queue);
    }

    //return the number of elements that are allowed to be visible to the user
    public int getNumElements() {
        int tmp = numElements; //no intellij, this is not redundant, It's art
        return tmp;
    }

    //randomly reshuffle the elements of the queue
    public void shuffle() {
        r = new Random();
        ArrayList<Integer> tmp = new ArrayList<>();

        //collect all queue elements
        for (int el : queue) {
            tmp.add(dequeue());
            System.out.println(el);
        }

        //ensure queue is empty
        queue.clear();

        //re-add elements to the queue
        for (int i = 0; i < tmp.size(); i++) {
            int tmpInt = r.nextInt(tmp.size());//generate random index
            queue.add(tmp.get(tmpInt));//grab index and add to queue, ensure search by index not by value
            tmp.remove(tmpInt);//make absolutely sure it removes the index not the value
        }

        //tell everybody we updated the queue
        this.setChanged();
        this.notifyObservers(queue);
    }

    public void forceUpdate(){
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
    public int dequeue(){
        int tmp;
        if(queue.size()<=0) {
            tmp = -1;//illegal value to show emptiness
        }else {
            tmp = queue.get((int) 0);
            //System.out.println(tmp + " Dequeued");
            queue.remove((int) 0);
        }
        setChanged();
        notifyObservers(queue);

        return tmp;
    }

    //return, but not remove, least recently added element from the queue
    public Integer peek() {
        return queue.get((int)queue.size()-1);
    }

    //return the element at the specified index. 0 being the most recently added element,
    // and queue.size() being the least recently added element.
    public Integer peek(int i){
        return queue.get(i);
    }

    public int size(){
        return queue.size();
    }




}
