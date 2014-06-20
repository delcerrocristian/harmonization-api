package persistence.firstfilter.cmmi.model;

/**
 * Created by Cristian del Cerro
 */
public class Standard {

    private int id;
    private String name;

    public Standard() {

    }

    public Standard(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        Standard standard = (Standard) o;

        if (id != standard.id) return false;
        if (name != null ? !name.equals(standard.name) : standard.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
