package model;

import java.util.Observable;

/**
 * Created by Garrett on 4/12/2017.
 * CS360
 */
class GameModel extends Observable {
    private GridModel gridModel = new GridModel();
    private QueueModel queueModel = new QueueModel();

    public GridModel getGridModel() {
        return gridModel;
    }

    public QueueModel getQueueModel() {
        return queueModel;
    }

    GameModel() {
        //TheGui gui = new TheGui();
        //grab updated grid and queue
        gridModel.forceUpdate();
        queueModel.forceUpdate();

    }
}
