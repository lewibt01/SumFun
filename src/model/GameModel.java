package model;

import java.util.Observable;

import view.TheGui;
/**
 * Created by Garrett on 4/16/2017.
 * CS360
 */
class GameModel extends Observable {
    GameModel() {
        GridModel gridModel = new GridModel();
        QueueModel queueModel = new QueueModel();
        TheGui gui = new TheGui();
        //add observers to the views
        gui.getGridView().addObserver(gridModel);
        gui.getGridView().registerGridModel(gridModel);
        //tie models to their respective views
        gui.getQueueView().addObserver(queueModel);
        gui.getQueueView().registerQueueModel(queueModel);
        //grab updated grid and queue
        gridModel.forceUpdate();
        queueModel.forceUpdate();
        //set gui to visible and the current frame to false to "close"
        gui.setVisible(true);
    }
}
