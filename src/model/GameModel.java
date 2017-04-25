package model;

import java.util.Observable;

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
        gridModel.forceUpdate();
        queueModel.forceUpdate();

    }
}
