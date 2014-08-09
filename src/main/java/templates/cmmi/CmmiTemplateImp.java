package templates.cmmi;

import services.cmmi.CmmiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian del Cerro.
 */
public class CmmiTemplateImp implements CmmiTemplate {

    CmmiAlgorithm cmmiAlgorithm;
    ArrayList<String> processArea;

    public CmmiTemplateImp(CmmiAlgorithm cmmiAlgorithm) {
        this.cmmiAlgorithm = cmmiAlgorithm;
        this.processArea = new ArrayList<String>();
    }

    @Override
    public void runTemplate(ArrayList<String> listOfText, int idStandard) {
        cmmiAlgorithm.start(listOfText,idStandard);
    }


}
