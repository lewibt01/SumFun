package model;

import com.GameController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//will populate the values needed into the view
public class QueueModel extends Observable {
    //private LinkedList<Integer> queue;
    private TileModel[] tileModel;

    //private JButton[] queuebtns;
    //private int numElements;//number of elements for the queue to actively display
    //private int visibleNumbers[] = new int[5];//where the visible numbers in the queue are stored


    public QueueModel() {

        tileModel = new TileModel[5];
        //queuebtns= new JButton[5];
        //queue = new LinkedList<>();
        //numElements = 5;
        for(int i = 0; i < 5; i++){
            int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
            tileModel[i] = new TileModel();
            tileModel[i].setNumber(randomVal);
            tileModel[i].setBoolean(true);

/*            queuebtns[i] = new JButton();
            queuebtns[i].setText(randomVal+"");
            queuebtns[i].setTransferHandler(new GameController.ValueExportTransferHandler(queuebtns[i].getText()));

            queuebtns[i].addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    JButton btn = (JButton) e.getSource();
                    TransferHandler handle = btn.getTransferHandler();
                    handle.exportAsDrag(btn, e, TransferHandler.COPY);
                }
            });*/
        }
        updateQueue();
    }

/*
    public TileModel[] getQueue() {
        return tileModel;
    }
*/
    //returns the queue of buttons to the gui
    public TileModel[] getQueue() {
        return tileModel;
    }

    public void updateQueue() {
        setChanged();
        notifyObservers();
    }

    /*//set the number of queue elements that should be visible to the user
    public void setNumberElements(int input) {
        numElements = input;
    }

    //return the number of elements that are allowed to be visible to the user
    public int getNumElements() {
        int tmp = numElements; //no intellij, this is not redundant, It's art
        return tmp;
    }*/

    /*//randomly reshuffle the elements of the queue
    public void shuffle() {
        Random r = new Random();
        ArrayList<Integer> tmp = new ArrayList<>();

        //collect all queue elements
        for (int el : queue) {
            tmp.add(retrieve());
            System.out.println(el);
        }

        //ensure queue is empty
        queue.clear();

        //re-add elements to the queue
        for (int i = 0; i < tmp.size(); i++) {
            int tmpInt = r.nextInt(tmp.size());//generate random index
            queue.addFirst(tmp.get(tmpInt));//grab index and add to queue, ensure search by index not by value
            tmp.remove(tmpInt);//make absolutely sure it removes the index not the value
        }

        //tell everybody we updated the queue
        this.setChanged();
        this.notifyObservers();
    }*/

   /* //push a value into the queue
    public void add(int i) {
        queue.addFirst(i);
    }*/

    /*public Integer peek() {
        return queue.peekLast();
    }*/

}
