package persistence.firstfilter.model;

/**
 * Created by spukyn on 3/05/14.
 */
public class Process {

    private int id;
    private String name;
    private int standard;

    public Process(){

    }

    public Process(int id, String name, int standard){
        this.id = id;
        this.name = name;
        this.standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
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

        Process process = (Process) o;

        if (id != process.id) return false;
        if (standard != process.standard) return false;
        if (name != null ? !name.equals(process.name) : process.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + standard;
        return result;
    }
}
