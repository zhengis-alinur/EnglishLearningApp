package classes;

import javax.swing.*;
import java.awt.*;
import static classes.Constants.*;

public class GrayPanel extends JPanel{
    JPanel topHorizontal = new JPanel();
    JPanel downHorizontal = new JPanel();
    JPanel leftVertical = new JPanel();
    JPanel rightVertical = new JPanel();
    JPanel center = new JPanel();

    Dimension topHorDim = new Dimension(0,0);
    Dimension downHorDim = new Dimension(0,0);
    Dimension leftVertDim = new Dimension(0,0);
    Dimension rightVertDim = new Dimension(0,0);

    public GrayPanel(){
        this.setLayout(new BorderLayout());
    }
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        this.topHorDim = topHorDim;
        this.downHorDim = downHorDim;
        this.leftVertDim = leftVertDim;
        this.rightVertDim = rightVertDim;
        setPanel();
    }

    protected void setPanel() {
        setTopHorizontal(topHorDim);
        setDownHorizontal(downHorDim);
        setCenter();
        setLeftVertical(leftVertDim);
        setRightVertical(rightVertDim);
        this.add(topHorizontal, BorderLayout.NORTH);
        this.add(downHorizontal, BorderLayout.SOUTH);
        this.add(center, BorderLayout.CENTER);
        this.add(leftVertical, BorderLayout.WEST);
        this.add(rightVertical, BorderLayout.EAST);
    }
    protected void setTopHorizontal(Dimension d) {
        topHorizontal = getEmptyPanel();
        topHorizontal.setPreferredSize(d);
    }
    protected void setDownHorizontal(Dimension d) {
        downHorizontal = getEmptyPanel();
        downHorizontal.setPreferredSize(d);
    }
    protected void setLeftVertical(Dimension d) {
        leftVertical = getEmptyPanel();
        leftVertical.setPreferredSize(d);
    }
    protected void setRightVertical(Dimension d) {
        rightVertical = getEmptyPanel();
        rightVertical.setPreferredSize(d);
    }
    protected void setCenter() {
        this.center = getEmptyPanel();
    }
    static JPanel getEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(true);
        emptyPanel.setBackground(mainGray);
        return emptyPanel;
    }
}
