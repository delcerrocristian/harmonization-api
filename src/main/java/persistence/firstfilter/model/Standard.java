package persistence.firstfilter.model;

/**
 * Created by Cristian del Cerro.
 */
public class Standard {

    private int id;
    private String name;

    public Standard(String name, int id) {
        this.name = name;
        this.id = id;
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

}
