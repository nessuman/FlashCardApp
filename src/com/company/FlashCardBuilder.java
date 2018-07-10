package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<FlashCard> cardList;
    private JFrame frame;

    public FlashCardBuilder() {
        // Build user interface
        frame = new JFrame("Flash Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold everything
        JPanel mainPanel = new JPanel();

        // Create font
        Font greatFont = new Font("Helvetica Neue", Font.BOLD, 21);

        // Create the JTextArea for the question box
        question = new JTextArea(6, 20);
        //Add line wrapping that cuts off at the whitespaces between words
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(greatFont);

        //Question area
        JScrollPane qJScrollPane = new JScrollPane(question);
        qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Answer area
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(greatFont);

        // JScrollPane for the answer JTextArea
        JScrollPane aJScrollPane = new JScrollPane(answer);
        aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Button
        JButton nextButton = new JButton("Next Card");

        // CardList
        cardList = new ArrayList<FlashCard>();

        // Create a few labels
        JLabel qJLabel = new JLabel("Question");
        JLabel aJLabel = new JLabel("Answer");

        // Add components to mainPanel
        mainPanel.add(qJLabel);
        mainPanel.add(qJScrollPane);
        mainPanel.add(aJLabel);
        mainPanel.add(aJScrollPane);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        //MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        // Add menu items to menu name
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);

        // Add menu name to menu bar
        menuBar.add(fileMenu);

        //Add eventListeners
        newMenuItem.addActionListener(new NewMenuItemListener());
        saveMenuItem.addActionListener(new SaveMenuItemListener());


        // Add to the frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);


    }


    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a flash card
            FlashCard card = new FlashCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();

            System.out.println("Size of ArrayList: " + cardList.size());

        }
    }


    class NewMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New Menu Clicked");
        }
    }

    class SaveMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FlashCard card = new FlashCard(question.getText(), answer.getText());
            cardList.add(card);

            //Create file dialog with file chooser
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());

        }
    }


    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File selectedFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            Iterator<FlashCard> cardIterator = cardList.iterator();

            while (cardIterator.hasNext()) {
                FlashCard card = (FlashCard) cardIterator.next();
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");

                //Format to be like this: Where's Tokyo/Japan
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Couldn't write to file");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        // write your code here

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardBuilder();
            }
        });
    }
}
