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

import model.GridModel;
import model.TileModel;

public class GridView extends JPanel implements Observer {

    private JButton[][] boardButtons;
    private GridModel gridMod;

    GridView() {
        super();

        TileModel cellData;
        gridMod = new GridModel();
        int maxRow = gridMod.getMaxRow();
        int maxCol = gridMod.getMaxCol();


        //gridMod = new GridModel();
        boardButtons = new JButton[maxCol][maxRow];

        GridLayout grid = new GridLayout(9, 9, 0, 0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(0), BorderFactory.createBevelBorder(0)));
        //this.setBackground(new Color(123, 255, 24));
        for (int i = 0; i < maxCol; i++) {
            for (int j = 0; j < maxRow; j++) {
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
    //this method will update the observers with the values populated on the grid
    public void update(Observable o, Object arg) {
        //TODO
        if (o.getClass().getSimpleName().equals("GridModel")) {
            TileModel[][] model = ((GridModel) o).getGrid();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardButtons[i][j].setBackground(model[i][j].getColor());
                    if(model[i][j].getBool()){
                        boardButtons[i][j].setText(model[i][j].getInt() + "");

                    }else{
                        boardButtons[i][j].setText("");
                    }
                }

            }
        }
    }


    private class GMController implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "FUBAR":
                    System.exit(0);
                    break;
/*
                case "RETRIEVE":
                    model.retrieve();
                    break;
                case "SHUFFLE":
                    model.shuffle();
*/
            }
        }
    }


    private class ButtonListener extends MouseAdapter implements MouseListener {
        int row;
        int col;

        ButtonListener(int c, int r) {
            this.col = c;
            this.row = r;
        }

        public void mouseEntered(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.GREEN);
        }

        public void mouseExited(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.WHITE);
        }

        public void mouseClicked(MouseEvent e) {
            if (boardButtons[col][row].getText().equals("")) {
                System.out.println("This is unoccupied");
        }
        }
    }


}

