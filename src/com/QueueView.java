package com;

import java.util.*;

public class QueueView implements Observer{
    private QueueModel data;

    public QueueView(){
        data=new QueueModel();
        //should add itself as an observer to its own view
    }

    public void addObserver(Observer o){

    }

    public void update(Observable o, Object arg){

    }



}
