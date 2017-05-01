package com;

//import java files

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
//import models and view classes
import model.GameModel;
import model.TimedGameModel;
import view.LeaderboardView;
import view.TheGui;

/**
 * This class will build the initial title menu for the game
 */
public class Master extends JFrame {
    private GameModel gameModel = null;
    private boolean timedMode = false;

    public boolean isTimedMode() {
        return timedMode;
    }

    public Master() {
        super();
        Font font = new Font("SansSerif", Font.BOLD, 28);
        Font fontButton = new Font("SansSerif", Font.BOLD, 22);
        //Panel
        JPanel buttonPanel = new JPanel();
        //JButtons
        JButton exitJb = new JButton("Exit Game");
        JButton untimedJb = new JButton("Play Un-timed Game");
        JButton timedJb = new JButton("Play Timed Game");
        JButton leaderJb = new JButton("Click to View LeaderBoard!");
        //JLabel
        JLabel welcome = new JLabel("Welcome To Sum Fun!! \n\n Please make a selection Below!");
        //changes font of JLabel
        welcome.setFont(font);
        //un-timed button set
        untimedJb.setFont(fontButton);
        untimedJb.setOpaque(true);
        untimedJb.setBackground(Color.GREEN);
        //exit button set
        exitJb.setFont(fontButton);
        exitJb.setOpaque(true);
        exitJb.setBackground(Color.RED);
        exitJb.setForeground(Color.BLACK);
        //timed button set
        timedJb.setFont(fontButton);
        timedJb.setOpaque(true);
        timedJb.setBackground(Color.YELLOW);
        //leader button set
        leaderJb.setFont(fontButton);
        leaderJb.setOpaque(true);
        leaderJb.setBackground(Color.CYAN);
        //set title, size, layout etc for the frame
        setTitle("Sum Fun Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(850, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        //add JLabel to Frame
        this.add(welcome, BorderLayout.NORTH);
        buttonPanel.setLayout(new GridLayout(2, 2, 0, 0));
        //un-timed listener
        untimedJb.addActionListener(new UntimedButtonListener());
        //exit listener
        exitJb.addActionListener(new ExitButtonListener());
        //timed listener
        timedJb.addActionListener(new TimedButtonListener());
        //leaderBoard Listener
        leaderJb.addActionListener(new LeaderBoardButtonListener());
        //add Buttons to the panel
        buttonPanel.add(untimedJb);
        buttonPanel.add(timedJb);
        buttonPanel.add(leaderJb);
        buttonPanel.add(exitJb);
        this.add(buttonPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        //make an instance of this class to fire the program
        Master m = new Master();
        m.setVisible(true);
    }

    /**
     * will be used to restart a game board
     */
    public void restartGame() {
        //used for referencing the build of the Users interface
        if (timedMode) {
            gameModel = new TimedGameModel();

            gameModel.reset(5);
        } else {
            GameModel gameModel = new GameModel();
        }
        gameModel.resetGame();
        TheGui gui = new TheGui(gameModel);

        //add observers to the views
        gui.getGridView().addObserver(gameModel.getGridModel());
        gui.getGridView().registerGridModel(gameModel.getGridModel());
        //tie models to their respective views
        gui.getQueueView().addObserver(gameModel.getQueueModel());
        gui.getQueueView().registerQueueModel(gameModel.getQueueModel());
        //tie current score model to it's view
        gui.getCurrentScoreView().addObserver(gameModel.getGridModel());
        gui.getCurrentScoreView().registerScoreModel(gameModel.getGridModel());
        gameModel.getGridModel().forceUpdate();
        gameModel.getQueueModel().forceUpdate();
        // Timing stuff
        if (timedMode) {
            gui.addTimer((TimedGameModel) gameModel);
        }
        //set gui to visible
        gui.getTheFrame().setVisible(true);
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class UntimedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timedMode = false;
            GameModel gameModel = new GameModel();

            TheGui gui = new TheGui(gameModel);

            //add observers to the views
            gui.getGridView().addObserver(gameModel.getGridModel());
            gui.getGridView().registerGridModel(gameModel.getGridModel());
            //tie models to their respective views
            gui.getQueueView().addObserver(gameModel.getQueueModel());
            gui.getQueueView().registerQueueModel(gameModel.getQueueModel());
            //tie current score model to it's view
            gui.getCurrentScoreView().addObserver(gameModel.getGridModel());
            gui.getCurrentScoreView().registerScoreModel(gameModel.getGridModel());
            gameModel.getGridModel().forceUpdate();
            gameModel.getQueueModel().forceUpdate();

            //set gui to visible and the current frame to false to "close"
            gui.getTheFrame().setVisible(true);
        }
    }

    private class TimedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timedMode = true;
            GameModel gameModel = new TimedGameModel();

            TheGui gui = new TheGui(gameModel);

            //add observers to the views
            gui.getGridView().addObserver(gameModel.getGridModel());
            gui.getGridView().registerGridModel(gameModel.getGridModel());
            //tie models to their respective views
            gui.getQueueView().addObserver(gameModel.getQueueModel());
            gui.getQueueView().registerQueueModel(gameModel.getQueueModel());
            //tie current score model to it's view
            gui.getCurrentScoreView().addObserver(gameModel.getGridModel());
            gui.getCurrentScoreView().registerScoreModel(gameModel.getGridModel());
            gameModel.getGridModel().forceUpdate();
            gameModel.getQueueModel().forceUpdate();
            // Timing stuff
            gui.addTimer((TimedGameModel) gameModel);
            ((TimedGameModel) gameModel).resetTimer((float) 5);
            //set gui to visible and the current frame to false to "close"
            gui.getTheFrame().setVisible(true);
            //setVisible(false);

        }
    }

    private class LeaderBoardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LeaderboardView leaderBoard;
            try {
                leaderBoard = new LeaderboardView();
                leaderBoard.setVisible(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}
