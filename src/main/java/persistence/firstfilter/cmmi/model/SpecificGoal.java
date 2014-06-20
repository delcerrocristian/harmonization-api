package persistence.firstfilter.cmmi.model;

/**
 * Created by Cristian del Cerro
 */
public class SpecificGoal {

    private int id;
    private String title;
    private String description;
    private int process;

    public SpecificGoal(){

    }

    public SpecificGoal(int id, String title, String description, int process) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.process = process;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecificGoal that = (SpecificGoal) o;

        if (id != that.id) return false;
        if (process != that.process) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + process;
        return result;
    }
}
