package model;

import java.util.Observable;

class GameModel extends Observable {
    private GridModel gridModel = new GridModel();
    private QueueModel queueModel = new QueueModel();
    private CurrentScoreModel currentScoreModel = new CurrentScoreModel();

    public GridModel getGridModel() {
        return gridModel;
    }

    public QueueModel getQueueModel() {
        return queueModel;
    }

    public CurrentScoreModel getCurrentScoreModel() {
        return currentScoreModel;
    }

    GameModel() {
        //TheGui gui = new TheGui();
        //grab updated grid and queue
        gridModel.forceUpdate();
        queueModel.forceUpdate();
        currentScoreModel.forceUpdate();

    }
}
