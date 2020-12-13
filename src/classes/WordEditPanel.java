package classes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static classes.Constants.*;
import static classes.Main.*;

public final class WordEditPanel extends GrayPanel {


    @Override
    public void setPanelDimensions(Dimension topHorDim, Dimension downHorDim, Dimension leftVertDim, Dimension rightVertDim) {
        super.setPanelDimensions(topHorDim, downHorDim, leftVertDim, rightVertDim);
    }

    public WordEditPanel(){
        super();
    }

    @Override
    protected void setLeftVertical(Dimension d) {
        super.setLeftVertical(d);
        JButton back = new MyButton("back",Color.BLACK,mainGray.brighter());
        leftVertical.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootCardLayout.show(rootPanel,"startPanel");
            }
        });
    }

    @Override
    protected void setCenter() {
        super.setCenter();

        center = new JPanel();
        center.setBackground(mainGray);
        center.setLayout(new GridLayout(3,1));


        JPanel center1 =  new JPanel();
        JPanel center2 =  new JPanel();

//center1
        center1.setLayout(new GridLayout(1,5,1,20));
        center1.setBackground(mainGray);
        center1.setBorder(new LineBorder(Color.BLACK));
        //set properties for wordPanel
        JLabel label1 = new JLabel("  English word:");
        label1.setFont(new Font("consolas",Font.PLAIN,11));
        label1.setForeground(myGreen);

        MyTextField engWordField = new MyTextField(new Dimension(200,20),20,myGreen,mainGray.brighter());
        engWordField.setText("");

        JLabel label2 = new JLabel(" Russian word:");
        label2.setFont(new Font("consolas",Font.PLAIN,11));
        label2.setForeground(myPink);

        JTextField rusWordField = new MyTextField(new Dimension(200,20),20,myPink, mainGray.brighter());
        rusWordField.setText("");

        engWordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    rusWordField.requestFocus();
                }
                super.keyPressed(e);
            }
        });

        JButton addButton = new MyButton("Add",myGreen,mainGray.darker());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!engWordField.getText().equalsIgnoreCase(""))&&(!rusWordField.getText().equalsIgnoreCase(""))) {
                    addWordToVocabulary(engWordField.getText(), rusWordField.getText());
                    for (Word w : wordList) {
                        System.out.print("Слово eng:[");
                        for (String engVer : w.engVersions) {
                            System.out.print(engVer + ", ");
                        }
                        System.out.print("]  rus:[ ");
                        for (String rusVer : w.rusVersions) {
                            System.out.print(rusVer + ", ");
                        }
                        System.out.println("]");
                    }
                    System.out.println("_____________________________________________");
                    engWordField.setText("");
                    rusWordField.setText("");
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "Fill all fields",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        center1.add(label1);
        center1.add(engWordField);
        center1.add(label2);
        center1.add(rusWordField);
        center1.add(addButton);

//center2
        center2.setLayout(new GridLayout(1,3));
        center2.setBackground(mainGray);
        center2.setBorder(new LineBorder(Color.BLACK));
        center2.setBackground(mainGray);
        JLabel deleteLabel = new JLabel(" Delete word: ");
        deleteLabel.setFont(new Font("consolas",Font.PLAIN,11));
        deleteLabel.setForeground(myPink);

        JTextField deleteWordField = new MyTextField(new Dimension(200,20),20,myPink,mainGray.brighter());
        deleteWordField.setText("");
        deleteWordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });

        JButton deleteButton = new MyButton("Delete",myPink,mainGray.darker());
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File check = new  File("D:\\MyWords");
                vocabSize = check.list().length;
                if(vocabSize==0);
                String[] fileNames = check.list();
                for (int i = 1; i <= vocabSize; i++) {
                    try {
                        File wordPath = new File(check.getAbsoluteFile()+"\\"+fileNames[i-1]);
                        FileInputStream fis = new FileInputStream(wordPath);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Word readword = (Word)ois.readObject();
                        ois.close();
                        if(readword.rusVersions.contains(deleteWordField.getText())){
                            readword.rusVersions.remove(deleteWordField.getText());
                            FileOutputStream fos = new FileOutputStream(wordPath);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(readword);
                            break;
                        }
                        if(readword.engVersions.contains(deleteWordField.getText())){
                            readword.engVersions.remove(deleteWordField.getText());
                            FileOutputStream fos = new FileOutputStream(wordPath);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(readword);
                            break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        center2.add(deleteLabel);
        center2.add(deleteWordField);
        center2.add(deleteButton);


//center
        center.add(center1);
        center.add(GrayPanel.getEmptyPanel());
        center.add(center2);

    }

    private boolean addWordToVocabulary(String eng, String rus){
        wordList = Main.WordSupport.downloadWords();
        int responce = Main.WordSupport.isThereTheSameWord(wordList,eng,rus);
        for(Word word: wordList){
            for(String engVer:word.engVersions){
                if(engVer.equalsIgnoreCase(eng)&&(responce>=2)) {
                    word.addRusVersion(rus);
                    System.out.println("sovpadenie eng");
                    Main.WordSupport.saveWord(word);
                    wordList.add(word);
                    return false;
                }
            }
            for(String rusVer:word.rusVersions){
                if(rusVer.equalsIgnoreCase(rus)&&(responce<=-2)) {
                    word.addEngVersion(eng);
                    System.out.println("sovpadenie rus");
                    Main.WordSupport.saveWord(word);
                    wordList.add(word);
                    return false;
                }
            }
        }
        if(responce!=0){
            System.out.println("Новое слово создано");
            Word newWord = new Word(eng,rus);
            Main.WordSupport.saveWord(newWord);
            wordList.add(newWord);
            return true;
        }
        return false;
    }

}
