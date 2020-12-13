package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import static classes.Constants.*;
import static classes.Main.*;

public class RepeatWithSentencesPanel extends GrayPanel {
    File fileWithSentences = new File("D:\\Senteses.txt");
    BufferedWriter bwSentences;
    public RepeatWithSentencesPanel() {
        super();
    }

    public JLabel wordLabel;
    public JTextField answer;
    private Word currentWord;
    private int wordNum = 0;
    public int countFieldInt = 0;

    @Override
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        super.setPanelDimensions(topHorDim, downHorDim, leftVertDim, rightVertDim);
    }

    @Override
    protected void setPanel() {
        super.setPanel();
    }

    @Override
    protected void setLeftVertical(Dimension d) {
        super.setLeftVertical(d);
        JButton back = new MyButton("back",Color.BLACK,mainGray.brighter());
        leftVertical.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootCardLayout.show(rootPanel,"quizPanel");
                answer.setText("");
                answer.setVisible(true);
            }
        });
    }

    @Override
    protected void setRightVertical(Dimension d) {
        super.setRightVertical(d);
    }

    @Override
    protected void setCenter() {
        super.setCenter();
        wordLabel = new JLabel("sdsd");
        wordLabel.setFont(new Font("consolas",Font.PLAIN,50));
        wordLabel.setForeground(myGreen);
        wordLabel.setVisible(true);
        center.add(wordLabel);
    }

    @Override
    protected void setDownHorizontal(Dimension d) {
        super.setDownHorizontal(d);
        answer = new MyTextField(new Dimension(700,45),35,myOrange,mainGray.brighter());
        answer.setFocusable(true);
        answer.setVisible(true);

        answer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    countFieldInt = Integer.parseInt(quizPanel.countField.getText());
                    System.out.println(wordNum);
                    currentWord = wordList.get(quizPanel.randomQueue.get(wordNum));
                    if((!answer.getText().equalsIgnoreCase(""))) {
                        wordNum++;
                        try {
                            writeSentence(wordLabel.getText(),answer.getText());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if(wordNum<countFieldInt){
                            currentWord = wordList.get(quizPanel.randomQueue.get(wordNum));
                            wordLabel.setText(currentWord.getRandomEngVersion());
                        }else{
                            try {
                                bwSentences.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            wordNum=0;
                            wordLabel.setText("Completed!");
                            answer.setVisible(false);
                            currentWord = null;
                            quizPanel.countField.setText("");
                        }

                    }
                    answer.setText("");
                }
            }
        });

        downHorizontal.add(answer);
    }

    private boolean writeSentence(String word, String answer) throws IOException {
        bwSentences.write(word+" - "+answer+"\n\r");
        return true;
    }
}
