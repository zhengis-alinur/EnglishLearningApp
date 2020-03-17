package classes;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Constants {
    final static Color mainGray = new Color(73,77,78);
    final static Color myPink = new Color(200,132,155);
    final static Color myGreen = new Color(0,204,125);
    final static Color myOrange = new Color(203,97,45);

    final static File wordsFolder = new File("D:\\MyWords");

    final static JPanel rootPanel = new JPanel();
    final static CardLayout rootCardLayout = new CardLayout();

    final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    final static int FRAME_WIDTH = 800;
    final static int FRAME_HEIGHT = 400;
    final static int LOCATION_X = (SCREEN_SIZE.width - FRAME_WIDTH) / 2;
    final static int LOCATION_Y = (SCREEN_SIZE.height - FRAME_HEIGHT) / 2;
}
