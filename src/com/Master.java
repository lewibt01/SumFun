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
import model.GridModel;
import model.QueueModel;
import model.TimedGameModel;
import view.LeaderboardView;
import view.TheGui;

//import models and view classes

//will build the initial title menu for the game
public class Master extends JFrame {
    private Master() {
        super();
        Font font = new Font("SansSerif", Font.BOLD, 28);
        Font fontButton = new Font("SansSerif", Font.BOLD, 22);
        //Panel
        JPanel buttonPanel = new JPanel();
        //JButtons
        JButton exitJB = new JButton("Exit Game");
        JButton untimedJB = new JButton("Play Un-timed Game");
        JButton timedJB = new JButton("Play Timed Game");
        JButton leaderJB = new JButton("Click to View LeaderBoard!");
        //JLabel
        JLabel welcome = new JLabel("Welcome To Sum Fun!! \n\n Please make a selection Below!");
        //changes font of JLabel
        welcome.setFont(font);
        //un-timed button set
        untimedJB.setFont(fontButton);
        untimedJB.setBackground(Color.GREEN);
        //exit button set
        exitJB.setFont(fontButton);
        exitJB.setBackground(Color.RED);
        exitJB.setForeground(Color.BLACK);
        //timed button set
        timedJB.setFont(fontButton);
        timedJB.setBackground(Color.YELLOW);
        //leader button set
        leaderJB.setFont(fontButton);
        leaderJB.setBackground(Color.CYAN);
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
        untimedJB.addActionListener(new UntimedButtonListener());
        //exit listener
        exitJB.addActionListener(new ExitButtonListener());
        //timed listener
        timedJB.addActionListener(new TimedButtonListener());
        //leaderBoard Listener
        leaderJB.addActionListener(new LeaderBoardButtonListener());
        //add Buttons to the panel
        buttonPanel.add(untimedJB);
        buttonPanel.add(timedJB);
        buttonPanel.add(leaderJB);
        buttonPanel.add(exitJB);
        this.add(buttonPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        //make an instance of this class to fire the program
        Master m = new Master();
        m.setVisible(true);
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

            //grab updated grid and queue
            gridModel.forceUpdate();
            queueModel.forceUpdate();
            //set gui to visible
            gui.setVisible(true);
        }
    }

    private class TimedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TimedGameModel timedGameModel = new TimedGameModel(5);
            TheGui gui = new TheGui();
            //add observers to the views
            gui.getGridView().addObserver(timedGameModel.getGridModel());
            gui.getGridView().registerGridModel(timedGameModel.getGridModel());
            //tie models to their respective views
            gui.getQueueView().addObserver(timedGameModel.getQueueModel());
            gui.getQueueView().registerQueueModel(timedGameModel.getQueueModel());
            timedGameModel.getGridModel().forceUpdate();
            timedGameModel.getQueueModel().forceUpdate();
            //set gui to visible and the current frame to false to "close"
            gui.setVisible(true);
            gui.update(timedGameModel, null);
            setVisible(false);
            // Timing stuff
            gui.addTimer(timedGameModel);

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
