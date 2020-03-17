package classes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static classes.Constants.*;

public class Main {
    static JFrame mainFrame = new JFrame("MyApp");
    static List<Word> wordList = new ArrayList<>();
    static int SCORE = 0;

    Dimension vert = new Dimension((int)(FRAME_WIDTH*0.1),FRAME_HEIGHT);
    Dimension hor = new Dimension((int)(FRAME_WIDTH*0.8),(int)(FRAME_HEIGHT*0.3));

    static StartPanel startPanel = new StartPanel();
    static QuizPanel quizPanel = new QuizPanel();
    static WordEditPanel wordEditPanel = new WordEditPanel();
    static ResultPanel resultPanel = new ResultPanel();
    static QuizTestPanel quizTestPanel = new QuizTestPanel();
    static RepeatWithSentencesPanel repeatWithSentencesPanel = new RepeatWithSentencesPanel();

    static int vocabSize = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.go();
    }
    private void go() {
        if(!wordsFolder.exists()){
            wordsFolder.mkdir();
        }
        wordList = WordSupport.downloadWords();
        setFrame();
    }
    private void setFrame(){
        startPanel.setPanelDimensions(hor,hor,vert,vert);
        quizPanel.setPanelDimensions(hor,hor,vert,vert);
        quizTestPanel.setPanelDimensions(hor,hor,vert,vert);
        repeatWithSentencesPanel.setPanelDimensions(hor,hor,vert,vert);
        wordEditPanel.setPanelDimensions(hor,hor,vert,vert);
        resultPanel.setPanelDimensions(hor,hor,vert,vert);

        mainFrame = new JFrame("Improve English");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(rootPanel);
        rootPanel.setLayout(rootCardLayout);

        rootPanel.add(startPanel,"startPanel");
        rootPanel.add(quizPanel,"quizPanel");
        rootPanel.add(quizTestPanel,"quizTestPanel");
        rootPanel.add(repeatWithSentencesPanel,"repeatWithSentencesPanel");
        rootPanel.add(wordEditPanel,"wordEditPanel");
        rootPanel.add(resultPanel,"resultPanel");

        rootCardLayout.show(rootPanel,"startPanel");
        mainFrame.setBounds(LOCATION_X, LOCATION_Y, FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    static class WordSupport{
        public static List<Word> downloadWords() throws NoWordsException{
            File check = new  File("D:\\MyWords");
            vocabSize = check.list().length;
            if(vocabSize==0);
            String[] fileNames = check.list();
            List<Word> list = new ArrayList<>();
            for (int i = 1; i <= vocabSize; i++) {
                try {
                    FileInputStream fis = new FileInputStream(check.getAbsoluteFile()+"\\"+fileNames[i-1]);
                    ObjectInputStream oos = new ObjectInputStream(fis);
                    list.add((Word)oos.readObject());
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        public static int getVocabSize(){
            File check = new  File("D:\\MyWords");
            return check.list().length;
        }

        public static void saveWord(Word word){
            try {
                FileOutputStream fos = new FileOutputStream(new File(wordsFolder.getAbsoluteFile() + "\\" + word.mainEng + "-" + word.mainRus + ".ser"));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(word);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if there is a eng version of word return 1, if russian -1, else 0, if both 2;
        public static int isThereTheSameWord(List<Word> checkingWords, String eng, String rus){
            for(Word word: checkingWords){
                for(String engVer:word.engVersions){
                    for(String rusVer:word.rusVersions){
                        if((engVer.equalsIgnoreCase(eng))&&(rusVer.equalsIgnoreCase(rus))) return -1;
                    }
                }
                for(String engVer:word.engVersions){
                    if(engVer.equalsIgnoreCase(eng)) return checkingWords.indexOf(word)+2;
                }
                for(String rusVer:word.rusVersions){
                    if(rusVer.equalsIgnoreCase(rus)) return -checkingWords.indexOf(word)-2;
                }
            }
            return 1;
        }

        public static ArrayList<Integer> getRandomQueue(int count){
            ArrayList<Integer> randomQueue = new ArrayList<>();
            if(count==vocabSize){
                for(int i=0; i < vocabSize;i++){
                    randomQueue.add(i);
                }
                return randomQueue;
            }
            randomQueue.add((int)(Math.random()*vocabSize));
            for (int i = 1; i < count; i++) {
                int add = (int) (Math.random() * vocabSize);
                if(vocabSize!=1) {
                    while (add == randomQueue.get(i - 1)) {
                        add = (int) (Math.random() * vocabSize);
                    }
                }else{add=0;}
                randomQueue.add(add);
            }
            return randomQueue;
        }


    }

}
