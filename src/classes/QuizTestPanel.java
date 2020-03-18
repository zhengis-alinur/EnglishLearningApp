package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static classes.Constants.*;
import static classes.Main.*;

public class QuizTestPanel extends GrayPanel {

    public JLabel wordLabel;
    public JTextField answer;
    private Word currentWord;
    private int wordNum = 0;
    public int countFieldInt = 0;

    public QuizTestPanel() {
        super();
    }

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
        answer = new MyTextField(new Dimension(300,45),35,myOrange,mainGray.brighter());
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
                        if(((currentWord.rusVersions.contains(wordLabel.getText()))&&(currentWord.engVersions.contains(answer.getText())))||((currentWord.engVersions.contains(wordLabel.getText()))&&(currentWord.rusVersions.contains(answer.getText())))) {
                            SCORE++;
                            isRightAnswerList.add(true);
                            System.out.println("Compated");
                        }else{isRightAnswerList.add(false);}
                        answerList.add(answer.getText());
                        questionList.add(wordLabel.getText());
                        wordNum++;
                        if(wordNum<countFieldInt){
                            currentWord = wordList.get(quizPanel.randomQueue.get(wordNum));
                            wordLabel.setText(currentWord.getRandomEngVersion());
                        }else{
                            wordNum=0;
                            answer.setText("");
                            currentWord = null;
                            quizPanel.countField.setText("");
                            resultPanel.renewResultsPanel();
                            rootCardLayout.show(rootPanel,"resultPanel");
                        }
                    }
                    answer.setText("");
                }
            }
        });

        downHorizontal.add(answer);
    }


}
