package templates.iso;

/**
 * Created by Cristian del Cerro.
 */

import services.iso.IsoService;
import utils.PathFiles;

import java.util.ArrayList;
import java.util.List;

public class IsoTemplateImp implements PathFiles, IsoTemplate {

    IsoService isoService;

    public IsoTemplateImp(IsoService isoService) {
        this.isoService = isoService;
    }

    @Override
    public void runTemplate(ArrayList<String> listOfText, int idStandard, List<String> patterns) {
        AlgorithmByDefault algorithmByDefault = new AlgorithmByDefault(listOfText, idStandard,
                isoService);
        for(String pattern : patterns){
            algorithmByDefault.find(pattern);
        }




    }



}