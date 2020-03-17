package classes;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {
    public List<String> rusVersions = new ArrayList<>();
    public List<String> engVersions = new ArrayList<>();
    public String mainRus;
    public String mainEng;

    public Word(String eng, String rus) {
        mainEng = eng;
        mainRus = rus;
        rusVersions.add(rus);
        engVersions.add(eng);
    }
    public String getRandomRusVersion() {
        return rusVersions.get((int)(Math.random()*rusVersions.size()));
    }
    public String getRandomEngVersion() {
        return engVersions.get((int)(Math.random()*engVersions.size()));
    }
    public void addRusVersion(String rus) {
        rusVersions.add(rus);
    }
    public void addEngVersion(String eng) {
        engVersions.add(eng);
    }
    public void deleteRusVersion(String delRus) {
        rusVersions.remove(delRus);
    }
    public void deleteEngVersion(String delEng) {
        rusVersions.remove(delEng);
    }
}
