package persistence.firstfilter.model;

/**
 * Created by Cristian del Cerro.
 */
public class MainSentence {

    private int id;
    private String content;
    private String category;
    private Integer standard;
    private Integer reliability;
    private Integer isProcessed;

    public MainSentence() {

    }

    public MainSentence(String content, String category, int standard) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public Integer getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public Integer getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(int isProcessed) {
        this.isProcessed = isProcessed;
    }
}
