package com;

import model.GridModel;
import model.QueueModel;
import view.TheGui;

public class Master {

    public static void main(String[] args) {
        //test_case____
        //GameController.Test test = new GameController.Test();
        GridModel gridModel = new GridModel();
        QueueModel queueModel = new QueueModel();

        TheGui gui = new TheGui();

        gui.getGridView().addObserver(gridModel);
        gui.getQueueView().addObserver(queueModel);
        gridModel.updateGrid();
        queueModel.updateQueue();

    }
}
