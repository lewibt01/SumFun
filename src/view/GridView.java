package view;

import com.GameController;
import model.GridModel;
import model.TileModel;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GridView extends JPanel implements Observer {

    private JButton[][] boardButtons = new JButton[9][9];

    public GridView() {

        super();

        GridLayout grid = new GridLayout(9, 9, 0, 0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(0),BorderFactory.createBevelBorder(0)));
        //this.setBackground(new Color(123, 255, 24));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardButtons[i][j] = new JButton();
                boardButtons[i][j].addMouseListener(new ButtonListener());
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
    public void update(Observable o, Object arg) {
        //TODO

        if (o.getClass().getSimpleName().equals("GridModel")) {
            TileModel[][] model = ((GridModel) o).getGrid();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardButtons[i][j].setText(model[i][j].getInt() + "");
                    boardButtons[i][j].setBackground(model[i][j].getColor());


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

    private class ButtonListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.GREEN);
        }

        public void mouseExited(MouseEvent e) {
            JButton buttonPress = (JButton) e.getSource();
            buttonPress.setBackground(Color.WHITE);
        }
    }

}
