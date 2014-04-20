package persistence.firstfilter.model;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by spukyn on 20/04/14.
 */
public class ResponseMainSentence {

    private int id;
    private String content;
    private String category;
    private int standard;
    ArrayList<EnumSentence> enumSentences;

    public ResponseMainSentence(MainSentence mainSentence){
        this.id = mainSentence.getId();
        this.content = mainSentence.getContent();
        this.category = mainSentence.getCategory();
        this.standard = mainSentence.getStandard();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public ArrayList<EnumSentence> getEnumSentences() {
        return enumSentences;
    }

    public void setEnumSentences(ArrayList<EnumSentence> enumSentences) {
        this.enumSentences = enumSentences;
    }

    public void addMainSentenceFields(MainSentence mainSentence){
        this.id = mainSentence.getId();
        this.content = mainSentence.getContent();
        this.category = mainSentence.getCategory();
        this.standard = mainSentence.getStandard();
    }

    public void addEnumSentence(EnumSentence enumSentence) {
        enumSentences.add(enumSentence);
    }
}
