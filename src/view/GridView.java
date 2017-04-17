package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
//class imports
import model.GridModel;
import model.TileModel;

public class GridView extends JPanel implements Observer {

    private JButton[][] boardButtons;
    private GridModel gridMod;
    private TileModel cellData;
    private QueueView queueLink;//start with no linked queue
    private CurrentScoreView scoreLink;

    GridView() {
        super();
        scoreLink = new CurrentScoreView();
        gridMod = new GridModel();
        int maxRow = gridMod.getMaxRow();
        int maxCol = gridMod.getMaxCol();


        //gridMod = new GridModel();
        boardButtons = new JButton[maxRow][maxCol];

        GridLayout grid = new GridLayout(9, 9, 0, 0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(0), BorderFactory.createBevelBorder(0)));
        //this.setBackground(new Color(123, 255, 24));
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                cellData = gridMod.getGrid()[i][j];
                boardButtons[i][j] = new JButton();
                boardButtons[i][j].setText(cellData.getInt() + "");
                boardButtons[i][j].addMouseListener(new ButtonListener(i, j));
                /*TransferHandler.TransferSupport support = new TransferHandler.TransferSupport(boardButtons[i][j], new StringSelection(boardButtons[i][j].getText()));
                GameController.ValueImportTransferHandler handle = new GameController.ValueImportTransferHandler();
                handle.canImport(support);
                boardButtons[i][j].setTransferHandler(handle);
                //boardButtons[i][j].*/
                add(boardButtons[i][j]);
            }
        }

    }

    public void addObserver(Observable o) {
        o.addObserver(this);
        //register a model with this view...
    }

    @Override
    //this method will update the values populated in the grid
    public void update(Observable o, Object arg) {
        //TODO
        if (o.getClass().getSimpleName().equals("GridModel")) {
            TileModel[][] model = (TileModel[][]) arg;//receive the grid from the model
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardButtons[i][j].setBackground(model[i][j].getColor());
                    if (model[i][j].getBool()) {

                        boardButtons[i][j].setText(model[i][j].getInt() + "");

                    } else {
                        boardButtons[i][j].setText("");
                    }
                }

            }
        }

    }

    //links the grid view to the queue view by reference for data transfer purposes
    public void registerQueueView(QueueView q) {
        queueLink = q;
    }

    //return the currently registered Queue View
    public QueueView getRegisteredQueueView() {
        return queueLink;
    }

    public void registerGridModel(GridModel g) {
        gridMod = g;
    }

    public GridModel getRegisteredGridModel() {
        return gridMod;
    }

    public void registerScoreView(CurrentScoreView c) {
        scoreLink = c;
    }

    public CurrentScoreView getRegisteredScoreView() {
        return scoreLink;
    }

    private class ButtonListener extends MouseAdapter implements MouseListener {
        int row;
        int col;

        ButtonListener(int r, int c) {
            this.row = r;
            this.col = c;
        }

        public void mouseEntered(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.GREEN);
        }

        public void mouseExited(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.WHITE);
        }

        /*
                public void mouseClicked(MouseEvent e) {
                    if (boardButtons[row][col].getText().equals("")) {
                        System.out.println("This is unoccupied");
                        gridMod.setTileValue(row,col,queueLink.getRegisteredQueueModel().dequeue());

                        //TileModel[] neighbors;
                        //neighbors = gridMod.getNeighbors(gridMod.getTilePosition(gridMod.getGrid()[row][col]));

                        gridMod.performCalc(gridMod.getNeighbors(gridMod.getTilePosition(gridMod.getGrid()[row][col])), gridMod.getGrid()[row][col]);
                        //decrement # of moves
                        //currScoreMod.decrMoves();
                    }

                    //gridMod.setTileValue(row,col,queueLink.getRegisteredQueueModel().dequeue());
                    //System.out.println(queueLink.getDisplay()[0].getText() + ":" + boardButtons[row][col].getText() + ":" + row +","+col);

                }
        */
        public void mousePressed(MouseEvent e) {
            if ((boardButtons[row][col].getText().equals("")) || (boardButtons[row][col].getText().equals(" "))) {
                System.out.println("clicked on empty");
                gridMod.setTileValue(row, col, queueLink.getRegisteredQueueModel().dequeue());
                gridMod.performCalc(gridMod.getNeighbors(gridMod.getTilePosition(gridMod.getGrid()[row][col])), gridMod.getGrid()[row][col]);
                gridMod.getCurrScoreMod().setNumberMoves();
                System.out.println("moves: " + gridMod.getCurrScoreMod().getNumberMoves());
            }
        }
    }
}

