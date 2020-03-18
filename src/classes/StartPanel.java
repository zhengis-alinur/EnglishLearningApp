package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.Main.*;

import static classes.Constants.*;
import static classes.Main.mainFrame;

public final class StartPanel extends GrayPanel {

    JButton quizButton;
    JButton wordEditButton;
    JButton quitButton;

    public StartPanel(){
        super();
    }

    @Override
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        super.setPanelDimensions(topHorDim, downHorDim, leftVertDim, rightVertDim);
    }

    @Override
    protected void setCenter() {
        this.center.setLayout(new GridLayout(3,1,1,1));
        center.setBackground(mainGray);
        quizButton = new MyButton("Quiz",myGreen,mainGray.darker());
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.vocabSize==0){
                    JOptionPane.showMessageDialog(mainFrame, "NoWordsException",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    throw new NoWordsException();
                }
                rootCardLayout.show(rootPanel,"quizPanel");
            }
        });

        wordEditButton = new MyButton("Edit word list",myGreen,mainGray.darker());
        wordEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootCardLayout.show(rootPanel,"wordEditPanel");
            }
        });

        quitButton = new MyButton("Quit",myGreen,mainGray.darker());

        center.add(quizButton);
        center.add(wordEditButton);
        center.add(quitButton);

        center.setVisible(true);
    }
}
