package persistence.firstfilter.model;

/**
 * Created by spukyn on 3/05/14.
 */
public class Task {

    private int id;
    private String content;
    private Integer activity;
    private Integer isProcessed;

    public Task() {

    }

    public Task (int id, String content, Integer activity, Integer isProcessed) {
        this.id = id;
        this.content = content;
        this.activity = activity;
        this.isProcessed = isProcessed;
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

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Integer getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Integer isProcessed) {
        this.isProcessed = isProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (activity != task.activity) return false;
        if (id != task.id) return false;
        if (isProcessed != task.isProcessed) return false;
        if (content != null ? !content.equals(task.content) : task.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + activity;
        result = 31 * result + isProcessed;
        return result;
    }
}
