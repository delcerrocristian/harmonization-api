package templates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian del Cerro.
 */
public interface IsoTemplate {

    void runTemplate(ArrayList<String> listOfText, int idStandard, List<String> patterns);
}
