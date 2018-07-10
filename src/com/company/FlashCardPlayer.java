package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashCardPlayer {

    private JFrame frame;
    private JTextArea display;
    private JTextArea answer;
    private JButton showAnswer;

    private ArrayList<FlashCard> cardList;
    private Iterator cardIterator;
    private FlashCard currentCard;
    private int currentCardIndex;
    private boolean isShowAnswer;

    public FlashCardPlayer() {

        //Build UI
        frame = new JFrame("Flash Card Player");
        JPanel mainPanel = new JPanel();
        Font mFont = new Font("Helvetica Neue", Font.BOLD, 22);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        display = new JTextArea(10, 20);
        display.setFont(mFont);

        JScrollPane qJScrollPane = new JScrollPane(display);
        qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        showAnswer = new JButton("Show Answer");

        mainPanel.add(qJScrollPane);
        mainPanel.add(showAnswer);
        showAnswer.addActionListener(new NextCardListener());

        // add menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
        loadMenuItem.addActionListener(new OpenMenuListener());

        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        //Add to frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);

    }


    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    class OpenMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void loadFile(File selectedFile) {


    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardPlayer();
            }
        });
    }

}
