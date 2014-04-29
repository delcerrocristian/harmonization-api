package persistence.firstfilter.model;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro on 20/04/14.
 */
public class ResponseMainSentence {

    private int id;
    private String content;
    private String category;
    private int standard;
    private int reliability;
    private int is_processed;
    ArrayList<EnumSentence> enumSentences;

    public ResponseMainSentence(MainSentence mainSentence){
        this.id = mainSentence.getId();
        this.content = mainSentence.getContent();
        this.category = mainSentence.getCategory();
        this.standard = mainSentence.getStandard();
        this.reliability = mainSentence.getReliability();
        this.is_processed = mainSentence.getIsProcessed();
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

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getIs_processed() {
        return is_processed;
    }

    public void setIs_processed(int is_processed) {
        this.is_processed = is_processed;
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
