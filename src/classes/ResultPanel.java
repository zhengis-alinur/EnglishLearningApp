package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.Constants.*;
import static classes.Main.*;

public final class ResultPanel extends GrayPanel{

    JTextArea resultTable;
    JButton okButton;
    JTextField resultTextField;
    JLabel resultLabel;

    @Override
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        super.setPanelDimensions(topHorDim, downHorDim, leftVertDim, rightVertDim);
    }

    public ResultPanel(){
        super();
    }

    @Override
    protected void setTopHorizontal(Dimension d) {
        super.setTopHorizontal(d);
        topHorizontal.setLayout(new GridLayout(1,3,1,1));

        //set properties for wordPanel
        JLabel label1 = new JLabel("Your result for test is: ");
        label1.setFont(new Font("consolas",Font.PLAIN,15));
        label1.setForeground(myGreen);

        resultLabel = new JLabel();
        resultLabel.setText(String.valueOf(SCORE));
        resultLabel.setFont(new Font("consolas",Font.PLAIN,15));
        resultLabel.setForeground(myPink);

        JButton okButton = new MyButton("Ok!",myGreen,mainGray.darker());
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordList.clear();
                answerList.clear();
                isRightAnswerList.clear();
                center.removeAll();
                SCORE = 0;
                rootCardLayout.show(rootPanel,"startPanel");
            }
        });
        topHorizontal.add(label1);
        topHorizontal.add(resultLabel);
        topHorizontal.add(okButton);
    }

    @Override
    protected void setCenter() {
        super.setCenter();
        JScrollPane jsp = new JScrollPane(center);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        renewResultsPanel();
    }

    public void renewResultsPanel(){
        center.setLayout(new GridLayout(quizTestPanel.countFieldInt,4,1,1));

        for (int i = 0; i < quizTestPanel.countFieldInt; i++) {
            resultLabel.setText(String.valueOf(SCORE));
            Word word = wordList.get(quizPanel.randomQueue.get(i));
            JPanel allEngVersions = new JPanel();
            allEngVersions.setBackground(mainGray);
            allEngVersions.setLayout(new GridLayout(word.engVersions.size(),1));
            JPanel allRusVersions = new JPanel();
            allRusVersions.setBackground(mainGray);
            allRusVersions.setLayout(new GridLayout(word.rusVersions.size(),1));

            for(int j = 0; j< word.engVersions.size();j++){
                JLabel english = new JLabel(word.engVersions.get(j));
                english.setForeground(Color.white.darker());
                allEngVersions.add(english);
            }
            for(int j = 0; j< word.rusVersions.size();j++){
                JLabel russian = new JLabel(word.rusVersions.get(j));
                russian.setForeground(Color.white.darker());
                allEngVersions.add(russian);
            }

            JLabel answer = new JLabel(String.valueOf(answerList.get(i)));
            JLabel mistake = new JLabel(Boolean.toString(isRightAnswerList.get(i)));

            if(isRightAnswerList.get(i).equals(true)) {
                answer.setForeground(myGreen);
                mistake.setForeground(myGreen);
            }else{
                answer.setForeground(myPink);
                mistake.setForeground(myPink);
            }
            center.add(allEngVersions);
            center.add(allRusVersions);
            center.add(answer);
            center.add(mistake);
        }
    }
}
