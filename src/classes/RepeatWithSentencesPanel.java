package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.Constants.*;

public class RepeatWithSentencesPanel extends GrayPanel {
    public RepeatWithSentencesPanel() {
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
    }
}
