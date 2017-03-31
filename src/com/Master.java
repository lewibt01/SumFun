package com;

import model.GridModel;
import model.QueueModel;
import view.TheGUI;

public class Master {

    public static GridModel gridModel;
    public static QueueModel queueModel;

    public static void main(String args[]) {
        //test_case____
        //GameController.Test test = new GameController.Test();
        
        TheGUI gui = new TheGUI();
        gridModel = new GridModel();
        queueModel = new QueueModel();
        gui.getGridView().addObserver(gridModel);
        gui.getQueueView().addObserver(queueModel);
        gridModel.updateGrid();
        queueModel.updateQueue();

    }
}
