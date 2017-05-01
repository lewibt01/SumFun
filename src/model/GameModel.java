package model;

import java.util.Observable;

/**
 * Model of a game
 * used for Timed and Untimed Games
 */
public class GameModel extends Observable {
    private GridModel gridModel = new GridModel();
    private QueueModel queueModel = new QueueModel();

    public GridModel getGridModel() {
        return gridModel;
    }

    public QueueModel getQueueModel() {
        return queueModel;
    }

    public void resetGame() {
        gridModel.resetGrid();
        queueModel.reset();
        // Update grid and queue
        gridModel.forceUpdate();
        queueModel.forceUpdate();
    }

    public GameModel() {
        gridModel.forceUpdate();
        queueModel.forceUpdate();

    }

    public void reset(int i) {
    }
}
