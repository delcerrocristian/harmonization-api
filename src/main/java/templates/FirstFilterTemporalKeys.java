package templates;

/**
 * Created by Cristian del Cerro on 1/04/14.
 */
public class FirstFilterTemporalKeys {

    int mainId;
    int enumId;

    public void initialize(){
        mainId = 1;
        enumId = 1;
    }

    public int insertMainId(){
        return mainId++;
    }

    public int getActualMainId() {
        return mainId;
    }

    public void restartMainId() {
        mainId = 1;
    }

    public int insertEnumId() {
        return enumId++;
    }

    public int getActualEnumId() {
        return enumId;
    }

    public void restartEnumId() {
        enumId = 1;
    }

}
