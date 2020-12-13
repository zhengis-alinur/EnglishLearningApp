package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static classes.Constants.*;
import static classes.Main.*;

public final class QuizPanel extends GrayPanel {

    TextField quizCount;
    TextField answerField;
    JLabel wordsNumInfo;
    JTextField countField;
    ArrayList<Integer> randomQueue;

    JButton quizTestButton;
    JButton back;
    JButton repeatWithSentencesButton;

    public QuizPanel(){
        super();
    }

    @Override
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        super.setPanelDimensions(topHorDim, downHorDim, leftVertDim, rightVertDim);
    }

    @Override
    protected void setTopHorizontal(Dimension d) {
        super.setTopHorizontal(d);
        JLabel explanationText = new JLabel("Choose the mode of quiz. ");
        explanationText.setFont(new Font("consolas",Font.PLAIN,15));
        explanationText.setForeground(mainGray.brighter().brighter());
        explanationText.setVisible(true);

        String aboutFileTextString;
        if(vocabSize>0){
            aboutFileTextString = "   There are "+vocabSize+" words in your vocabulary today";
        }else if(vocabSize==1){
            aboutFileTextString = "   There is 1 word in your vocabulary today";
        }else{
            aboutFileTextString = "   There is no word in your vocabulary today";
        }

        wordsNumInfo = new JLabel(aboutFileTextString);
        wordsNumInfo.setFont(new Font("consolas",Font.PLAIN,15));
        wordsNumInfo.setForeground(mainGray.brighter().brighter());

        JPanel quizCountPanel = new JPanel();
        quizCountPanel.setLayout(new GridLayout(1,3,1,0));
        quizCountPanel.setBackground(mainGray);

        JLabel aboutCountLabel = new JLabel("   How many questions you wish? ");
        aboutCountLabel.setFont(new Font("consolas",Font.PLAIN,15));
        aboutCountLabel.setForeground(mainGray.brighter().brighter());

        countField = new MyTextField(new Dimension(10,10),15,myGreen,mainGray.brighter());
        countField.setFocusable(true);

        quizCountPanel.add(aboutCountLabel);
        quizCountPanel.add(countField);

        topHorizontal.add(explanationText);
        topHorizontal.add(wordsNumInfo);
        topHorizontal.add(quizCountPanel);
    }

    @Override
    protected void setDownHorizontal(Dimension d) {
        super.setDownHorizontal(d);
    }

    @Override
    protected void setLeftVertical(Dimension d) {
        super.setLeftVertical(d);
        back = new MyButton("back",Color.BLACK,mainGray.brighter());
        leftVertical.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootCardLayout.show(rootPanel,"startPanel");
            }
        });
    }

    @Override
    protected void setCenter() {
        super.setCenter();
        this.center.setLayout(new GridLayout(3,1,1,1));

        quizTestButton = new MyButton("Vocabulary test",myGreen,mainGray.darker());
        quizTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.wordList = WordSupport.downloadWords();
                } catch (NoWordsException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "No words to repeat",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                System.out.println("happened");
                try {
                    if((!countField.getText().equals(""))) {
                        char[] num = countField.getText().toCharArray();
                        boolean isNumber = true;
                        for (char ch : num) {
                            if (Character.isDigit(ch)) {
                                isNumber = true;
                            } else {
                                isNumber = false;
                            }
                        }
                        if(!isNumber){throw new NumberFormatException();}
                    }
                    randomQueue = WordSupport.getRandomQueue(Integer.parseInt(countField.getText()));
                    quizTestPanel.wordLabel.setText(wordList.get(randomQueue.get(0)).getRandomEngVersion());
                    rootCardLayout.show(rootPanel,"quizTestPanel");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Enter number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        repeatWithSentencesButton = new MyButton("Repeat words writing sentences",myGreen,mainGray.darker());
        repeatWithSentencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.wordList = WordSupport.downloadWords();
                } catch (NoWordsException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "No words to repeat",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                System.out.println("happened");
                try {
                    if((!countField.getText().equals(""))) {
                        char[] num = countField.getText().toCharArray();
                        boolean isNumber = true;
                        for (char ch : num) {
                            if (Character.isDigit(ch)) {
                                isNumber = true;
                            } else {
                                isNumber = false;
                            }
                        }
                        if(!isNumber){throw new NumberFormatException();}
                    }
                    randomQueue = WordSupport.getRandomQueue(Integer.parseInt(countField.getText()));
                    repeatWithSentencesPanel.wordLabel.setText(wordList.get(randomQueue.get(0)).getRandomEngVersion());
                    try {
                        repeatWithSentencesPanel.bwSentences = new BufferedWriter(new FileWriter(repeatWithSentencesPanel.fileWithSentences,true));
                    }  catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    rootCardLayout.show(rootPanel,"repeatWithSentencesPanel");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Enter number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });

        center.add(quizTestButton);
        center.add(repeatWithSentencesButton);

        center.setVisible(true);

    }


}
