package classes;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(Dimension size, int fontSize, Color textColor, Color color){
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setFont(new Font("calibri",Font.PLAIN,fontSize));
        setForeground(textColor);
        setPreferredSize(size);
        setBackground(color);
    }
}
