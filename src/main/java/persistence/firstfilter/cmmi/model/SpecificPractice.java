package persistence.firstfilter.cmmi.model;

/**
 * Created by Cristian del Cerro
 */
public class SpecificPractice {

    private int id;
    private String title;
    private String description;
    private int specificGoal;

    public SpecificPractice() {

    }

    public SpecificPractice(int id, String title, String description, int specificGoal) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.specificGoal = specificGoal;
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

    public int getSpecificGoal() {
        return specificGoal;
    }

    public void setSpecificGoal(int specificGoal) {
        this.specificGoal = specificGoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecificPractice that = (SpecificPractice) o;

        if (id != that.id) return false;
        if (specificGoal != that.specificGoal) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + specificGoal;
        return result;
    }
}
