package classes;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    String s;
    Color textColor;
    Color color;
    int fontSize = 15;
    public MyButton(String s, Color textColor, Color color){
        super(s);
        this.s = s;
        this.color = color;
        this.textColor = textColor;
    }
    public MyButton(String s, Color textColor, Color color, int fontSize){
        this(s,textColor,color);
        this.fontSize = fontSize;
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setFont(new Font("consolas",Font.PLAIN,fontSize));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(textColor);
        g2.drawString(s, getWidth()/2-s.length()*4,getHeight()/2+6);
        g2.dispose();
        super.paintComponent(g);
    }
}
