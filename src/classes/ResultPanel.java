package classes;

import javax.swing.*;
import java.awt.*;
import static classes.Constants.*;
import static classes.Main.*;

public final class ResultPanel extends GrayPanel{

    JTextArea resultTable;
    JButton okButton;
    JTextField resultTextField;

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
    }

    @Override
    protected void setCenter() {
        super.setCenter();
    }
}
