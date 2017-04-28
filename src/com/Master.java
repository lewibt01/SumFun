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
import model.GridModel;
import model.QueueModel;
import model.TimedGameModel;
import view.LeaderboardView;
import view.TheGui;

/**
 * This class will build the initial title menu for the game
 *
 */
public class Master extends JFrame {

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
        untimedJb.setBackground(Color.GREEN);
        //exit button set
        exitJb.setFont(fontButton);
        exitJb.setBackground(Color.RED);
        exitJb.setForeground(Color.BLACK);
        //timed button set
        timedJb.setFont(fontButton);
        timedJb.setBackground(Color.YELLOW);
        //leader button set
        leaderJb.setFont(fontButton);
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

    // will be used to restart a game board
    public void restartGame() {
        //used for referencing the build of the Users interface
        GridModel gridModel = new GridModel();
        QueueModel queueModel = new QueueModel();
        TheGui gui = new TheGui();
        //add observers to the views
        gui.getGridView().addObserver(gridModel);
        gui.getGridView().registerGridModel(gridModel);
        //tie models to their respective views
        gui.getQueueView().addObserver(queueModel);
        gui.getQueueView().registerQueueModel(queueModel);
        //tie current score model to it's view
        gui.getCurrentScoreView().addObserver(gridModel);
        gui.getCurrentScoreView().registerScoreModel(gridModel);
        //grab updated grid and queue
        gridModel.forceUpdate();
        queueModel.forceUpdate();
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
            //used for referencing the build of the Users interface
            GridModel gridModel = new GridModel();
            QueueModel queueModel = new QueueModel();
            TheGui gui = new TheGui();
            //add observers to the views
            gui.getGridView().addObserver(gridModel);
            gui.getGridView().registerGridModel(gridModel);
            //tie models to their respective views
            gui.getQueueView().addObserver(queueModel);
            gui.getQueueView().registerQueueModel(queueModel);
            //tie current score model to it's view
            gui.getCurrentScoreView().addObserver(gridModel);
            gui.getCurrentScoreView().registerScoreModel(gridModel);
            //grab updated grid and queue
            gridModel.forceUpdate();
            queueModel.forceUpdate();
            //set gui to visible
            gui.getTheFrame().setVisible(true);
        }
    }

    private class TimedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TimedGameModel timedGameModel = new TimedGameModel();

            TheGui gui = new TheGui();

            //add observers to the views
            gui.getGridView().addObserver(timedGameModel.getGridModel());
            gui.getGridView().registerGridModel(timedGameModel.getGridModel());
            //tie models to their respective views
            gui.getQueueView().addObserver(timedGameModel.getQueueModel());
            gui.getQueueView().registerQueueModel(timedGameModel.getQueueModel());
            //tie current score model to it's view
            gui.getCurrentScoreView().addObserver(timedGameModel.getGridModel());
            gui.getCurrentScoreView().registerScoreModel(timedGameModel.getGridModel());
            timedGameModel.getGridModel().forceUpdate();
            timedGameModel.getQueueModel().forceUpdate();
            // Timing stuff
            gui.addTimer(timedGameModel);
            timedGameModel.reset(5);
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
