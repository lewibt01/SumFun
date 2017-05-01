package view;
//imports

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
//class imports
import static java.awt.Color.cyan;

import model.GridModel;
import model.TileModel;

public class GridView extends JPanel implements Observer {
    private JPopupMenu popup;
    private JMenuItem menuItem;
    private JButton[][] boardButtons;
    private GridModel gridMod;
    private QueueView queueLink;//start with no linked queue
    private TimerView timerView;
    private CurrentScoreView scoreLink;
    private TheGui guiReg;

    GridView() {
        super();
        Font font = new Font("SansSerif", Font.BOLD, 18);
        TileModel cellData;
        popup = new JPopupMenu();
        menuItem = new JMenuItem("Remove All Like Numbers!");
        popup.add(menuItem);
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
                boardButtons[i][j].setOpaque(true);
                boardButtons[i][j].setFont(font);
                boardButtons[i][j].setText(cellData.getInt() + "");
                boardButtons[i][j].addMouseListener(new ButtonListener(i, j));
                menuItem.addMouseListener(new ButtonListener(i, j));
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
                        if (boardButtons[i][j].getText().equals("-1")) {
                            boardButtons[i][j].setText("");
                        }
                    } else {
                        boardButtons[i][j].setText("");
                    }
                }

            }
        }

    }

    void registerGui(TheGui gui) {
        guiReg = gui;
    }

    //links the grid view to the queue view by reference for data transfer purposes
    void registerQueueView(QueueView q) {
        queueLink = q;
    }

    //grab the model created
    public void registerGridModel(GridModel g) {
        gridMod = g;
    }

    GridModel getRegisteredGridModel() {
        return gridMod;
    }

    //grab the scoreView to use in the controller
    void registerScoreView(CurrentScoreView c) {
        scoreLink = c;
    }

    //grab the timers view to user in the controller
    void registerTimerView(TimerView t) {
        timerView = t;
    }


    //this listener will let the user know what tile their cursor is on
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

        public void mousePressed(MouseEvent e) {
            if ((boardButtons[row][col].getText().equals("0"))
                    ||
                    (boardButtons[row][col].getText().equals("1"))
                    ||
                    (boardButtons[row][col].getText().equals("2"))
                    ||
                    (boardButtons[row][col].getText().equals("3"))
                    ||
                    (boardButtons[row][col].getText().equals("4"))
                    ||
                    (boardButtons[row][col].getText().equals("5"))
                    ||
                    (boardButtons[row][col].getText().equals("6"))
                    ||
                    (boardButtons[row][col].getText().equals("7"))
                    ||
                    (boardButtons[row][col].getText().equals("8"))
                    ||
                    (boardButtons[row][col].getText().equals("9"))) {
                if (queueLink.canRemoveNumber()) {
                    gridMod.removeSame(Integer.parseInt(boardButtons[row][col].getText()));
                    queueLink.getRemoveJButton().setEnabled(false);
                }
                //checkbox functionality goes here

            } else if ((boardButtons[row][col].getText().equals("")) || (boardButtons[row][col].getText().equals(" "))) {
                System.out.println("clicked on empty");
                gridMod.setTileValue(row, col, queueLink.getRegisteredQueueModel().dequeue());
                gridMod.performCalc(gridMod.getNeighbors(gridMod.getTilePosition(gridMod.getGrid()[row][col])), gridMod.getGrid()[row][col]);
                gridMod.setNumberMoves();
                gridMod.setCurrentScore(gridMod.getCurrentScore());
                if (gridMod.countFilledTiles() > 80 || gridMod.getIntMoves() <= 0) {
                    //the game is over
                    int option = JOptionPane.showConfirmDialog(null, "Would you like to start a new game?", "Start New Game?", JOptionPane.YES_NO_OPTION);
                    if (option == 0) {
                        //yes option
                        //resets the grid and queue
                        gridMod.resetGrid();
                        //make sure to enable buttons to start new game
                        queueLink.getHintJButton().setEnabled(true);
                        queueLink.getRemoveJButton().setEnabled(true);
                        queueLink.getRegisteredQueueModel().reset();
                        queueLink.getShuffleJButton().setEnabled(true);
                        //sends 5 minutes to the timer
                        timerView.getRegisteredTimeModel().reset(5);
                    } else {
                        //closes program
                        System.exit(0);
                    }
                }

            }
        }

    }
}



