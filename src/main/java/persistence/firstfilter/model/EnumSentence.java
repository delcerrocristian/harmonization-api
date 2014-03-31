package persistence.firstfilter.model;

/**
 * Created by Cristian del Cerro.
 */
public class EnumSentence {

    private int id;
    private int position;
    private String content;
    private int mainSentence;

    public EnumSentence() {

    }

    public EnumSentence(int id, int position, String content, int mainSentence) {
        this.id = id;
        this.position = position;
        this.content = content;
        this.mainSentence = mainSentence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMainSentence() {
        return mainSentence;
    }

    public void setMainSentence(int mainSentence) {
        this.mainSentence = mainSentence;
    }
}
