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
import view.TheGui;

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
        //set title, size, layout etc for the frame
        setTitle("Sum Fun Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(850, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        //add JLabel to Frame
        this.add(welcome, BorderLayout.NORTH);
        buttonPanel.setLayout(new GridLayout(1, 3, 0, 0));
        buttonPanel.add(untimedJB);
        //un-timed listener
        untimedJB.addActionListener(new UntimedButtonListener());
        buttonPanel.add(exitJB);
        //exit listener
        exitJB.addActionListener(new ExitButtonListener());
        buttonPanel.add(timedJB);
        //timed listener
        timedJB.addActionListener(new TimedButtonListener());
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
            gui.getQueueView().addObserver(queueModel);
            //grab updated grid and queue
            gridModel.updateGrid();
            queueModel.updateQueue();
            //set gui to visible and the current frame to false to "close"
            gui.setVisible(true);
            setVisible(false);
        }
    }

    //To Do Needs implemented to be instantiated
    private class TimedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
