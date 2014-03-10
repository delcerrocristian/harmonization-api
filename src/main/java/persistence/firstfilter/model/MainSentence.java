package persistence.firstfilter.model;

/**
 * Created by Cristian del Cerro.
 */
public class MainSentence {

    private int id;
    private String content;
    private char category;
    private int standard;

    public MainSentence(int id, String content, char category, int standard) {
        this.id = id;
        this.content = content;
        this.category = category;
        this.standard = standard;
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

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }
}
