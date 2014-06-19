package persistence.firstfilter.cmmi.model;

/**
 * Created by Cristian del Cerro
 */
public class WorkProduct {

    int id;
    String description;
    int specificPractice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSpecificPractice() {
        return specificPractice;
    }

    public void setSpecificPractice(int specificPractice) {
        this.specificPractice = specificPractice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkProduct that = (WorkProduct) o;

        if (id != that.id) return false;
        if (specificPractice != that.specificPractice) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + specificPractice;
        return result;
    }
}
