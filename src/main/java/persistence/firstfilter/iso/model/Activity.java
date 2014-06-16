package persistence.firstfilter.iso.model;

/**
 * Created by spukyn on 3/05/14.
 */
public class Activity {

    private int id;
    private String name;
    private int process;

    public Activity() {

    }

    public Activity(int id, String name, int process) {
        this.id = id;
        this.name = name;
        this.process = process;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != activity.id) return false;
        if (process != activity.process) return false;
        if (name != null ? !name.equals(activity.name) : activity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + process;
        return result;
    }
}
